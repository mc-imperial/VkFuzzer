package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkBeginCommandBufferConfig;
import tool.configs.vulkan.resources.VkCmdBeginRenderPassConfig;
import tool.configs.vulkan.resources.VkCreateFrameBufferConfig;
import tool.configs.vulkan.resources.VkCreateRenderpassConfig;
import tool.configs.vulkan.swapchain.VkAcquireNextImageKHRConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 08/05/2016.
 */
public class VkCmdBeginRenderPassGenerator extends VulkanCodeGenerator {
    private final String CLEAR_VALUES = "clearValues";
    private final String RENDERPASS_BEGIN_INFO = "renderpassBeginInfo";

    public VkCmdBeginRenderPassGenerator(RandomStringGenerator randomStringGenerator,
                                         RandomNumberGanerator randomNumberGanerator,
                                         FreshMap freshMap,
                                         Coverage coverage,
                                         VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CMD_BEGIN_RENDERPASS, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCmdBeginRenderPassConfig config = new VkCmdBeginRenderPassConfig();

        config.setId(generateConfigId());
        config.setClearValues(CLEAR_VALUES + freshMap.getFreshId(CLEAR_VALUES));
        config.setRenderpassBeginInfo(RENDERPASS_BEGIN_INFO +
                freshMap.getFreshId(RENDERPASS_BEGIN_INFO));

        // image
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_ACQUIRE_NEXT_IMAGE_KHR);

        VkAcquireNextImageKHRConfig aquireNextImageConfig =
                (VkAcquireNextImageKHRConfig)
                        configs.get(configs.size() - 1);

        config.setNextImage(aquireNextImageConfig.getNextImage());
        config.setBad(aquireNextImageConfig.isBad());
        config.addDependency(aquireNextImageConfig.getId());

        // command buffer
        ArrayList<Config> commandBuffers =
                globalState.getConfig(VulkanState.VK_BEGIN_COMMAND_BUFFER);

        VkBeginCommandBufferConfig beginCommandBufferConfig =
                (VkBeginCommandBufferConfig)
                        commandBuffers.get(commandBuffers.size() - 1);

        config.setCommandBuffer(beginCommandBufferConfig.getCommandBuffer());
        config.addDependency(beginCommandBufferConfig.getId());
        config.setBad(config.isBad() || beginCommandBufferConfig.isBad());

        // framebuffer
        ArrayList<Config> framebuffers =
                globalState.getConfig(VulkanState.VK_CREATE_FRAMEBUFFER);

        VkCreateFrameBufferConfig framebuffer =
                (VkCreateFrameBufferConfig)
                framebuffers.get(framebuffers.size() - 1);

        config.setSwapchainExtent(framebuffer.getSwapchainExtent());
        config.setFramebuffers(framebuffer.getFramebuffers());
        config.addDependency(framebuffer.getId());
        config.setBad(config.isBad() || framebuffer.isBad());

        // renderpass
        ArrayList<Config> renderpasses =
                globalState.getConfig(VulkanState.VK_CREATE_RENDERPASS);

        VkCreateRenderpassConfig renderpass =
                (VkCreateRenderpassConfig)
                renderpasses.get(renderpasses.size() - 1);

        config.setRenderpass(renderpass.getRenderpass());
        config.addDependency(renderpass.getId());
        config.setBad(renderpass.isBad() || renderpass.isBad());

        globalState.addConfig(VulkanState.VK_CMD_BEGIN_RENDERPASS, config);

        return config;
    }
}
