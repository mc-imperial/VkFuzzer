package tool.codegen.vulkan.device;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.device.VkGetDeviceQueueConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 17/04/2016.
 */
public class VkGetDeviceQueueGenerator extends VulkanCodeGenerator {
    private final String QUEUE = "queue";
    private final String QUEUE_COUNT_INDEX = "qcountIndex";
    private final String QUEUE_FAMILY_INDEX = "familyIndex";

    public VkGetDeviceQueueGenerator(RandomStringGenerator randomStringGenerator,
                                     RandomNumberGanerator randomNumberGanerator,
                                     FreshMap freshMap,
                                     Coverage coverage,
                                     VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_GET_DEVICE_QUEUE, globalState);
    }

    @Override
    public Config generateConfig() {
        VkGetDeviceQueueConfig config = new VkGetDeviceQueueConfig();

        config.setId(generateConfigId());
        config.setQueue(QUEUE + freshMap.getFreshId(QUEUE));
        config.setQueueCountIndex(QUEUE_COUNT_INDEX +
                freshMap.getFreshId(QUEUE_COUNT_INDEX));
        config.setQueueFamilyIndex(QUEUE_FAMILY_INDEX +
                freshMap.getFreshId(QUEUE_FAMILY_INDEX));

        // Find a random device
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_COMMAND_POOL);

        VkCreateCommandPoolConfig cmdPoolConfig =
                (VkCreateCommandPoolConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.addDependency(cmdPoolConfig.getId());
        config.setQueueIndex(cmdPoolConfig.getRandomQueueCount());
        config.setFamilyIndex(cmdPoolConfig.getRandomQueueIndex());
        config.setDevice(cmdPoolConfig.getRandomDevice());

        return config;
    }
}
