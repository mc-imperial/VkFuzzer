package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.resources.VkCreateImageConfig;
import tool.configs.vulkan.resources.VkGetImageSubResourceLayoutConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkGetImageSubResourceLayoutGenerator extends VulkanCodeGenerator {
    private final String IMAGE_SUB_RESOURCE = "imageSubResource";
    private final String SUB_RESOURCE_LAYOUT = "subresourceLayout";
    private final String VALID_TILING = "VK_IMAGE_TILING_LINEAR";
    private final String ASPECT = "VK_IMAGE_ASPECT_COLOR_BIT";

    public VkGetImageSubResourceLayoutGenerator(RandomStringGenerator randomStringGenerator,
                                                RandomNumberGanerator randomNumberGanerator,
                                                FreshMap freshMap,
                                                Coverage coverage,
                                                VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_GET_IMAGE_SUBRESOURCE_LAYOUT, globalState);
    }

    @Override
    public Config generateConfig() {
        VkGetImageSubResourceLayoutConfig config =
                new VkGetImageSubResourceLayoutConfig();

        config.setId(generateConfigId());
        config.setImageSubresource(IMAGE_SUB_RESOURCE +
                freshMap.getFreshId(IMAGE_SUB_RESOURCE));
        config.setSubresourceLayout(SUB_RESOURCE_LAYOUT +
                freshMap.getFreshId(SUB_RESOURCE_LAYOUT));

        // random image
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_IMAGE);

        VkCreateImageConfig vkImage =
                (VkCreateImageConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setMipLevel(vkImage.getMipLevels());
        config.setArrayLayer(vkImage.getArrayLayers());
        config.setAspectMask(ASPECT);
        config.setImage(vkImage.getImage());
        config.setDevice(vkImage.getDevice());

        config.setBad(vkImage.isBad());
        if (!vkImage.getTiling().equals(VALID_TILING)) {
            config.setBad(true);
        }

        config.addDependency(vkImage.getId());

        globalState.addConfig(VulkanState.VK_GET_IMAGE_SUBRESOURCE_LAYOUT, config);

        return config;
    }
}
