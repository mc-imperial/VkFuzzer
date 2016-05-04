/*
* Vulkan Samples
*
* Copyright (C) 2015-2016 Valve Corporation
* Copyright (C) 2015-2016 LunarG, Inc.
*
* Permission is hereby granted, free of charge, to any person obtaining a
* copy of this software and associated documentation files (the "Software"),
* to deal in the Software without restriction, including without limitation
* the rights to use, copy, modify, merge, publish, distribute, sublicense,
* and/or sell copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included
* in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL
* THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
* DEALINGS IN THE SOFTWARE.
*/

#define EXIT_INVALID_CODE_GENERATION -5
#define EXIT_RUN_OUT_OF_MEMORY -6
#define ASSERTION_FAILED -7
#define NO_PRESENT_DEVICE_AVAILABLE -8
#define assert(C) if(!(C)) { std::cerr << "Assertion failed at line " << __LINE__ << std::endl; exit(ASSERTION_FAILED);}

#define XYZ1(_x_, _y_, _z_) (_x_), (_y_), (_z_), 1.f

#define WIDTH 640
#define HEIGHT 480

#ifdef _WIN32
#pragma comment(linker, "/subsystem:console")
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
#endif // _WIN32

#include <vulkan/vulkan.h>

#include <SDL.h>
#include <SDL_syswm.h>

#include <cstring>
#include <vector>
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <fstream>

SDL_Window *window;
SDL_SysWMinfo windowInfo;

// Initializes SDL
SDL_bool initSDL()
{
    SDL_Init(SDL_INIT_VIDEO);

    window = SDL_CreateWindow("VulkanProgram", SDL_WINDOWPOS_UNDEFINED,
            SDL_WINDOWPOS_UNDEFINED, WIDTH, HEIGHT, SDL_WINDOW_HIDDEN);

    if (window == NULL)
    {
        std::cerr << "Could not initialize window" << std::endl;
        return SDL_FALSE;
    }

    SDL_SysWMinfo windowInfo;
    SDL_VERSION(&windowInfo.version);
    return SDL_GetWindowWMInfo(window, &windowInfo);
}

// Shutsdown SDL
void shutDownSDL()
{
    SDL_DestroyWindow(window);
    SDL_Quit();
}

void checkResultOutOfMemory(VkResult result)
{
    if ((result == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (result == VK_ERROR_OUT_OF_DEVICE_MEMORY))
    {
        std::cerr << "Run out of memory. Exiting gracefully." << std::endl;
        exit(EXIT_RUN_OUT_OF_MEMORY);
    }
}

struct FuzzerLogicalDevice
{
    VkDevice device;
    VkPhysicalDevice gpu;
    uint32_t queueFamilyIndex;
    uint32_t maxQueueCount;
    bool supportsGraphics;
    bool supportsCompute;
    bool supportsTransfer;
    bool supportsSparseBinding;
};

struct Vertex
{
    float posX, posY, posZ, posW;    // Position data
    float r, g, b, a;                // Color
};

struct SwapchainBuffer
{
    VkImage image;
    VkImageView view;
};

const Vertex triData[] =
{
    { XYZ1( -0.25, -0.25, 0 ), XYZ1( 1.f, 0.f, 0.f ) },
    { XYZ1(  0.25, -0.25, 0 ), XYZ1( 1.f, 0.f, 0.f ) },
    { XYZ1(  0,  0.25, 0 ),    XYZ1( 1.f, 0.f, 0.f ) },
    { XYZ1( -0.75, -0.25, 0 ), XYZ1( 0.f, 1.f, 0.f ) },
    { XYZ1( -0.25, -0.25, 0 ), XYZ1( 0.f, 1.f, 0.f ) },
    { XYZ1(  -0.5,  0.25, 0 ), XYZ1( 0.f, 1.f, 0.f ) },
    { XYZ1(  0.25, -0.25, 0 ), XYZ1( 0.f, 0.f, 1.f ) },
    { XYZ1(  0.75, -0.25, 0 ), XYZ1( 0.f, 0.f, 1.f ) },
    { XYZ1(  0.5,  0.25, 0 ),  XYZ1( 0.f, 0.f, 1.f ) },
};

int main(int argc, char *argv[])
{
    // Set seed for random operations
    srand(static_cast<unsigned>(time(0)));

    // Init SDL
    if (!initSDL())
    {
        return 1;
    }

    // Swapchain instance extension
    std::vector<const char*> instanceExtensions;
    instanceExtensions.push_back(VK_KHR_SURFACE_EXTENSION_NAME);
#ifdef __ANDROID__
    instanceExtensions.push_back(VK_KHR_ANDROID_SURFACE_EXTENSION_NAME);
#elif defined(_WIN32)
    instanceExtensions.push_back(VK_KHR_WIN32_SURFACE_EXTENSION_NAME);
#else
    instanceExtensions.push_back(VK_KHR_XCB_SURFACE_EXTENSION_NAME);
#endif

    // Swapchain device extension
    std::vector<const char*> deviceExtensions;
    deviceExtensions.push_back(VK_KHR_SWAPCHAIN_EXTENSION_NAME);

${config.body}

    shutDownSDL();

    return 0;
}
