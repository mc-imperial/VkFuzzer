package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.resources.VkCreateImageConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

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
    private final String IMAGE = "image";
    private final String IMAGE_CREATE_INFO = "imageCreateInfo";
    private final String QUEUES = "NULL";
    private final String QUEUES_COUNT = "0";
    private final String SHARING_MODE = "VK_SHARING_MODE_EXCLUSIVE";

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

        globalState.addConfig(VulkanState.VK_CREATE_IMAGE, config);

        return config;
    }
}
