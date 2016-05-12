package tool.codegen.vulkan.memory;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.memory.PopulateVertexBufferConfig;
import tool.configs.vulkan.resources.VkCreateBufferConfig;
import tool.configs.vulkan.resources.VkGetBufferMemoryRequirementsConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class PopulateVertexBufferGenerator extends VulkanCodeGenerator {
    private final String VERTEX_BUFFER = "vertexbuffer";
    private final String TYPE_INDEX = "typeIndex";
    private final String FOUND = "found";
    private final String MEMORY_ALLOCATE_INFO = "memoryAllocateInfo";
    private final String DATA_POINTER = "dataPointer";
    private final String RANDOM_DATA = "randomData";

    public PopulateVertexBufferGenerator(RandomStringGenerator randomStringGenerator,
                                         RandomNumberGanerator randomNumberGanerator,
                                         FreshMap freshMap,
                                         Coverage coverage,
                                         VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.POPULATE_VERTEX_BUFFER, globalState);
    }

    @Override
    public Config generateConfig() {
        PopulateVertexBufferConfig config = new PopulateVertexBufferConfig();

        // random buffer
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_GET_BUFFER_MEMORY_REQUIREMENTS);

        VkGetBufferMemoryRequirementsConfig bufferMemoryRequirements =
                (VkGetBufferMemoryRequirementsConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        // Find memory requirements
        ArrayList<Config> buffers =
                globalState.getConfig(VulkanState.VK_CREATE_BUFFER);

        VkCreateBufferConfig buffer = null;
        for (Config c : buffers) {
            VkCreateBufferConfig vertexBuffer = (VkCreateBufferConfig)c;

            if (vertexBuffer.getBuffer().equals(bufferMemoryRequirements.getBuffer())) {
                buffer = vertexBuffer;
                break;
            }
        }

        config.setId(generateConfigId());
        config.setVertexbuffer(VERTEX_BUFFER + freshMap.getFreshId(VERTEX_BUFFER));
        config.setTypeIndex(TYPE_INDEX + freshMap.getFreshId(TYPE_INDEX));
        config.setFound(FOUND + freshMap.getFreshId(FOUND));
        config.setMemoryAllocateInfo(MEMORY_ALLOCATE_INFO +
                freshMap.getFreshId(MEMORY_ALLOCATE_INFO));
        config.setDataPointer(DATA_POINTER + freshMap.getFreshId(DATA_POINTER));
        config.setRandomData(RANDOM_DATA + freshMap.getFreshId(RANDOM_DATA));
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        config.setMemoryRequirements(bufferMemoryRequirements.getMemoryRequirements());
        config.setBuffer(buffer.getBuffer());
        config.setDevice(buffer.getDevice());
        config.setBufferSize(buffer.getSize());
        config.addDependency(buffer.getId());
        config.addDependency(bufferMemoryRequirements.getId());
        config.setBad(buffer.isBad() || bufferMemoryRequirements.isBad());

        globalState.addConfig(VulkanState.POPULATE_VERTEX_BUFFER, config);

        return config;
    }
}
