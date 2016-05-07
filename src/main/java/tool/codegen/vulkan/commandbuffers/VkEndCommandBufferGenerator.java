package tool.codegen.vulkan.commandbuffers;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkAllocateCommandBuffersConfig;
import tool.configs.vulkan.commandbuffers.VkBeginCommandBufferConfig;
import tool.configs.vulkan.commandbuffers.VkEndCommandBufferConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkEndCommandBufferGenerator extends VulkanCodeGenerator {
    public VkEndCommandBufferGenerator(RandomStringGenerator randomStringGenerator,
                                       RandomNumberGanerator randomNumberGanerator,
                                       FreshMap freshMap,
                                       Coverage coverage,
                                       VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_END_COMMAND_BUFFER, globalState);
    }

    @Override
    public Config generateConfig() {
        VkEndCommandBufferConfig config = new VkEndCommandBufferConfig();

        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_BEGIN_COMMAND_BUFFER);

        VkBeginCommandBufferConfig beginCommandBufferConfig =
                (VkBeginCommandBufferConfig)
                configs.get(configs.size() - 1);

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setCommandBuffer(beginCommandBufferConfig.getCommandBuffer());
        config.setBad(beginCommandBufferConfig.isBad());

        globalState.addConfig(VulkanState.VK_END_COMMAND_BUFFER, config);

        return config;
    }
}
