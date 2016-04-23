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

        globalState.addConfig(VulkanState.VK_CREATE_IMAGE, config);

        return config;
    }
}
