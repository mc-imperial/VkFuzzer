package tool.fsm.vulkan.initializers;

import tool.codegen.coverage.CoverageRandomizer;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.commandbuffers.VkAllocateCommandBuffersGenerator;
import tool.codegen.vulkan.commandbuffers.VkBeginCommandBufferGenerator;
import tool.codegen.vulkan.commandbuffers.VkCreateCommandPoolGenerator;
import tool.codegen.vulkan.commandbuffers.VkEndCommandBufferGenerator;
import tool.codegen.vulkan.commands.VkCmdClearColorImageGenerator;
import tool.codegen.vulkan.deallocation.DeallocationGenerator;
import tool.codegen.vulkan.device.*;
import tool.codegen.vulkan.enumeration.VkEnumerateInstanceExtensionPropertiesGenerator;
import tool.codegen.vulkan.enumeration.VkEnumerateInstanceLayerPropertiesGenerator;
import tool.codegen.vulkan.enumeration.VkEnumeratePhysicalDevicesGenerator;
import tool.codegen.vulkan.instance.VkApplicationInfoGenerator;
import tool.codegen.vulkan.instance.VkCreateInstanceGenerator;
import tool.codegen.vulkan.instance.VkInstanceCreateGenerator;
import tool.codegen.vulkan.memory.PopulateVertexBufferGenerator;
import tool.codegen.vulkan.pipelines.VkCreateGraphicsPipelinesGenerator;
import tool.codegen.vulkan.pipelines.VkCreatePipelineCacheGenerator;
import tool.codegen.vulkan.pipelines.VkCreatePipelineLayoutGenerator;
import tool.codegen.vulkan.pipelines.VkGetPipelineCacheDataGenerator;
import tool.codegen.vulkan.resources.*;
import tool.codegen.vulkan.shaders.VkCreateShaderModuleGenerator;
import tool.codegen.vulkan.swapchain.InitSwapchainGenerator;
import tool.codegen.vulkan.swapchain.VkAcquireNextImageKHRGenerator;
import tool.codegen.vulkan.swapchain.VkCreateSurfaceKHRGenerator;
import tool.codegen.vulkan.swapchain.VkQueuePresentKHRGenerator;
import tool.codegen.vulkan.synchronisation.*;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.HashMap;

/**
 * Created by constantinos on 30/03/2016.
 * Initializes code generators
 */
public class CodeGeneratorsInitializer {
    private final RandomNumberGanerator randomNumberGanerator;
    private final RandomStringGenerator randomStringGenerator;
    private final FreshMap freshMap;
    private final VulkanGlobalState globalState;
    private final HashMap<String, VulkanCodeGenerator> generators;

    public CodeGeneratorsInitializer(final VulkanGlobalState globalState) {
        randomNumberGanerator = new RandomNumberGanerator();
        randomStringGenerator = new RandomStringGenerator();
        freshMap = new FreshMap();
        generators = new HashMap<>();
        this.globalState = globalState;
    }

    // Initializes the generator hashmap
    public HashMap<String, VulkanCodeGenerator> initializeCodeGenerators() {
        CoverageRandomizer randomizer = new CoverageRandomizer();

        // Now populate the hashmap
        generators.put(
                VulkanState.VK_ENUMERATE_INSTANCE_EXTENSION_PROPERTIES.toString(),
                new VkEnumerateInstanceExtensionPropertiesGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState
                ));

        generators.put(
                VulkanState.VK_ENUMERATE_INSTANCE_LAYER_PROPERTIES.toString(),
                new VkEnumerateInstanceLayerPropertiesGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState
                ));

        generators.put(
                VulkanState.VK_APPLICATION_INFO.toString(),
                new VkApplicationInfoGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_INSTANCE_CREATE_INFO.toString(),
                new VkInstanceCreateGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_INSTANCE.toString(),
                new VkCreateInstanceGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_ENUMERATE_PHYSICAL_DEVICES.toString(),
                new VkEnumeratePhysicalDevicesGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.GET_DEVICE_PROPERTIES.toString(),
                new DevicePropertiesGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_DEVICE.toString(),
                new VkCreateDeviceGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_COMMAND_POOL.toString(),
                new VkCreateCommandPoolGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_ALLOCATE_COMMAND_BUFFERS.toString(),
                new VkAllocateCommandBuffersGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_GET_DEVICE_QUEUE.toString(),
                new VkGetDeviceQueueGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_EVENT.toString(),
                new VkCreateEventGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_SEMAPHORE.toString(),
                new VkCreateSemaphoreGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_FENCE.toString(),
                new VkCreateFenceGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_GET_EVENT_STATUS.toString(),
                new VkGetEventStatusGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_GET_FENCE_STATUS.toString(),
                new VkGetFenceStatusGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_RESET_EVENT.toString(),
                new VkResetEventGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_RESET_FENCES.toString(),
                new VkResetFencesGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_SET_EVENT.toString(),
                new VkSetEventGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_BUFFER.toString(),
                new VkCreateBufferGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_BUFFER_VIEW.toString(),
                new VkCreateBufferViewGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_GET_BUFFER_MEMORY_REQUIREMENTS.toString(),
                new VkGetBufferMemoryRequirementsGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_IMAGE.toString(),
                new VkCreateImageGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_IMAGE_VIEW.toString(),
                new VkCreateImageViewGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_GET_IMAGE_MEMORY_REQUIREMENTS.toString(),
                new VkGetImageMemoryRequirementsGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_GET_IMAGE_SUBRESOURCE_LAYOUT.toString(),
                new VkGetImageSubResourceLayoutGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_SHADER_MODULE.toString(),
                new VkCreateShaderModuleGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_RENDERPASS.toString(),
                new VkCreateRenderPassGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_FRAMEBUFFER.toString(),
                new VkCreateFrameBufferGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_GRAPHICS_PIPELINES.toString(),
                new VkCreateGraphicsPipelinesGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_PIPELINE_CACHE.toString(),
                new VkCreatePipelineCacheGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_PIPELINE_LAYOUT.toString(),
                new VkCreatePipelineLayoutGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_GET_PIPELINE_CACHE_DATA.toString(),
                new VkGetPipelineCacheDataGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_SURFACE_KHR.toString(),
                new VkCreateSurfaceKHRGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.INIT_SWAPCHAIN.toString(),
                new InitSwapchainGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.POPULATE_VERTEX_BUFFER.toString(),
                new PopulateVertexBufferGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_ACQUIRE_NEXT_IMAGE_KHR.toString(),
                new VkAcquireNextImageKHRGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_BEGIN_COMMAND_BUFFER.toString(),
                new VkBeginCommandBufferGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_END_COMMAND_BUFFER.toString(),
                new VkEndCommandBufferGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_QUEUE_SUBMIT.toString(),
                new VkQueueSubmitGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_DEVICE_WAIT_IDLE.toString(),
                new VkDeviceWaitIdleGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_QUEUE_PRESENT_KHR.toString(),
                new VkQueuePresentKHRGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CMD_CLEAR_COLOR.toString(),
                new VkCmdClearColorImageGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.DEALLOCATION.toString(),
                new DeallocationGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        return generators;
    }
}
