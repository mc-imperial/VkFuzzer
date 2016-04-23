package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.resources.VkCreateImageViewConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkCreateImageViewGenerator extends VulkanCodeGenerator {
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

        globalState.addConfig(VulkanState.VK_CREATE_IMAGE_VIEW, config);

        return config;
    }
}
