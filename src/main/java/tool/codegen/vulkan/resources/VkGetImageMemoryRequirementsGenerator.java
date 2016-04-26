package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.resources.VkCreateImageConfig;
import tool.configs.vulkan.resources.VkGetImageMemoryRequirementsConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkGetImageMemoryRequirementsGenerator extends VulkanCodeGenerator {
    private final String IMAGE_MEMORY_REQUIREMENTS = "imageMemoryRequirements";

    public VkGetImageMemoryRequirementsGenerator(RandomStringGenerator randomStringGenerator,
                                                 RandomNumberGanerator randomNumberGanerator,
                                                 FreshMap freshMap,
                                                 Coverage coverage,
                                                 VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_GET_IMAGE_MEMORY_REQUIREMENTS, globalState);
    }

    @Override
    public Config generateConfig() {
        VkGetImageMemoryRequirementsConfig config =
                new VkGetImageMemoryRequirementsConfig();

        config.setId(generateConfigId());
        config.setMemoryRequirements(IMAGE_MEMORY_REQUIREMENTS +
                freshMap.getFreshId(IMAGE_MEMORY_REQUIREMENTS));

        // random image
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_IMAGE);

        VkCreateImageConfig vkImage =
                (VkCreateImageConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setDevice(vkImage.getDevice());
        config.setImage(vkImage.getImage());
        config.setBad(vkImage.isBad());
        config.addDependency(vkImage.getId());

        globalState.addConfig(VulkanState.VK_GET_IMAGE_MEMORY_REQUIREMENTS, config);

        return config;
    }
}
