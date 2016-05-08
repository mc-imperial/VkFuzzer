package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkBeginCommandBufferConfig;
import tool.configs.vulkan.resources.VkCmdEndRenderPassConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 08/05/2016.
 */
public class VkCmdEndRenderPassGenerator extends VulkanCodeGenerator {
    public VkCmdEndRenderPassGenerator(RandomStringGenerator randomStringGenerator,
                                       RandomNumberGanerator randomNumberGanerator,
                                       FreshMap freshMap,
                                       Coverage coverage,
                                       VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CMD_END_RENDERPASS, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCmdEndRenderPassConfig config = new VkCmdEndRenderPassConfig();

        config.setId(generateConfigId());

        // command buffer
        ArrayList<Config> commandBuffers =
                globalState.getConfig(VulkanState.VK_BEGIN_COMMAND_BUFFER);

        VkBeginCommandBufferConfig beginCommandBufferConfig =
                (VkBeginCommandBufferConfig)
                        commandBuffers.get(commandBuffers.size() - 1);

        config.setCommandBuffer(beginCommandBufferConfig.getCommandBuffer());
        config.addDependency(beginCommandBufferConfig.getId());
        config.setBad(beginCommandBufferConfig.isBad());

        globalState.addConfig(VulkanState.VK_CMD_END_RENDERPASS, config);

        return config;
    }
}
