package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.resources.VkCreateImageConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkCreateImageGenerator extends VulkanCodeGenerator {
    private final String[] FLAGS =
    {
        "VK_IMAGE_CREATE_SPARSE_BINDING_BIT",
        "VK_IMAGE_CREATE_SPARSE_RESIDENCY_BIT",
        "VK_IMAGE_CREATE_SPARSE_ALIASED_BIT",
        "VK_IMAGE_CREATE_MUTABLE_FORMAT_BIT",
        "VK_IMAGE_CREATE_CUBE_COMPATIBLE_BIT"
    };
    private final String[] USAGE =
    {
        "VK_IMAGE_USAGE_TRANSFER_SRC_BIT",
        "VK_IMAGE_USAGE_TRANSFER_DST_BIT",
        "VK_IMAGE_USAGE_SAMPLED_BIT",
        "VK_IMAGE_USAGE_STORAGE_BIT",
        "VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT",
        "VK_IMAGE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT",
        "VK_IMAGE_USAGE_TRANSIENT_ATTACHMENT_BIT",
        "VK_IMAGE_USAGE_INPUT_ATTACHMENT_BIT"
    };
    private final String[] TYPES =
    {
        "VK_IMAGE_TYPE_1D",
        "VK_IMAGE_TYPE_2D",
        "VK_IMAGE_TYPE_3D",
    };
    private final String[] TILING =
    {
        "VK_IMAGE_TILING_OPTIMAL",
        "VK_IMAGE_TILING_LINEAR",
    };
    private final String[] LAYOUTS =
    {
            "VK_IMAGE_LAYOUT_UNDEFINED",
            "VK_IMAGE_LAYOUT_PREINITIALIZED",
    };
    private final String[] SAMPLING =
    {
            "VK_SAMPLE_COUNT_1_BIT",
            "VK_SAMPLE_COUNT_2_BIT",
            "VK_SAMPLE_COUNT_4_BIT",
            "VK_SAMPLE_COUNT_8_BIT",
            "VK_SAMPLE_COUNT_16_BIT",
            "VK_SAMPLE_COUNT_32_BIT",
            "VK_SAMPLE_COUNT_64_BIT"
    };
    private final String IMAGE = "image";
    private final String IMAGE_CREATE_INFO = "imageCreateInfo";
    private final String QUEUES = "NULL";
    private final String QUEUES_COUNT = "0";
    private final String SHARING_MODE = "VK_SHARING_MODE_EXCLUSIVE";
    private final String FORMAT = "VK_FORMAT_R8G8B8A8_UNORM";
    private final String EXTENT = "extent";
    private final int MAX_WITDTH = 1080;
    private final int MAX_HEIGHT = 1920;
    private final int DEPTH = 1;
    private final int MIP_LEVELS = 1;
    private final int ARRAY_LEVELS = 1;

    public VkCreateImageGenerator(RandomStringGenerator randomStringGenerator,
                                  RandomNumberGanerator randomNumberGanerator,
                                  FreshMap freshMap,
                                  Coverage coverage,
                                  VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_IMAGE, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateImageConfig config = new VkCreateImageConfig();

        config.setId(generateConfigId());
        config.setImage(IMAGE + freshMap.getFreshId(IMAGE));
        config.setVkImageCreateInfo(IMAGE_CREATE_INFO +
                freshMap.getFreshId(IMAGE_CREATE_INFO));
        config.setpQueueFamilyIndices(QUEUES);
        config.setQueueFamilyIndexCount(QUEUES_COUNT);
        config.setSharingMode(SHARING_MODE);
        config.setExtent(EXTENT + freshMap.getFreshId(EXTENT));
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        // Random extend values
        config.setWidth(randomNumberGanerator.randomNumber(MAX_WITDTH));
        config.setHeight(randomNumberGanerator.randomNumber(MAX_HEIGHT));
        config.setDepth(DEPTH);

        // random device
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_COMMAND_POOL);

        VkCreateCommandPoolConfig cmdPool =
                (VkCreateCommandPoolConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setDevice(cmdPool.getRandomDevice());
        config.addDependency(cmdPool.getId());
        config.setBad(cmdPool.isBad());

        // random usage flags
        ArrayList<String> randomUsageFlags = new ArrayList<>();
        randomUsageFlags.add(USAGE[0]);
        config.setUsage(randomUsageFlags);

        // random creation flags
        ArrayList<String> randomCreationFlags = new ArrayList<>();
        randomCreationFlags.add("0");
        config.setFlags(randomCreationFlags);

        // random tiling
        config.setTiling(TILING[1]);

        // random layout
        ArrayList<String> layout = new ArrayList<>(Arrays.asList(LAYOUTS));
        Collections.shuffle(layout);
        config.setInitialLayout(layout.get(0));

        config.setSamples(SAMPLING[0]);
        config.setImageType(TYPES[1]);
        config.setArrayLayers(ARRAY_LEVELS);
        config.setFormat(FORMAT);
        config.setMipLevels(MIP_LEVELS);

        globalState.addConfig(VulkanState.VK_CREATE_IMAGE, config);

        return config;
    }
}
