package tool.codegen.vulkan.swapchain;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.swapchain.InitSwapchainConfig;
import tool.configs.vulkan.swapchain.VkAcquireNextImageKHRConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkAcquireNextImageKHRGenerator extends VulkanCodeGenerator {
    private final String NEXT_IMAGE = "nextImage";

    public VkAcquireNextImageKHRGenerator(RandomStringGenerator randomStringGenerator,
                                          RandomNumberGanerator randomNumberGanerator,
                                          FreshMap freshMap,
                                          Coverage coverage,
                                          VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_ACQUIRE_NEXT_IMAGE_KHR, globalState);
    }

    @Override
    public Config generateConfig() {
        VkAcquireNextImageKHRConfig config = new VkAcquireNextImageKHRConfig();

        config.setId(generateConfigId());
        config.setNextImage(NEXT_IMAGE + freshMap.getFreshId(NEXT_IMAGE));
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.INIT_SWAPCHAIN);

        InitSwapchainConfig initSwapchainConfig =
                (InitSwapchainConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setDevice(initSwapchainConfig.getDevice());
        config.setSwapchain(initSwapchainConfig.getSwapchain());
        config.addDependency(initSwapchainConfig.getId());
        config.setBad(initSwapchainConfig.isBad());

        globalState.addConfig(VulkanState.VK_ACQUIRE_NEXT_IMAGE_KHR, config);
        return config;
    }
}
