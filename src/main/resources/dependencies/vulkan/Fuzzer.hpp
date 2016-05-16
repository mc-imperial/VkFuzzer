#ifndef _FUZZER_FUZZER_HPP_
#define _FUZZER_FUZZER_HPP_

#ifdef _WIN32
#ifdef _FUZZER_COMPUTE_ONLY_
#pragma comment(linker, "/subsystem:console")
#endif
#include "platform/windows/ExitCondition.hpp"
#include "platform/windows/WindowConfig.hpp"
#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif
#ifndef VK_USE_PLATFORM_WIN32_KHR
#define VK_USE_PLATFORM_WIN32_KHR
#endif
#ifndef NOMINMAX
#define NOMINMAX /* Don't let Windows define min() or max() */
#endif
#define APP_NAME_STR_LEN 80
#elif defined(__ANDROID__)
// Include files for Android
#include <unistd.h>
#include <android/log.h>
#include "vulkan_wrapper.h" // Include Vulkan_wrapper and dynamically load symbols.
#else //__ANDROID__
#include <unistd.h>
#include "vulkan/vk_sdk_platform.h"
#include "../platform/linux/ExitCondition.hpp"
#include "../platform/linux/WindowConfig.hpp"
#ifndef VK_USE_PLATFORM_XCB_KHR
#define VK_USE_PLATFORM_XCB_KHR
#endif
#endif // _WIN32

#include <vulkan/vulkan.h>

#define assert(C) if(!(C)) { std::cerr << "Assertion failed at line " << __LINE__ << std::endl; exit(ASSERTION_FAILED);}

#define EXIT_INVALID_CODE_GENERATION -5
#define EXIT_RUN_OUT_OF_MEMORY -6
#define ASSERTION_FAILED -7
#define NO_PRESENT_DEVICE_AVAILABLE -8
#define NO_MEMORY_TYPE_INDEX_FOUND -9
#define MAX_LOGICAL_DEVICES 4

uint32_t totalLogicalDevices = 0;

const char* const* VALIDATION_LAYERS = {"VK_LAYER_LUNARG_standard_validation"};

void checkResultOutOfMemory(VkResult result)
{
    if ((result == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (result == VK_ERROR_OUT_OF_DEVICE_MEMORY))
    {
        std::cerr << "Run out of memory. Exiting gracefully." << std::endl;
        exit(EXIT_RUN_OUT_OF_MEMORY);
    }
}

bool memoryTypeFromProperties(VkPhysicalDeviceMemoryProperties &deviceMemoryProperties,
        uint32_t typeBits, VkFlags requirements_mask, uint32_t *typeIndex)
{
     // Search memtypes to find first index with those properties
     for (uint32_t i = 0; i < 32; i++) {
         if ((typeBits & 1) == 1) {
             // Type is available, does it match user properties?
             if ((deviceMemoryProperties.memoryTypes[i].propertyFlags & requirements_mask)
                    == requirements_mask) {
                 *typeIndex = i;
                 return true;
             }
         }
         typeBits >>= 1;
     }
     // No memory types matched, return failure
     return false;
}

void setImageLayout(
	VkCommandBuffer commandBuffer,
	VkImage image,
	VkImageAspectFlags aspectMask,
	VkImageLayout oldImageLayout,
	VkImageLayout newImageLayout)
{
	VkImageMemoryBarrier image_memory_barrier = {};
	image_memory_barrier.sType = VK_STRUCTURE_TYPE_IMAGE_MEMORY_BARRIER;
	image_memory_barrier.pNext = NULL;
	image_memory_barrier.srcAccessMask = 0;
	image_memory_barrier.dstAccessMask = 0;
	image_memory_barrier.oldLayout = oldImageLayout;
	image_memory_barrier.newLayout = newImageLayout;
	image_memory_barrier.srcQueueFamilyIndex = VK_QUEUE_FAMILY_IGNORED;
	image_memory_barrier.dstQueueFamilyIndex = VK_QUEUE_FAMILY_IGNORED;
	image_memory_barrier.image = image;
	image_memory_barrier.subresourceRange.aspectMask = aspectMask;
	image_memory_barrier.subresourceRange.baseMipLevel = 0;
	image_memory_barrier.subresourceRange.levelCount = 1;
	image_memory_barrier.subresourceRange.baseArrayLayer = 0;
	image_memory_barrier.subresourceRange.layerCount = 1;

	if (oldImageLayout == VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL) {
		image_memory_barrier.srcAccessMask =
			VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT;
	}

	if (newImageLayout == VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL) {
		image_memory_barrier.dstAccessMask = VK_ACCESS_TRANSFER_WRITE_BIT;
	}

	if (newImageLayout == VK_IMAGE_LAYOUT_TRANSFER_SRC_OPTIMAL) {
		image_memory_barrier.dstAccessMask = VK_ACCESS_TRANSFER_READ_BIT;
	}

	if (oldImageLayout == VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL) {
		image_memory_barrier.srcAccessMask = VK_ACCESS_TRANSFER_WRITE_BIT;
	}

	if (oldImageLayout == VK_IMAGE_LAYOUT_PREINITIALIZED) {
		image_memory_barrier.srcAccessMask = VK_ACCESS_HOST_WRITE_BIT;
	}

	if (newImageLayout == VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL) {
		image_memory_barrier.srcAccessMask =
			VK_ACCESS_HOST_WRITE_BIT | VK_ACCESS_TRANSFER_WRITE_BIT;
		image_memory_barrier.dstAccessMask = VK_ACCESS_SHADER_READ_BIT;
	}

	if (newImageLayout == VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL) {
		image_memory_barrier.dstAccessMask =
			VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT;
	}

	if (newImageLayout == VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL) {
		image_memory_barrier.dstAccessMask =
			VK_ACCESS_DEPTH_STENCIL_ATTACHMENT_WRITE_BIT;
	}

	VkPipelineStageFlags src_stages = VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT;
	VkPipelineStageFlags dest_stages = VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT;

	vkCmdPipelineBarrier(commandBuffer, src_stages, dest_stages, 0, 0, NULL, 0, NULL,
		1, &image_memory_barrier);
}

struct FuzzerLogicalDevice
{
    VkDevice device;
    VkPhysicalDevice gpu;
    VkPhysicalDeviceMemoryProperties deviceMemoryProperties;
    uint32_t gpuQueueFamilyCount;
    uint32_t queueFamilyIndex;
    uint32_t maxQueueCount;
    bool supportsGraphics;
    bool supportsCompute;
    bool supportsTransfer;
    bool supportsSparseBinding;
};

struct SwapchainBuffer
{
    VkImage image;
    VkImageView view;
};

#endif // _FUZZER_FUZZER_HPP_