package tool.codegen.vulkan.commands;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkBeginCommandBufferConfig;
import tool.configs.vulkan.commands.DrawBuffersConfig;
import tool.configs.vulkan.memory.PopulateVertexBufferConfig;
import tool.configs.vulkan.pipelines.VkCreateGraphicsPipelinesConfig;
import tool.configs.vulkan.swapchain.InitSwapchainConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 09/05/2016.
 */
public class DrawBuffersGenerator extends VulkanCodeGenerator {
    private final String OFFSETS = "offsets";
    private final String VIEWPORT = "viewport";
    private final String SCISSOR = "scissor";

    public DrawBuffersGenerator(RandomStringGenerator randomStringGenerator,
                                RandomNumberGanerator randomNumberGanerator,
                                FreshMap freshMap,
                                Coverage coverage,
                                VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.DRAW_BUFFERS, globalState);
    }

    @Override
    public Config generateConfig() {
        DrawBuffersConfig config = new DrawBuffersConfig();

        config.setId(generateConfigId());
        config.setOffsets(OFFSETS + freshMap.getFreshId(OFFSETS));
        config.setViewport(VIEWPORT + freshMap.getFreshId(VIEWPORT));
        config.setScissor(SCISSOR + freshMap.getFreshId(SCISSOR));

        // command buffer
        ArrayList<Config> commandBuffers =
                globalState.getConfig(VulkanState.VK_BEGIN_COMMAND_BUFFER);

        VkBeginCommandBufferConfig beginCommandBufferConfig =
                (VkBeginCommandBufferConfig)
                        commandBuffers.get(commandBuffers.size() - 1);

        config.setCommandBuffer(beginCommandBufferConfig.getCommandBuffer());
        config.addDependency(beginCommandBufferConfig.getId());
        config.setBad(config.isBad() || beginCommandBufferConfig.isBad());

        // swapchain
        ArrayList<Config> swapchains =
                globalState.getConfig(VulkanState.INIT_SWAPCHAIN);

        InitSwapchainConfig initSwapchainConfig =
                (InitSwapchainConfig)
                        swapchains.get(randomNumberGanerator.randomNumber(swapchains.size()));

        config.setSwapchainExtent(initSwapchainConfig.getSwapchainExtent());
        config.setBad(config.isBad() || initSwapchainConfig.isBad());
        config.addDependency(initSwapchainConfig.getId());

        // Vertex buffer
        ArrayList<Config> populateVertexBufferConfigs =
                globalState.getConfig(VulkanState.POPULATE_VERTEX_BUFFER);

        PopulateVertexBufferConfig populateVertexBufferConfig =
                (PopulateVertexBufferConfig)
                populateVertexBufferConfigs.get(randomNumberGanerator.randomNumber(populateVertexBufferConfigs.size()));

        config.setDataPointer(populateVertexBufferConfig.getDataPointer());
        config.setRandomData(populateVertexBufferConfig.getRandomData());
        config.setVertexbuffer(populateVertexBufferConfig.getVertexbuffer());
        config.setBad(config.isBad() || populateVertexBufferConfig.isBad());
        config.addDependency(populateVertexBufferConfig.getId());

        // Pipeline
        ArrayList<Config> pipelines =
                globalState.getConfig(VulkanState.VK_CREATE_GRAPHICS_PIPELINES);

        if (!pipelines.isEmpty()) {
            VkCreateGraphicsPipelinesConfig pipeline =
                    (VkCreateGraphicsPipelinesConfig)
                            pipelines.get(randomNumberGanerator.randomNumber(pipelines.size()));

            config.setPipeline(pipeline.getGraphicsPipeline());
            config.setBad(config.isBad() || initSwapchainConfig.isBad());
            config.addDependency(initSwapchainConfig.getId());

        } else {
            config.setBad(true);
        }

        globalState.addConfig(VulkanState.DRAW_BUFFERS, config);
        return config;
    }
}
