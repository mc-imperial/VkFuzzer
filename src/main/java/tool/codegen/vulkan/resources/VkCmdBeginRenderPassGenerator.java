package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

/**
 * Created by constantinos on 08/05/2016.
 */
public class VkCmdBeginRenderPassGenerator extends VulkanCodeGenerator {
    public VkCmdBeginRenderPassGenerator(RandomStringGenerator randomStringGenerator, RandomNumberGanerator randomNumberGanerator, FreshMap freshMap, Coverage coverage, String template, VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage, template, globalState);
    }

    @Override
    public Config generateConfig() {
        return null;
    }
}
