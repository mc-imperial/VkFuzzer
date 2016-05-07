package tool.codegen.vulkan.commands;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkBeginCommandBufferConfig;
import tool.configs.vulkan.commands.VkCmdClearColorImageConfig;
import tool.configs.vulkan.resources.VkCreateImageViewConfig;
import tool.configs.vulkan.swapchain.VkAcquireNextImageKHRConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkCmdClearColorImageGenerator extends VulkanCodeGenerator {
    private final String SUBRESOURCE_RANGE = "subresourceRange";
    private final String CLEAR_COLOR = "clearColor";

    public VkCmdClearColorImageGenerator(RandomStringGenerator randomStringGenerator,
                                         RandomNumberGanerator randomNumberGanerator,
                                         FreshMap freshMap,
                                         Coverage coverage,
                                         VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CMD_CLEAR_COLOR_IMAGE, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCmdClearColorImageConfig config = new VkCmdClearColorImageConfig();

        config.setId(generateConfigId());
        config.setSubresourceRange(SUBRESOURCE_RANGE +
                freshMap.getFreshId(SUBRESOURCE_RANGE));
        config.setClearColor(CLEAR_COLOR + freshMap.getFreshId(CLEAR_COLOR));

        // command buffer
        ArrayList<Config> commandBuffers =
                globalState.getConfig(VulkanState.VK_BEGIN_COMMAND_BUFFER);

        VkBeginCommandBufferConfig beginCommandBufferConfig =
                (VkBeginCommandBufferConfig)
                commandBuffers.get(commandBuffers.size() - 1);

        config.setCommandBuffer(beginCommandBufferConfig.getCommandBuffer());
        config.addDependency(beginCommandBufferConfig.getId());
        config.setBad(beginCommandBufferConfig.isBad());

        // image
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_ACQUIRE_NEXT_IMAGE_KHR);

        VkAcquireNextImageKHRConfig aquireNextImageConfig =
                (VkAcquireNextImageKHRConfig)
                configs.get(configs.size() - 1);

        config.setNextImage(aquireNextImageConfig.getNextImage());
        config.addDependency(aquireNextImageConfig.getId());
        config.setBad(aquireNextImageConfig.isBad());

        // swapchain buffers
        ArrayList<Config> imageviews =
                globalState.getConfig(VulkanState.VK_CREATE_IMAGE_VIEW);

        VkCreateImageViewConfig imageViewConfig =
                (VkCreateImageViewConfig)
                imageviews.get(randomNumberGanerator.randomNumber(imageviews.size()));

        config.setSwapchainBuffers(imageViewConfig.getSwapchainBuffers());
        config.addDependency(imageViewConfig.getId());
        config.setBad(imageViewConfig.isBad());

        globalState.addConfig(VulkanState.VK_CMD_CLEAR_COLOR, config);

        return config;
    }
}
