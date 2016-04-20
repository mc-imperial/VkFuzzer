package tool.codegen.vulkan.synchronisation;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.synchronisation.VkCreateEventConfig;
import tool.configs.vulkan.synchronisation.VkResetEventConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 19/04/2016.
 */
public class VkResetEventGenerator extends VulkanCodeGenerator {
    public VkResetEventGenerator(RandomStringGenerator randomStringGenerator,
                                 RandomNumberGanerator randomNumberGanerator,
                                 FreshMap freshMap,
                                 Coverage coverage,
                                 VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_RESET_EVENT, globalState);
    }

    @Override
    public Config generateConfig() {
        VkResetEventConfig config = new VkResetEventConfig();

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        // Get random event and check if it is set
        ArrayList<Config> createEvents =
                globalState.getConfig(VulkanState.VK_CREATE_EVENT);

        VkCreateEventConfig createEventConfig =
                (VkCreateEventConfig)
                createEvents.get(randomNumberGanerator.randomNumber(createEvents.size()));

        config.setDevice(createEventConfig.getDevice());
        config.setEvent(createEventConfig.getEvent());
        config.addDependency(createEventConfig.getId());
        config.setBad(createEventConfig.isBad());

        globalState.addConfig(VulkanState.VK_RESET_EVENT, config);

        return config;
    }
}
