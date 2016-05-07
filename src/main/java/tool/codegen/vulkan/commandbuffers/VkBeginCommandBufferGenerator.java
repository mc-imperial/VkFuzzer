package tool.codegen.vulkan.commandbuffers;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkAllocateCommandBuffersConfig;
import tool.configs.vulkan.commandbuffers.VkBeginCommandBufferConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkBeginCommandBufferGenerator extends VulkanCodeGenerator {
    private final String COMMAND_BUFFER_BEGIN_INFO = "commandBufferBeginInfo";
    private final String COMMAND_BUFFER = "commandBuffer";

    public VkBeginCommandBufferGenerator(RandomStringGenerator randomStringGenerator,
                                         RandomNumberGanerator randomNumberGanerator,
                                         FreshMap freshMap,
                                         Coverage coverage,
                                         VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_BEGIN_COMMAND_BUFFER, globalState);
    }

    @Override
    public Config generateConfig() {
        VkBeginCommandBufferConfig config = new VkBeginCommandBufferConfig();

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setCommandBufferBeginInfo(COMMAND_BUFFER_BEGIN_INFO +
                freshMap.getFreshId(COMMAND_BUFFER_BEGIN_INFO));
        config.setCommandBuffer(COMMAND_BUFFER + freshMap.getFreshId(COMMAND_BUFFER));

        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_ALLOCATE_COMMAND_BUFFERS);

        VkAllocateCommandBuffersConfig commandBuffersConfig =
                (VkAllocateCommandBuffersConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setCommandBuffers(commandBuffersConfig.getBuffers());
        config.addDependency(commandBuffersConfig.getId());
        config.setBad(commandBuffersConfig.isBad());

        globalState.addConfig(VulkanState.VK_BEGIN_COMMAND_BUFFER, config);

        return config;
    }
}
