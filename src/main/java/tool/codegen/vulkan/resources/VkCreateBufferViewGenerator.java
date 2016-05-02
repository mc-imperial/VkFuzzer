package tool.codegen.vulkan.resources;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.resources.VkCreateBufferConfig;
import tool.configs.vulkan.resources.VkCreateBufferViewConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkCreateBufferViewGenerator extends VulkanCodeGenerator {
    private final String BUFFER_VIEW = "bufferView";
    private final String BUFFER_VIEW_CREATE_INFO = "bufferViewCreateInfo";
    private final String OFFSET = "0";
    private final String RANGE = "VK_WHOLE_SIZE";
    private final String FORMAT = "VK_FORMAT_R8G8B8A8_UNORM";

    public VkCreateBufferViewGenerator(RandomStringGenerator randomStringGenerator,
                                       RandomNumberGanerator randomNumberGanerator,
                                       FreshMap freshMap,
                                       Coverage coverage,
                                       VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_BUFFER_VIEW, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateBufferViewConfig config = new VkCreateBufferViewConfig();

        config.setId(generateConfigId());
        config.setBufferView(BUFFER_VIEW + freshMap.getFreshId(BUFFER_VIEW));
        config.setOffset(OFFSET);
        config.setRange(RANGE);
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setBufferViewCreateInfo(BUFFER_VIEW_CREATE_INFO +
                freshMap.getFreshId(BUFFER_VIEW_CREATE_INFO));
        config.setFormat(FORMAT);

        // find random buffer
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_BUFFER);

        VkCreateBufferConfig buffer =
                (VkCreateBufferConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setBuffer(buffer.getBuffer());
        config.setDevice(buffer.getDevice());

        config.setBad(!(buffer.getUsage().contains("VK_BUFFER_USAGE_UNIFORM_TEXEL_BUFFER_BIT")
            || buffer.getUsage().contains("VK_BUFFER_USAGE_STORAGE_TEXEL_BUFFER_BIT"))
            || buffer.isBad());

        config.addDependency(buffer.getId());

        globalState.addConfig(VulkanState.VK_CREATE_BUFFER_VIEW, config);

        return config;
    }
}
