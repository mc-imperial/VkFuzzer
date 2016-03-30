package tool.codegen.vulkan.instance;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

/**
 * Created by constantinos on 27/03/2016.
 */
public class VkCreateInstanceGenerator extends VulkanCodeGenerator {
    public VkCreateInstanceGenerator(final RandomStringGenerator randomStringGenerator,
                                     final RandomNumberGanerator randomNumberGanerator,
                                     final FreshMap freshMap,
                                     final Coverage coverage,
                                     final VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.VK_CREATE_INSTANCE, globalState);
    }

    @Override
    public Config generateConfig() {
        return new Config();
    }
}
