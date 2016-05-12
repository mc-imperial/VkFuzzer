package tool.codegen.vulkan.commandbuffers;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.swapchain.InitSwapchainConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 11/04/2016.
 */
public class VkCreateCommandPoolGenerator extends VulkanCodeGenerator {
    private final String CMD_POOL_CREATE_INFO = "cmdPoolCreateInfo";
    private final String FLAGS = "VK_COMMAND_POOL_CREATE_RESET_COMMAND_BUFFER_BIT";
    private final String COMMAND_POOL = "commandPool";
    private final String RANDOM_INDEX = "randomIndex";
    private final String RANDOM_QUEUE_INDEX = "randomQueueIndex";
    private final String RANDOM_DEVICE = "randomDevice";
    private final String RANDOM_QUEUE_COUNT = "randomQueueCount";

    public VkCreateCommandPoolGenerator(RandomStringGenerator randomStringGenerator,
                                        RandomNumberGanerator randomNumberGanerator,
                                        FreshMap freshMap,
                                        Coverage coverage,
                                        VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.VK_CREATE_COMMAND_POOL, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateCommandPoolConfig config = new VkCreateCommandPoolConfig();

        config.setId(generateConfigId());
        config.setCmdPoolCreateInfo(CMD_POOL_CREATE_INFO +
                freshMap.getFreshId(CMD_POOL_CREATE_INFO));
        config.setFlags(FLAGS);
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setCommandPool(COMMAND_POOL +
                freshMap.getFreshId(COMMAND_POOL));
        config.setRandomIndex(RANDOM_INDEX + freshMap.getFreshId(RANDOM_INDEX));
        config.setRandomQueueIndex(RANDOM_QUEUE_INDEX +
                freshMap.getFreshId(RANDOM_QUEUE_INDEX));
        config.setRandomQueueCount(RANDOM_QUEUE_COUNT +
                freshMap.getFreshId(RANDOM_QUEUE_COUNT));

        // Select the logical device
        ArrayList<Config> initSwapchains =
                globalState.getConfig(VulkanState.INIT_SWAPCHAIN);

        InitSwapchainConfig swapchainConfig =
                (InitSwapchainConfig)initSwapchains.get(0);

        config.setRandomDevice(swapchainConfig.getDevice());
        config.setBad(swapchainConfig.isBad());
        config.addDependency(swapchainConfig.getId());

        globalState.addConfig(VulkanState.VK_CREATE_COMMAND_POOL, config);

        return config;
    }
}
