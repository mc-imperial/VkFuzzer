package tool.codegen.vulkan.synchronisation;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.synchronisation.VkCreateEventConfig;
import tool.configs.vulkan.synchronisation.VkGetEventStatusConfig;
import tool.configs.vulkan.synchronisation.VkSetEventConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 19/04/2016.
 */
public class VkGetEventStatusGenerator extends VulkanCodeGenerator {
    private final String RESULT = "result";
    private final String EVENT_SET = "VK_EVENT_SET";
    private final String EVENT_NOT_SET = "VK_EVENT_RESET";

    public VkGetEventStatusGenerator(RandomStringGenerator randomStringGenerator,
                                     RandomNumberGanerator randomNumberGanerator,
                                     FreshMap freshMap,
                                     Coverage coverage,
                                     VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_GET_EVENT_STATUS, globalState);
    }

    @Override
    public Config generateConfig() {
        VkGetEventStatusConfig config = new VkGetEventStatusConfig();

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        // Get random event and check if it is set
        ArrayList<Config> createEvents =
                globalState.getConfig(VulkanState.VK_CREATE_EVENT);

        ArrayList<Config> setEvents =
                globalState.getConfig(VulkanState.VK_SET_EVENT);

        VkCreateEventConfig createEventConfig =
                (VkCreateEventConfig)
                createEvents.get(randomNumberGanerator.randomNumber(createEvents.size()));

        VkSetEventConfig setEvent = isEventSet(setEvents, createEventConfig);

        config.setDevice(createEventConfig.getDevice());
        config.setExpectedReturnCode(setEvent != null ? EVENT_SET : EVENT_NOT_SET);
        config.addDependency(createEventConfig.getId());
        config.setEvent(createEventConfig.getEvent());

        if (setEvent != null) {
            config.addDependency(setEvent.getId());
            config.setBad(setEvent.isBad());
        }

        config.setBad(createEventConfig.isBad() || config.isBad());

        globalState.addConfig(VulkanState.VK_GET_EVENT_STATUS, config);

        return config;
    }

    // Checks whether of not the event is set
    private VkSetEventConfig isEventSet(ArrayList<Config> setEvents,
                               VkCreateEventConfig createEventConfig)
    {
        for (Config config : setEvents) {
            VkSetEventConfig event = (VkSetEventConfig)config;
            if (event.getEvent().equals(createEventConfig.getEvent())) {
                return  event;
            }
        }

        return null;
    }
}
