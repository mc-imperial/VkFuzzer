package tool.codegen.vulkan.device;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.device.VkDeviceWaitIdleConfig;
import tool.configs.vulkan.device.VkGetDeviceQueueConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkDeviceWaitIdleGenerator extends VulkanCodeGenerator {
    public VkDeviceWaitIdleGenerator(RandomStringGenerator randomStringGenerator,
                                     RandomNumberGanerator randomNumberGanerator,
                                     FreshMap freshMap,
                                     Coverage coverage,
                                     VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_DEVICE_WAIT_IDLE, globalState);
    }

    @Override
    public Config generateConfig() {
        VkDeviceWaitIdleConfig config = new VkDeviceWaitIdleConfig();

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        // Find queue
        ArrayList<Config> deviceQueues =
                globalState.getConfig(VulkanState.VK_GET_DEVICE_QUEUE);

        VkGetDeviceQueueConfig deviceQueueConfig =
                (VkGetDeviceQueueConfig)
                        deviceQueues.get(deviceQueues.size() - 1);

        config.setDevice(deviceQueueConfig.getDevice());
        config.addDependency(deviceQueueConfig.getId());
        config.setBad(deviceQueueConfig.isBad());

        globalState.addConfig(VulkanState.VK_DEVICE_WAIT_IDLE, config);

        return config;
    }
}
