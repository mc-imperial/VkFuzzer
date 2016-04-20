package tool.codegen.vulkan.synchronisation;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.synchronisation.VkCreateFenceConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 19/04/2016.
 */
public class VkCreateFenceGenerator extends VulkanCodeGenerator {
    private final String RESULT = "result";
    private final String FENCE_CREATE_INFO = "fenceCreateInfo";
    private final String FENCE = "fence";
    private final String FLAGS = "VK_FENCE_CREATE_SIGNALED_BIT";
    private final String NO_FLAGS = "0";

    public VkCreateFenceGenerator(RandomStringGenerator randomStringGenerator,
                                  RandomNumberGanerator randomNumberGanerator,
                                  FreshMap freshMap,
                                  Coverage coverage,
                                  VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_FENCE, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateFenceConfig config = new VkCreateFenceConfig();

        // Generate random names
        config.setId(generateConfigId());
        config.setFence(FENCE + freshMap.getFreshId(FENCE));
        config.setFenceCreateInfo(FENCE_CREATE_INFO +
                freshMap.getFreshId(FENCE_CREATE_INFO));
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_COMMAND_POOL);

        VkCreateCommandPoolConfig cmdPool =
                (VkCreateCommandPoolConfig)
                        configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setDevice(cmdPool.getRandomDevice());
        config.setBad(cmdPool.isBad());
        config.setFlags(coverage.createBadConfig() ? NO_FLAGS : FLAGS);

        config.addDependency(cmdPool.getId());
        globalState.addConfig(VulkanState.VK_CREATE_FENCE, config);


        return config;
    }
}
