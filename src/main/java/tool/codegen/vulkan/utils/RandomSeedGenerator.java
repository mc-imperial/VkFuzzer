package tool.codegen.vulkan.utils;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.utils.RandomSeedConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.Random;

/**
 * Created by constantinos on 16/05/2016.
 */
public class RandomSeedGenerator extends VulkanCodeGenerator {
    private final long UINT32_MAX = ((long)2 << 31) - 1;

    public RandomSeedGenerator(RandomStringGenerator randomStringGenerator,
                               RandomNumberGanerator randomNumberGanerator,
                               FreshMap freshMap,
                               Coverage coverage,
                               VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.RANDOM_SEED, globalState);
    }

    @Override
    public Config generateConfig() {
        RandomSeedConfig config = new RandomSeedConfig();

        config.setId(generateConfigId());

        Random random = new Random();
        config.setRandomSeed(Math.abs(random.nextLong() % UINT32_MAX));

        globalState.addConfig(VulkanState.RANDOM_SEED, config);

        return config;
    }
}
