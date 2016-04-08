package tool.codegen.vulkan.instance;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.instance.VkCreateInstanceConfig;
import tool.configs.vulkan.instance.VkInstanceCreateInfoConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 27/03/2016.
 */
public class VkCreateInstanceGenerator extends VulkanCodeGenerator {
    private final String RESULT_NAME = "result";
    private final String ALLOC = "NULL";
    private final String INSTANCE_NAME = "instance";

    public VkCreateInstanceGenerator(final RandomStringGenerator randomStringGenerator,
                                     final RandomNumberGanerator randomNumberGanerator,
                                     final FreshMap freshMap,
                                     final Coverage coverage,
                                     final VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.VK_CREATE_INSTANCE, globalState);
    }

    @Override
    public ArrayList<Config> generateConfig() {
        ArrayList<Config> configs = new ArrayList<>();

        VkCreateInstanceConfig config = new VkCreateInstanceConfig();

        ArrayList<Config> instanceCreateInfos =
                globalState.getConfig(VulkanState.VK_INSTANCE_CREATE_INFO);

        // Create random variable names
        config.setId(generateConfigId());
        config.setInstanceName(INSTANCE_NAME + freshMap.getFreshId(INSTANCE_NAME));
        config.setAlloc(ALLOC);
        config.setResult(RESULT_NAME + freshMap.getFreshId(RESULT_NAME));

        // Choose a random VkInstanceCreateInfoConfig
        VkInstanceCreateInfoConfig instanceCreateInfoConfig = (VkInstanceCreateInfoConfig)
                        instanceCreateInfos.get(randomNumberGanerator.randomNumber(instanceCreateInfos.size()));

        config.setInstanceCreateInfo(instanceCreateInfoConfig.getVariableName());
        config.setBad(instanceCreateInfoConfig.isBad());
        config.addDependency(instanceCreateInfoConfig.getId());

        globalState.addConfig(VulkanState.VK_CREATE_INSTANCE, config);
        configs.add(config);

        return configs;
    }
}
