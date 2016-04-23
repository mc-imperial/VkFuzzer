package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.resources.VkGetImageSubResourceLayoutConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkGetImageSubResourceLayout extends VulkanCodeGenerator {
    public VkGetImageSubResourceLayout(RandomStringGenerator randomStringGenerator,
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

        globalState.addConfig(VulkanState.VK_GET_IMAGE_SUBRESOURCE_LAYOUT, config);

        return config;
    }
}
