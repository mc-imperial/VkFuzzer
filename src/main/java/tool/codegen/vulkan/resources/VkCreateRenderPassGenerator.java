package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.resources.VkCreateRenderpassConfig;
import tool.configs.vulkan.swapchain.InitSwapchainConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 28/04/2016.
 */
public class VkCreateRenderPassGenerator extends VulkanCodeGenerator {
    private final String ATTACHMENTS = "attachments";
    private final String COLOUR = "colour";
    private final String DEPTH = "depth";
    private final String SUBPASS = "subpass";
    private final String RENDERPASS_CREATE_INFO = "renderpassCreateInfo";
    private final String RENDERPASS = "renderpass";

    public VkCreateRenderPassGenerator(RandomStringGenerator randomStringGenerator,
                                       RandomNumberGanerator randomNumberGanerator,
                                       FreshMap freshMap,
                                       Coverage coverage,
                                       VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_RENDERPASS, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateRenderpassConfig config = new VkCreateRenderpassConfig();

        config.setId(generateConfigId());
        config.setAttachments(ATTACHMENTS + freshMap.getFreshId(ATTACHMENTS));
        config.setColour(COLOUR + freshMap.getFreshId(COLOUR));
        config.setDepth(DEPTH + freshMap.getFreshId(DEPTH));
        config.setSubpass(SUBPASS + freshMap.getFreshId(SUBPASS));
        config.setRenderpassCreateInfo(RENDERPASS_CREATE_INFO
                + freshMap.getFreshId(RENDERPASS_CREATE_INFO));
        config.setRenderpass(RENDERPASS + freshMap.getFreshId(RENDERPASS));
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        // random device
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_COMMAND_POOL);

        VkCreateCommandPoolConfig cmdPool =
                (VkCreateCommandPoolConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));


        // swapchain
        ArrayList<Config> swapchains =
                globalState.getConfig(VulkanState.INIT_SWAPCHAIN);

        InitSwapchainConfig initSwapchainConfig =
                (InitSwapchainConfig)
                swapchains.get(randomNumberGanerator.randomNumber(swapchains.size()));

        config.setFormat(initSwapchainConfig.getFormat());
        config.setBad(config.isBad() || initSwapchainConfig.isBad());
        config.addDependency(initSwapchainConfig.getId());

        config.setDevice(cmdPool.getRandomDevice());
        config.addDependency(cmdPool.getId());
        config.setBad(config.isBad() || cmdPool.isBad());

        globalState.addConfig(VulkanState.VK_CREATE_RENDERPASS, config);

        return config;
    }
}
