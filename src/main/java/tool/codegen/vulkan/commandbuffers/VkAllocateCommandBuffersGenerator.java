package tool.codegen.vulkan.commandbuffers;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkAllocateCommandBuffersConfig;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

/**
 * Created by constantinos on 15/04/2016.
 */
public class VkAllocateCommandBuffersGenerator extends VulkanCodeGenerator {
    public VkAllocateCommandBuffersGenerator(RandomStringGenerator randomStringGenerator,
                                             RandomNumberGanerator randomNumberGanerator,
                                             FreshMap freshMap,
                                             Coverage coverage,
                                             VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_ALLOCATE_COMMAND_BUFFERS, globalState);
    }

    @Override
    public Config generateConfig() {
        VkAllocateCommandBuffersConfig config =
                new VkAllocateCommandBuffersConfig();

        config.setId(generateConfigId());

        return config;
    }
}
