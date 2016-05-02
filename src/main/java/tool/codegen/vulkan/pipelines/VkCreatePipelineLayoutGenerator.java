package tool.codegen.vulkan.pipelines;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.pipelines.VkCreatePipelineLayoutConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by kyriaki on 01/05/2016.
 */
public class VkCreatePipelineLayoutGenerator extends VulkanCodeGenerator {
    private final String PIPELINE_LAYOUT = "pipelineLayout";
    private final String PIPELINE_LAYOUT_CREATE_INFO = "pipelineLayoutCreateInfo";

    public VkCreatePipelineLayoutGenerator(RandomStringGenerator randomStringGenerator,
                                           RandomNumberGanerator randomNumberGanerator,
                                           FreshMap freshMap,
                                           Coverage coverage,
                                           VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_PIPELINE_LAYOUT, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreatePipelineLayoutConfig config =
                new VkCreatePipelineLayoutConfig();

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setPipelineLayout(PIPELINE_LAYOUT +
                freshMap.getFreshId(PIPELINE_LAYOUT));
        config.setPipelineLayoutCreateInfo(PIPELINE_LAYOUT_CREATE_INFO
                + freshMap.getFreshId(PIPELINE_LAYOUT_CREATE_INFO));

        // random device
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_COMMAND_POOL);

        VkCreateCommandPoolConfig cmdPool =
                (VkCreateCommandPoolConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setDevice(cmdPool.getRandomDevice());
        config.setBad(cmdPool.isBad());

        globalState.addConfig(VulkanState.VK_CREATE_PIPELINE_LAYOUT, config);

        return config;
    }
}
