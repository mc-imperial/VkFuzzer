package tool.codegen.vulkan.utils;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.utils.InstanceExtensionsConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

/**
 * Created by constantinos on 16/05/2016.
 */
public class InstanceExtensionsGenerator extends VulkanCodeGenerator {
    public InstanceExtensionsGenerator(RandomStringGenerator randomStringGenerator,
                                       RandomNumberGanerator randomNumberGanerator,
                                       FreshMap freshMap,
                                       Coverage coverage,
                                       VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.INSTANCE_EXTENSIONS, globalState);
    }

    @Override
    public Config generateConfig() {
        InstanceExtensionsConfig config = new InstanceExtensionsConfig();

        config.setId(generateConfigId());

        globalState.addConfig(VulkanState.INSTANCE_EXTENSIONS, config);

        return config;
    }
}
