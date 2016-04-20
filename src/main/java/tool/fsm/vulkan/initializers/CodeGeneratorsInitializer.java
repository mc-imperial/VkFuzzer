package tool.fsm.vulkan.initializers;

import tool.codegen.coverage.CoverageRandomizer;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.commandbuffers.VkAllocateCommandBuffersGenerator;
import tool.codegen.vulkan.commandbuffers.VkCreateCommandPoolGenerator;
import tool.codegen.vulkan.deallocation.DeallocationGenerator;
import tool.codegen.vulkan.device.DevicePropertiesGenerator;
import tool.codegen.vulkan.device.VkCreateDeviceGenerator;
import tool.codegen.vulkan.device.VkGetDeviceQueueGenerator;
import tool.codegen.vulkan.enumeration.VkEnumerateInstanceExtensionPropertiesGenerator;
import tool.codegen.vulkan.enumeration.VkEnumerateInstanceLayerPropertiesGenerator;
import tool.codegen.vulkan.enumeration.VkEnumeratePhysicalDevicesGenerator;
import tool.codegen.vulkan.instance.VkApplicationInfoGenerator;
import tool.codegen.vulkan.instance.VkCreateInstanceGenerator;
import tool.codegen.vulkan.instance.VkInstanceCreateGenerator;
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
