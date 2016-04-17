package tool.codegen.vulkan.device;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.device.VkCreateDeviceConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by cvryo on 10/04/2016.
 */
public class VkCreateDeviceGenerator extends VulkanCodeGenerator {
    private final String LOGICAL_DEVICES = "logicalDevices";
    private final String QUEUE_INDEX = "queueIndex";
    private final String QUEUE_COUNTS = "queueCounts";

    public VkCreateDeviceGenerator(RandomStringGenerator randomStringGenerator,
                                   RandomNumberGanerator randomNumberGanerator,
                                   FreshMap freshMap,
                                   Coverage coverage,
                                   VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.VK_CREATE_DEVICE, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateDeviceConfig config = new VkCreateDeviceConfig();
        ArrayList<Config> enumeratedPhysicalDeviceProperties =
                globalState.getConfig(VulkanState.GET_DEVICE_PROPERTIES);

        config.setId(generateConfigId());
        config.setDevicePropertiesConfigs(enumeratedPhysicalDeviceProperties);
        config.setLogicalDevices(LOGICAL_DEVICES +
                freshMap.getFreshId(LOGICAL_DEVICES));
        config.setQueueIndex(QUEUE_INDEX +
                freshMap.getFreshId(QUEUE_INDEX));
        config.setQueueCounts(QUEUE_COUNTS +
                freshMap.getFreshId(QUEUE_COUNTS));

        // Check if all configs are bad
        boolean allBadConfigs = true;
        for (Config theConfig : enumeratedPhysicalDeviceProperties) {
            allBadConfigs = allBadConfigs && theConfig.isBad();
            config.addDependency(theConfig.getId());
        }

        config.setBad(allBadConfigs);

        globalState.addConfig(VulkanState.VK_CREATE_DEVICE, config);

        return config;
    }
}
