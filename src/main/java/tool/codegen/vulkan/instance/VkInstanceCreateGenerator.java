package tool.codegen.vulkan.instance;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.instance.VkApplicationInfoConfig;
import tool.configs.vulkan.instance.VkInstanceCreateInfoConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 27/03/2016.
 */
public class VkInstanceCreateGenerator extends VulkanCodeGenerator {
    private final String VAR_NAME = "instanceCreateInfo";
    private final String TYPE = "VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO";
    private final String NEXT = "NULL";
    private final String FLAGS = "0";
    private final String LAYER_NAMES = "NULL";
    private final int LAYER_COUNT = 0;
    private final String EXTENSION_NAME = "NULL";
    private final int EXTENSION_COUNT = 0;

    public VkInstanceCreateGenerator(final RandomStringGenerator randomStringGenerator,
                                     final RandomNumberGanerator randomNumberGanerator,
                                     final FreshMap freshMap,
                                     final Coverage coverage,
                                     final VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.VK_INSTANCE_CREATE_INFO, globalState);
    }

    @Override
    public Config generateConfig() {
        VkInstanceCreateInfoConfig config = new VkInstanceCreateInfoConfig();

        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_APPLICATION_INFO);

        // Generate random variable names and values
        config.setId(generateConfigId());
        config.setVariableName(VAR_NAME + freshMap.getFreshId(VAR_NAME));
        config.setType(TYPE);
        config.setNext(NEXT);
        config.setFlags(FLAGS);
        config.setEnabledExtensionNames(EXTENSION_NAME);
        config.setEnabledExtensionCount(EXTENSION_COUNT);
        config.setEnabledLayerNames(LAYER_NAMES);
        config.setEnabledLayerCount(LAYER_COUNT);

        // Choose random VkApplicationInfoConfig
        VkApplicationInfoConfig appInfo = (VkApplicationInfoConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setApplicationInfo(appInfo.getVariableName());
        config.setBad(appInfo.isBad());
        config.addDependency(appInfo.getId());

        globalState.addConfig(VulkanState.VK_INSTANCE_CREATE_INFO, config);

        return config;
    }
}
