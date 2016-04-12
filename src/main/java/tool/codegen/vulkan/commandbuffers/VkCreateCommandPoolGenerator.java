package tool.codegen.vulkan.commandbuffers;

import com.sun.org.apache.regexp.internal.RE;
import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.device.VkCreateDeviceConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 11/04/2016.
 */
public class VkCreateCommandPoolGenerator extends VulkanCodeGenerator {
    private final String CMD_POOL_CREATE_INFO = "cmdPoolCreateInfo";
    private final String QUEUE_FAMILY_INDEX = "queueIndex";
    private final String FLAGS = "0";
    private final String LOGICAL_DEVICE = "logicalDevice";
    private final String RESULT = "result";
    private final String COMMAND_POOL = "commandPool";

    public VkCreateCommandPoolGenerator(RandomStringGenerator randomStringGenerator,
                                        RandomNumberGanerator randomNumberGanerator,
                                        FreshMap freshMap,
                                        Coverage coverage,
                                        VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.VK_CREATE_COMMAND_POOL, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateCommandPoolConfig config = new VkCreateCommandPoolConfig();

        config.setId(generateConfigId());
        config.setCmdPoolCreateInfo(CMD_POOL_CREATE_INFO +
                freshMap.getFreshId(CMD_POOL_CREATE_INFO));
        config.setFlags(FLAGS);
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setCommandPool(COMMAND_POOL +
                freshMap.getFreshId(COMMAND_POOL));

        // Select a random logical device
        ArrayList<Config> vkCreateDeviceConfigs =
                globalState.getConfig(VulkanState.VK_CREATE_DEVICE);

        VkCreateDeviceConfig randomConfig = (VkCreateDeviceConfig)
                vkCreateDeviceConfigs.get(
                randomNumberGanerator.randomNumber(vkCreateDeviceConfigs.size()));

//        config.setLogicalDevice(randomConfig.getLogicalDevices() +
//                randomNumberGanerator.randomNumber(randomConfig.getDevicePropertiesConfigs()));



        return config;
    }
}
