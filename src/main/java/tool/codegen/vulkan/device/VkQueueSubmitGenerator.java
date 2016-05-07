package tool.codegen.vulkan.device;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkBeginCommandBufferConfig;
import tool.configs.vulkan.device.VkGetDeviceQueueConfig;
import tool.configs.vulkan.device.VkQueueSubmitConfig;
import tool.configs.vulkan.swapchain.InitSwapchainConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkQueueSubmitGenerator extends VulkanCodeGenerator {
    private final String SUBMIT_INFO = "submitInfo";

    public VkQueueSubmitGenerator(RandomStringGenerator randomStringGenerator,
                                  RandomNumberGanerator randomNumberGanerator,
                                  FreshMap freshMap,
                                  Coverage coverage,
                                  VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_QUEUE_SUBMIT, globalState);
    }

    @Override
    public Config generateConfig() {
        VkQueueSubmitConfig config = new VkQueueSubmitConfig();

        config.setId(generateConfigId());
        config.setSubmitInfo(SUBMIT_INFO + freshMap.getFreshId(SUBMIT_INFO));

        // Find a random device
        ArrayList<Config> deviceQueues =
                globalState.getConfig(VulkanState.VK_GET_DEVICE_QUEUE);

        VkGetDeviceQueueConfig  deviceQueueConfig =
                (VkGetDeviceQueueConfig)
                deviceQueues.get(deviceQueues.size() - 1);

        config.setQueue(deviceQueueConfig.getQueue());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.addDependency(deviceQueueConfig.getId());

        // Find a random device
        ArrayList<Config> commandBuffers =
                globalState.getConfig(VulkanState.VK_BEGIN_COMMAND_BUFFER);

        VkBeginCommandBufferConfig  commandBufferConfig =
                (VkBeginCommandBufferConfig)
                commandBuffers.get(commandBuffers.size() - 1);

        config.setCommandBuffer(commandBufferConfig.getCommandBuffer());
        config.addDependency(commandBufferConfig.getId());
        config.setBad(commandBufferConfig.isBad() || deviceQueueConfig.isBad());

        globalState.addConfig(VulkanState.VK_QUEUE_SUBMIT, config);

        return config;
    }
}
