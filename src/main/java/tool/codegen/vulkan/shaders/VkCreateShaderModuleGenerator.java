package tool.codegen.vulkan.shaders;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.shaders.VkCreateShaderModuleConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 27/04/2016.
 */
public class VkCreateShaderModuleGenerator extends VulkanCodeGenerator {
    private final String VERTEX_STREAM = "vert";
    private final String FRAGMENT_STREAM = "frag";
    private final String VERTEX_CONTENTS = "vertContents";
    private final String FRAGMENT_CONTENTS = "fragContents";
    private final String VERTEX_PIPELINE_SHADER_STAGE_CREATE_INFO =
            "vertexPipelineShaderStageCreateInfo";
    private final String FRAGMENT_PIPELINE_SHADER_STAGE_CREATE_INFO =
            "fragmentPipelineShaderStageCreateInfo";
    private final String VERTEX_SHADER_MODULE =
            "vertexShaderModule";
    private final String FRAGMENT_SHADER_MODULE =
            "fragmentShaderModule";
    private final String VERTEX_SHADER_CREATE_INFO =
            "vertexShaderCreateInfo";
    private final String FRAGMENT_SHADER_CREATE_INFO =
            "fragmentShaderCreateInfo";
    private final String LENGTH = "length";

    public VkCreateShaderModuleGenerator(RandomStringGenerator randomStringGenerator,
                                         RandomNumberGanerator randomNumberGanerator,
                                         FreshMap freshMap,
                                         Coverage coverage,
                                         VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_SHADER_MODULE, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateShaderModuleConfig config = new VkCreateShaderModuleConfig();

        // Fragments shader
        config.setId(generateConfigId());
        config.setFrag(FRAGMENT_STREAM + freshMap.getFreshId(FRAGMENT_STREAM));
        config.setFragContents(FRAGMENT_CONTENTS + freshMap.getFreshId(FRAGMENT_CONTENTS));
        config.setFragmentShaderModule(FRAGMENT_SHADER_MODULE + freshMap.getFreshId(FRAGMENT_SHADER_MODULE));
        config.setFragmentPipelineShaderStageCreateInfo(FRAGMENT_PIPELINE_SHADER_STAGE_CREATE_INFO
                + freshMap.getFreshId(FRAGMENT_PIPELINE_SHADER_STAGE_CREATE_INFO));
        config.setFragmentModuleCreateInfo(FRAGMENT_SHADER_CREATE_INFO +
                freshMap.getFreshId(FRAGMENT_SHADER_CREATE_INFO));

        // Vertex shader
        config.setId(generateConfigId());
        config.setVert(VERTEX_STREAM + freshMap.getFreshId(VERTEX_STREAM));
        config.setVertContents(VERTEX_CONTENTS + freshMap.getFreshId(VERTEX_CONTENTS));
        config.setVertexShaderModule(VERTEX_SHADER_MODULE + freshMap.getFreshId(VERTEX_SHADER_MODULE));
        config.setVertexPipelineShaderStageCreateInfo(VERTEX_PIPELINE_SHADER_STAGE_CREATE_INFO
                + freshMap.getFreshId(VERTEX_PIPELINE_SHADER_STAGE_CREATE_INFO));
        config.setVertexModuleCreateInfo(VERTEX_SHADER_CREATE_INFO +
                freshMap.getFreshId(VERTEX_SHADER_CREATE_INFO));

        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setLength(LENGTH + freshMap.getFreshId(LENGTH));

        // Random device
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_COMMAND_POOL);

        VkCreateCommandPoolConfig cmdPool =
                (VkCreateCommandPoolConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.addDependency(cmdPool.getId());
        config.setDevice(cmdPool.getRandomDevice());
        config.setBad(cmdPool.isBad());

        globalState.addConfig(VulkanState.VK_CREATE_SHADER_MODULE, config);

        return config;
    }
}
