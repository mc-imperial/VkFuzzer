package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.resources.VkCreateBufferConfig;
import tool.configs.vulkan.resources.VkGetBufferMemoryRequirementsConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkGetBufferMemoryRequirementsGenerator extends VulkanCodeGenerator {
    private final String BUFFER_REQUIREMENTS = "bufferMemoryRequirements";

    public VkGetBufferMemoryRequirementsGenerator(RandomStringGenerator randomStringGenerator,
                                                  RandomNumberGanerator randomNumberGanerator,
                                                  FreshMap freshMap,
                                                  Coverage coverage,
                                                  VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_GET_BUFFER_MEMORY_REQUIREMENTS, globalState);
    }

    @Override
    public Config generateConfig() {
        VkGetBufferMemoryRequirementsConfig config =
                new VkGetBufferMemoryRequirementsConfig();

        config.setId(generateConfigId());
        config.setMemoryRequirements(BUFFER_REQUIREMENTS);

        // random device
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_BUFFER);

        VkCreateBufferConfig buffer =
                (VkCreateBufferConfig)
                        configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setBuffer(buffer.getBuffer());
        config.setDevice(buffer.getDevice());
        config.setBad(buffer.isBad());

        globalState.addConfig(VulkanState.VK_GET_BUFFER_MEMORY_REQUIREMENTS, config);

        return config;
    }
}
