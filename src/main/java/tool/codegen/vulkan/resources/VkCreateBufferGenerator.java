package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.resources.VkCreateBufferConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkCreateBufferGenerator extends VulkanCodeGenerator {
    private final String[] FLAGS =
    {
        "0",
        "VK_BUFFER_CREATE_SPARSE_BINDING_BIT",
        "VK_BUFFER_CREATE_SPARSE_RESIDENCY_BIT",
        "VK_BUFFER_CREATE_SPARSE_ALIASED_BIT"
    };
    private final String[] USAGE =
    {
        "VK_BUFFER_USAGE_TRANSFER_SRC_BIT",
        "VK_BUFFER_USAGE_TRANSFER_DST_BIT",
        "VK_BUFFER_USAGE_UNIFORM_TEXEL_BUFFER_BIT",
        "VK_BUFFER_USAGE_STORAGE_TEXEL_BUFFER_BIT",
        "VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT",
        "VK_BUFFER_USAGE_STORAGE_BUFFER_BIT",
        "VK_BUFFER_USAGE_INDEX_BUFFER_BIT",
        "VK_BUFFER_USAGE_VERTEX_BUFFER_BIT",
        "VK_BUFFER_USAGE_INDIRECT_BUFFER_BIT"
    };
    private final String SHARING_MODE = "VK_SHARING_MODE_EXCLUSIVE";
    private final String QUEUES = "NULL";
    private final String BUFFER = "buffer";
    private final String BUFFER_CREATE_INFO = "bufferCreateInfo";
    private final int FAMILY_COUNT = 1;
    private final int MAX_SIZE = 39;
    private final int FORMAT_SIZE = 32;
    private final int FUZZER_DATA_SIZE = 32;

    public VkCreateBufferGenerator(RandomStringGenerator randomStringGenerator,
                                   RandomNumberGanerator randomNumberGanerator,
                                   FreshMap freshMap,
                                   Coverage coverage,
                                   VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_BUFFER, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateBufferConfig config = new VkCreateBufferConfig();

        config.setId(generateConfigId());
        config.setpQueueFamilyIndices(QUEUES);
        config.setQueueFamilyIndexCount(FAMILY_COUNT);
        config.setSharingMode(SHARING_MODE);
        config.setSize((randomNumberGanerator.randomNumber(MAX_SIZE) + 1)
                * FORMAT_SIZE * FUZZER_DATA_SIZE);
        config.setBuffer(BUFFER + freshMap.getFreshId(BUFFER));
        config.setBufferCreateInfo(BUFFER_CREATE_INFO +
                freshMap.getFreshId(BUFFER_CREATE_INFO));
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        // random usage flags
        ArrayList<String> randomUsageFlags = new ArrayList<>();
        randomUsageFlags.add(USAGE[7]);
        config.setUsage(randomUsageFlags);

        // random creation flags
        ArrayList<String> randomCreationFlags = new ArrayList<>();
        randomCreationFlags.add(FLAGS[0]);
        config.setFlags(randomCreationFlags);

        // random device
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_COMMAND_POOL);

        VkCreateCommandPoolConfig cmdPool =
                (VkCreateCommandPoolConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setDevice(cmdPool.getRandomDevice());
        config.addDependency(cmdPool.getId());
        config.setBad(cmdPool.isBad());

        globalState.addConfig(VulkanState.VK_CREATE_BUFFER, config);

        return config;
    }
}
