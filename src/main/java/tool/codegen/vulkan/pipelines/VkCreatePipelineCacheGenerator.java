package tool.codegen.vulkan.pipelines;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.pipelines.VkCreatePipelineCacheConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by kyriaki on 01/05/2016.
 */
public class VkCreatePipelineCacheGenerator extends VulkanCodeGenerator {
    private final String PIPELINE_CACHE = "pipelineCache";
    private final String PIPELINE_CACHE_CREATE_INFO = "pipelineCacheCreateInfo";

    public VkCreatePipelineCacheGenerator(RandomStringGenerator randomStringGenerator,
                                          RandomNumberGanerator randomNumberGanerator,
                                          FreshMap freshMap,
                                          Coverage coverage,
                                          VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_PIPELINE_CACHE, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreatePipelineCacheConfig config =
                new VkCreatePipelineCacheConfig();

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setPipelineCache(PIPELINE_CACHE +
                freshMap.getFreshId(PIPELINE_CACHE));
        config.setPipelineCacheCreateInfo(PIPELINE_CACHE_CREATE_INFO +
                freshMap.getFreshId(PIPELINE_CACHE_CREATE_INFO));

        // random device
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_COMMAND_POOL);

        VkCreateCommandPoolConfig cmdPool =
                (VkCreateCommandPoolConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setDevice(cmdPool.getRandomDevice());
        config.setBad(cmdPool.isBad());

        globalState.addConfig(VulkanState.VK_CREATE_PIPELINE_CACHE, config);

        return config;
    }
}
