package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.resources.VkCreateFrameBufferConfig;
import tool.configs.vulkan.resources.VkCreateImageConfig;
import tool.configs.vulkan.resources.VkCreateRenderpassConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by kyriaki on 30/04/2016.
 */
public class VkCreateFrameBufferGenerator extends VulkanCodeGenerator {
    private final String FRAME_BUFFER = "framebuffer";
    private final String FRAME_BUFFER_CREATE_INFO = "framebufferCreateInfo";
    private final int ATTACHMENT_COUNT = 1;

    public VkCreateFrameBufferGenerator(RandomStringGenerator randomStringGenerator,
                                        RandomNumberGanerator randomNumberGanerator,
                                        FreshMap freshMap,
                                        Coverage coverage,
                                        VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_FRAMEBUFFER, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateFrameBufferConfig config = new VkCreateFrameBufferConfig();

        // random VkImage
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_IMAGE);

        config.setId(generateConfigId());

        if (!configs.isEmpty()) {
            // random renderpass
            ArrayList<Config> renderpasses =
                    globalState.getConfig(VulkanState.VK_CREATE_RENDERPASS);

            VkCreateRenderpassConfig renderpass =
                    (VkCreateRenderpassConfig)
                    renderpasses.get(randomNumberGanerator.randomNumber(renderpasses.size()));

            VkCreateImageConfig vkImage =
                    (VkCreateImageConfig)
                    configs.get(randomNumberGanerator.randomNumber(configs.size()));

            config.setDevice(vkImage.getDevice());
            config.setBad(vkImage.isBad());
            config.addDependency(vkImage.getId());
            config.setAttachments(vkImage.getImage());
            config.setHeight(vkImage.getHeight());
            config.setWidth(vkImage.getWidth());
            config.setResult(RESULT + freshMap.getFreshId(RESULT));
            config.setFramebuffer(FRAME_BUFFER + freshMap.getFreshId(FRAME_BUFFER));
            config.setFramebufferCreateInfo(FRAME_BUFFER_CREATE_INFO +
                    freshMap.getFreshId(FRAME_BUFFER_CREATE_INFO));
            config.setLayers(config.getLayers());
            config.setAttachmentsCount(ATTACHMENT_COUNT);
            config.setRenderpass(renderpass.getRenderpass());
            config.addDependency(renderpass.getId());
        } else {
            config.setBad(true);
        }

        return config;
    }
}
