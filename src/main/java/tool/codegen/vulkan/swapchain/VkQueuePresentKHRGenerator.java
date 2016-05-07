package tool.codegen.vulkan.swapchain;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.device.VkGetDeviceQueueConfig;
import tool.configs.vulkan.swapchain.InitSwapchainConfig;
import tool.configs.vulkan.swapchain.VkAcquireNextImageKHRConfig;
import tool.configs.vulkan.swapchain.VkQueuePresentKHRConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkQueuePresentKHRGenerator extends VulkanCodeGenerator {
    private final String PRESENT_INFO = "presentInfo";

    public VkQueuePresentKHRGenerator(RandomStringGenerator randomStringGenerator,
                                      RandomNumberGanerator randomNumberGanerator,
                                      FreshMap freshMap,
                                      Coverage coverage,
                                      VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_QUEUE_PRESENT_KHR, globalState);
    }

    @Override
    public Config generateConfig() {
        VkQueuePresentKHRConfig config = new VkQueuePresentKHRConfig();

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setPresentInfo(PRESENT_INFO + freshMap.getFreshId(PRESENT_INFO));

        // Find queue
        ArrayList<Config> deviceQueues =
                globalState.getConfig(VulkanState.VK_GET_DEVICE_QUEUE);

        VkGetDeviceQueueConfig deviceQueueConfig =
                (VkGetDeviceQueueConfig)
                        deviceQueues.get(deviceQueues.size() - 1);

        // image
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_ACQUIRE_NEXT_IMAGE_KHR);

        VkAcquireNextImageKHRConfig aquireNextImageConfig =
                (VkAcquireNextImageKHRConfig)
                configs.get(configs.size() - 1);

        // swapchain
        ArrayList<Config> swapchains =
                globalState.getConfig(VulkanState.INIT_SWAPCHAIN);

        InitSwapchainConfig initSwapchainConfig =
                (InitSwapchainConfig)
                swapchains.get(randomNumberGanerator.randomNumber(swapchains.size()));

        config.setSwapchain(initSwapchainConfig.getSwapchain());
        config.setBad(initSwapchainConfig.isBad());
        config.addDependency(initSwapchainConfig.getId());

        config.setImageIndex(aquireNextImageConfig.getNextImage());
        config.setBad(config.isBad() || aquireNextImageConfig.isBad());
        config.addDependency(aquireNextImageConfig.getId());

        config.setQueue(deviceQueueConfig.getQueue());
        config.setBad(config.isBad() || deviceQueueConfig.isBad());
        config.addDependency(deviceQueueConfig.getId());

        globalState.addConfig(VulkanState.VK_QUEUE_PRESENT_KHR, config);

        return config;
    }
}
