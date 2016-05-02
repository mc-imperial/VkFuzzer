package tool.codegen.vulkan.pipelines;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.pipelines.VkCreatePipelineCacheConfig;
import tool.configs.vulkan.pipelines.VkGetPipelineCacheDataConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by kyriaki on 01/05/2016.
 */
public class VkGetPipelineCacheDataGenerator extends VulkanCodeGenerator {
    private final String SIZE = "size";
    private final String PIPELINE_DATA = "pipelineData";

    public VkGetPipelineCacheDataGenerator(RandomStringGenerator randomStringGenerator,
                                           RandomNumberGanerator randomNumberGanerator,
                                           FreshMap freshMap,
                                           Coverage coverage,
                                           VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_GET_PIPELINE_CACHE_DATA, globalState);
    }

    @Override
    public Config generateConfig() {
        VkGetPipelineCacheDataConfig config =
                new VkGetPipelineCacheDataConfig();

        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setId(generateConfigId());

        // random device
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_PIPELINE_CACHE);

        VkCreatePipelineCacheConfig cache =
                (VkCreatePipelineCacheConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setDevice(cache.getDevice());
        config.setBad(cache.isBad());
        config.setPipelineCache(cache.getPipelineCache());
        config.setSize(SIZE + freshMap.getFreshId(SIZE));
        config.setPipelineCacheData(PIPELINE_DATA +
                freshMap.getFreshId(PIPELINE_DATA));

        globalState.addConfig(VulkanState.VK_GET_PIPELINE_CACHE_DATA, config);

        return config;
    }
}
