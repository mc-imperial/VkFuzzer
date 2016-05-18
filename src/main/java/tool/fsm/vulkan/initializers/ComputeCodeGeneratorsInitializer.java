package tool.fsm.vulkan.initializers;

import tool.codegen.coverage.CoverageRandomizer;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.commandbuffers.VkAllocateCommandBuffersGenerator;
import tool.codegen.vulkan.commandbuffers.VkBeginCommandBufferGenerator;
import tool.codegen.vulkan.commandbuffers.VkCreateCommandPoolGenerator;
import tool.codegen.vulkan.commandbuffers.VkEndCommandBufferGenerator;
import tool.codegen.vulkan.deallocation.DeallocationGenerator;
import tool.codegen.vulkan.device.*;
import tool.codegen.vulkan.enumeration.VkEnumerateInstanceExtensionPropertiesGenerator;
import tool.codegen.vulkan.enumeration.VkEnumerateInstanceLayerPropertiesGenerator;
import tool.codegen.vulkan.enumeration.VkEnumeratePhysicalDevicesGenerator;
import tool.codegen.vulkan.instance.VkApplicationInfoGenerator;
import tool.codegen.vulkan.instance.VkCreateInstanceGenerator;
import tool.codegen.vulkan.instance.VkInstanceCreateGenerator;
import tool.codegen.vulkan.pipelines.VkCreatePipelineCacheGenerator;
import tool.codegen.vulkan.pipelines.VkCreatePipelineLayoutGenerator;
import tool.codegen.vulkan.pipelines.VkGetPipelineCacheDataGenerator;
import tool.codegen.vulkan.resources.*;
import tool.codegen.vulkan.shaders.VkCreateShaderModuleGenerator;
import tool.codegen.vulkan.synchronisation.*;
import tool.codegen.vulkan.utils.RandomSeedGenerator;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.vulkan.states.VulkanState;

import java.util.HashMap;

/**
 * Created by constantinos on 16/05/2016.
 */
public class ComputeCodeGeneratorsInitializer extends CodeGeneratorsInitializer {
    public ComputeCodeGeneratorsInitializer(VulkanGlobalState globalState) {
        super(globalState);
    }

    @Override
    // Initializes the generator hashmap
    public HashMap<String, VulkanCodeGenerator> initializeCodeGenerators() {
        CoverageRandomizer randomizer = new CoverageRandomizer();

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
                VulkanState.VK_CREATE_FRAMEBUFFER.toString(),
                new VkCreateFrameBufferGenerator(
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
                VulkanState.DEALLOCATION.toString(),
                new DeallocationGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.RANDOM_SEED.toString(),
                new RandomSeedGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.SELECT_DEVICE.toString(),
                new SelectDeviceGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        return generators;
    }
}
