package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkAllocateCommandBuffersConfig;
import tool.configs.vulkan.resources.VkCreateImageViewConfig;
import tool.configs.vulkan.swapchain.InitSwapchainConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkCreateImageViewGenerator extends VulkanCodeGenerator {
    private final String[] ASPECT_MASKS =
    {
        "VK_IMAGE_ASPECT_COLOR_BIT",
        "VK_IMAGE_ASPECT_DEPTH_BIT",
        "VK_IMAGE_ASPECT_STENCIL_BIT",
        "VK_IMAGE_ASPECT_METADATA_BIT"
    };
    private final String[] VIEW_TYPE =
    {
        "VK_IMAGE_VIEW_TYPE_1D",
        "VK_IMAGE_VIEW_TYPE_2D",
        "VK_IMAGE_VIEW_TYPE_3D",
        "VK_IMAGE_VIEW_TYPE_CUBE",
        "VK_IMAGE_VIEW_TYPE_1D_ARRAY",
        "VK_IMAGE_VIEW_TYPE_2D_ARRAY",
        "VK_IMAGE_VIEW_TYPE_CUBE_ARRAY"
    };
    private final String SWAPCHAIN_BUFFERS = "swapchainbuffers";
    private final String COMMAND_BUFFER_BEGIN_INFO = "commandBufferBeginInfo";
    private final String IMAGE_VIEW = "imageView";
    private final String IMAGE_VIEW_CREATE_INFO = "imageViewCreateInfo";
    private final String SUB_RESOURCE_RANGE = "subResourceRange";
    private final String COMPONENTS = "components";
    private final int BASE_MIP_LEVEL = 0;
    private final String FORMAT = "VK_FORMAT_R8G8B8A8_UNORM";

    public VkCreateImageViewGenerator(RandomStringGenerator randomStringGenerator,
                                      RandomNumberGanerator randomNumberGanerator,
                                      FreshMap freshMap,
                                      Coverage coverage,
                                      VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_IMAGE_VIEW, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateImageViewConfig config = new VkCreateImageViewConfig();

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setImageView(IMAGE_VIEW + freshMap.getFreshId(IMAGE_VIEW));
        config.setVkImageViewCreateInfo(IMAGE_VIEW_CREATE_INFO +
                freshMap.getFreshId(IMAGE_VIEW_CREATE_INFO));
        config.setSubresourceRange(SUB_RESOURCE_RANGE +
                freshMap.getFreshId(SUB_RESOURCE_RANGE));

        // find swapchain config
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.INIT_SWAPCHAIN);

        InitSwapchainConfig swapchainConfig =
                (InitSwapchainConfig)configs.get(0);

        // find swapchain config
        ArrayList<Config> buffers =
                globalState.getConfig(VulkanState.VK_ALLOCATE_COMMAND_BUFFERS);

        VkAllocateCommandBuffersConfig buffer =
                (VkAllocateCommandBuffersConfig)buffers.get(0);

        config.setCommandBufferBeginInfo(COMMAND_BUFFER_BEGIN_INFO +
                freshMap.getFreshId(COMMAND_BUFFER_BEGIN_INFO));
        config.setCommandBuffer(buffer.getBuffers());
        config.setDevice(swapchainConfig.getDevice());
        config.setBaseMipLevel(BASE_MIP_LEVEL);
        config.setLevelCount(1);
        config.setLayerCount(1);
        config.setFormat(swapchainConfig.getFormat());
        config.setComponents(COMPONENTS + freshMap.getFreshId(COMPONENTS));
        config.setSwapchainBuffers(SWAPCHAIN_BUFFERS + freshMap.getFreshId(SWAPCHAIN_BUFFERS));
        config.setSwapchainImageCount(swapchainConfig.getSwapchainImageCount());
        config.setSwapchainImages(swapchainConfig.getSwapchainImages());

        // random creation flags
        ArrayList<String> aspect = new ArrayList<>();
        aspect.add(ASPECT_MASKS[0]);
        config.setAspectMask(aspect.get(0));

        // random creation flags
        config.setViewType(VIEW_TYPE[1]);

        config.setBad(swapchainConfig.isBad());
        config.addDependency(swapchainConfig.getId());

        globalState.addConfig(VulkanState.VK_CREATE_IMAGE_VIEW, config);

        return config;
    }
}
