package tool.codegen.vulkan.enumeration;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.enumeration.VkEnumerateInstanceExtensionPropertiesConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

/**
 * Created by constantinos on 05/04/2016.
 */
public class VkEnumerateInstanceExtensionPropertiesGenerator extends VulkanCodeGenerator {
    private final String RESULT_NAME = "result";
    private final String INSTANCE_EXTENSION_COUNT_NAME = "instanceExtensionCount";
    private final String INSTANCE_EXTENSION_PROPERTIES_NAME =
            "instanceExtensionProperties";

    public VkEnumerateInstanceExtensionPropertiesGenerator(
            RandomStringGenerator randomStringGenerator,
            RandomNumberGanerator randomNumberGanerator,
            FreshMap freshMap,
            Coverage coverage,
            VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.VK_ENUMERATE_INSTANCE_EXTENSION_PROPERTIES,
                globalState);
    }

    @Override
    public Config generateConfig() {
        VkEnumerateInstanceExtensionPropertiesConfig config =
                new VkEnumerateInstanceExtensionPropertiesConfig();

        // Create variable names
        config.setId(generateConfigId());
        config.setResult(RESULT_NAME + freshMap.getFreshId(RESULT_NAME));
        config.setInstanceExtensionCount(INSTANCE_EXTENSION_COUNT_NAME +
                freshMap.getFreshId(INSTANCE_EXTENSION_COUNT_NAME));
        config.setInstanceExtensionProperties(INSTANCE_EXTENSION_PROPERTIES_NAME +
                freshMap.getFreshId(INSTANCE_EXTENSION_PROPERTIES_NAME));

        globalState.addConfig(VulkanState.VK_ENUMERATE_INSTANCE_EXTENSION_PROPERTIES,
                config);

        return config;
    }
}
