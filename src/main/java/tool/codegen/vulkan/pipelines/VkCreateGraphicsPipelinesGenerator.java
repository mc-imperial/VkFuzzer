package tool.codegen.vulkan.pipelines;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.pipelines.VkCreateGraphicsPipelinesConfig;
import tool.configs.vulkan.pipelines.VkCreatePipelineCacheConfig;
import tool.configs.vulkan.pipelines.VkCreatePipelineLayoutConfig;
import tool.configs.vulkan.resources.VkCreateRenderpassConfig;
import tool.configs.vulkan.shaders.VkCreateShaderModuleConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by kyriaki on 01/05/2016.
 */
public class VkCreateGraphicsPipelinesGenerator extends VulkanCodeGenerator {
    private final String DYNAMIC_STATES_ENABLES = "dynamicStatesEnables";
    private final String DYNAMIC_STATE_CREATE_INFO = "dynamicStateCreateInfo";
    private final String VERTEX_INPUT_STATE_CREATE_INFO= "vertexInputStateCreateInfo";
    private final String INPUT_ASSEMBLY_STATE_CREATE_INFO= "inputAssemblyStateCreateInfo";
    private final String RASTERISATION_STATE_CREATE_INFO = "rasterisationStateCreateInfo";
    private final String ATTACHMENT_STATE = "attachmentState";
    private final String COLOUR_BLEND_STATE_CREATE_INFO = "colourBlendStateCreateInfo";
    private final String VIEWPORT_CREATE_INFO= "viewportStateCreateInfo";
    private final String STENCIL_STATE_CREATE_INFO = "stencilStateCreateInfo";
    private final String MULTISAMPLE_STATE_CREATE_INFO = "multisampleStateCreateInfo";
    private final String SHADER_STAGES = "shaderStages";
    private final String PIPELINE_CREATE_INFO = "pipelineCreateInfo";
    private final String GRAPHICS_PIPELINE = "graphicsPipeline";
    private final String VERTEX_ATTRIBUTES = "vertexAttributes";
    private final String VERTEX_BINDINGS = "vertexBindings";

    public VkCreateGraphicsPipelinesGenerator(RandomStringGenerator randomStringGenerator,
                                              RandomNumberGanerator randomNumberGanerator,
                                              FreshMap freshMap,
                                              Coverage coverage,
                                              VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_GRAPHICS_PIPELINES, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateGraphicsPipelinesConfig config =
                new VkCreateGraphicsPipelinesConfig();

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setAttachmentState(ATTACHMENT_STATE +
                freshMap.getFreshId(ATTACHMENT_STATE));
        config.setInputAssemblyStateCreateInfo(INPUT_ASSEMBLY_STATE_CREATE_INFO +
                freshMap.getFreshId(INPUT_ASSEMBLY_STATE_CREATE_INFO));
        config.setRasterisationStateCreateInfo(RASTERISATION_STATE_CREATE_INFO +
                freshMap.getFreshId(RASTERISATION_STATE_CREATE_INFO));
        config.setStencilStateCreateInfo(STENCIL_STATE_CREATE_INFO +
                freshMap.getFreshId(STENCIL_STATE_CREATE_INFO));
        config.setVertexInputStateCreateInfo(VERTEX_INPUT_STATE_CREATE_INFO +
                freshMap.getFreshId(VERTEX_INPUT_STATE_CREATE_INFO));
        config.setDynamicStateCreateInfo(DYNAMIC_STATE_CREATE_INFO +
                freshMap.getFreshId(DYNAMIC_STATE_CREATE_INFO));
        config.setDynamicStatesEnables(DYNAMIC_STATES_ENABLES +
                freshMap.getFreshId(DYNAMIC_STATES_ENABLES));
        config.setViewportStateCreateInfo(VIEWPORT_CREATE_INFO +
                freshMap.getFreshId(VIEWPORT_CREATE_INFO));
        config.setColourBlendStateCreateInfo(COLOUR_BLEND_STATE_CREATE_INFO +
                freshMap.getFreshId(COLOUR_BLEND_STATE_CREATE_INFO));
        config.setMultisampleStateCreateInfo(MULTISAMPLE_STATE_CREATE_INFO +
                freshMap.getFreshId(MULTISAMPLE_STATE_CREATE_INFO));
        config.setShaderStages(SHADER_STAGES +
                freshMap.getFreshId(SHADER_STAGES));
        config.setPipelineCreateInfo(PIPELINE_CREATE_INFO +
                freshMap.getFreshId(PIPELINE_CREATE_INFO));
        config.setGraphicsPipeline(GRAPHICS_PIPELINE +
                freshMap.getFreshId(GRAPHICS_PIPELINE));
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setVertexAttributes(VERTEX_ATTRIBUTES +
                freshMap.getFreshId(VERTEX_ATTRIBUTES));
        config.setVertexBindings(VERTEX_BINDINGS +
                freshMap.getFreshId(VERTEX_BINDINGS));


        // random shaders
        ArrayList<Config> shaderConfigs =
                globalState.getConfig(VulkanState.VK_CREATE_SHADER_MODULE);

        VkCreateShaderModuleConfig shaders =
                (VkCreateShaderModuleConfig)
                        shaderConfigs.get(randomNumberGanerator.randomNumber(shaderConfigs.size()));

        // random layout
        ArrayList<Config> layoutConfigs =
                globalState.getConfig(VulkanState.VK_CREATE_PIPELINE_LAYOUT);

        VkCreatePipelineLayoutConfig layout =
                (VkCreatePipelineLayoutConfig)
                layoutConfigs.get(randomNumberGanerator.randomNumber(layoutConfigs.size()));

        // random renderpass
        ArrayList<Config> renderpassConfigs =
                globalState.getConfig(VulkanState.VK_CREATE_RENDERPASS);

        VkCreateRenderpassConfig renderpass =
                (VkCreateRenderpassConfig)
                renderpassConfigs.get(randomNumberGanerator.randomNumber(renderpassConfigs.size()));

        // random pipeline cache
        ArrayList<Config> pipelineCacheConfigs =
                globalState.getConfig(VulkanState.VK_CREATE_PIPELINE_CACHE);

        VkCreatePipelineCacheConfig pipelineCache =
                (VkCreatePipelineCacheConfig)
                pipelineCacheConfigs.get(randomNumberGanerator.randomNumber(pipelineCacheConfigs.size()));

        config.setFragmentShader(shaders.getFragmentPipelineShaderStageCreateInfo());
        config.setVertexShader(shaders.getVertexPipelineShaderStageCreateInfo());
        config.setDevice(shaders.getDevice());
        config.setPipelineLayout(layout.getPipelineLayout());
        config.setRenderpass(renderpass.getRenderpass());
        config.setPipelineCache(pipelineCache.getPipelineCache());
        config.setBad(shaders.isBad() || layout.isBad()
                || renderpass.isBad() || pipelineCache.isBad());
        config.addDependency(shaders.getId());
        config.addDependency(layout.getId());
        config.addDependency(renderpass.getId());
        config.addDependency(pipelineCache.getId());

        globalState.addConfig(VulkanState.VK_CREATE_GRAPHICS_PIPELINES, config);

        return config;
    }
}
