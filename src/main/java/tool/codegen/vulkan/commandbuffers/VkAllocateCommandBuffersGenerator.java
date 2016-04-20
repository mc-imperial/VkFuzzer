package tool.codegen.vulkan.commandbuffers;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkAllocateCommandBuffersConfig;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 15/04/2016.
 */
public class VkAllocateCommandBuffersGenerator extends VulkanCodeGenerator {
    private final String CMD_BUFFER_ALLOC_INFO = "cmdBufferAllocInfo";
    private final String NEXT = "NULL";
    private final String BUFFERS = "buffers";
    private final String PRIMARY_BUFFER = "VK_COMMAND_BUFFER_LEVEL_PRIMARY";
    private final String SECONDARY_BUFFER = "VK_COMMAND_BUFFER_LEVEL_SECONDARY";
    private final int MAX_COUNT = 10;

    public VkAllocateCommandBuffersGenerator(RandomStringGenerator randomStringGenerator,
                                             RandomNumberGanerator randomNumberGanerator,
                                             FreshMap freshMap,
                                             Coverage coverage,
                                             VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_ALLOCATE_COMMAND_BUFFERS, globalState);
    }

    @Override
    public Config generateConfig() {
        VkAllocateCommandBuffersConfig config =
                new VkAllocateCommandBuffersConfig();

        config.setId(generateConfigId());
        config.setCmdBufferAllocInfo(CMD_BUFFER_ALLOC_INFO +
                freshMap.getFreshId(CMD_BUFFER_ALLOC_INFO));
        config.setNext(NEXT);
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setCount(randomNumberGanerator.randomNumber(MAX_COUNT));
        config.setBuffers(BUFFERS + freshMap.getFreshId(BUFFERS));

        // Use coverage to randomly choose a buffer type
        boolean bufferType = coverage.createBadConfig();
        if (bufferType) {
            config.setBufferType(SECONDARY_BUFFER);
        } else {
            config.setBufferType(PRIMARY_BUFFER);
        }

        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_COMMAND_POOL);

        VkCreateCommandPoolConfig randomCommandPool =
                (VkCreateCommandPoolConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setCommandPool(randomCommandPool.getCommandPool());
        config.setDevice(randomCommandPool.getRandomDevice());
        config.addDependency(randomCommandPool.getId());
        config.setBad(randomCommandPool.isBad());

        globalState.addConfig(VulkanState.VK_ALLOCATE_COMMAND_BUFFERS, config);

        return config;
    }
}
