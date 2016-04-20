package tool.codegen.vulkan.enumeration;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanReturnCodes;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.enumeration.VkEnumeratePhysicalDevicesConfig;
import tool.configs.vulkan.instance.VkCreateInstanceConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 06/04/2016.
 */
public class VkEnumeratePhysicalDevicesGenerator extends VulkanCodeGenerator {
    private final String GPUS = "gpus";
    private final String GPU_COUNT = "gpuCount";

    public VkEnumeratePhysicalDevicesGenerator(
            RandomStringGenerator randomStringGenerator,
            RandomNumberGanerator randomNumberGanerator,
            FreshMap freshMap,
            Coverage coverage,
            VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.VK_ENUMERATE_PHYSICAL_DEVICES, globalState);
    }

    @Override
    public Config generateConfig() {
        VkEnumeratePhysicalDevicesConfig config =
                new VkEnumeratePhysicalDevicesConfig();

        // Create names for variables
        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setGpus(GPUS + freshMap.getFreshId(GPUS));
        config.setGpuCount(GPU_COUNT + freshMap.getFreshId(GPU_COUNT));

        // Choose a random instance config
        ArrayList<Config> instances =
                globalState.getConfig(VulkanState.VK_CREATE_INSTANCE);

        VkCreateInstanceConfig instanceConfig = (VkCreateInstanceConfig)
                instances.get(randomNumberGanerator.randomNumber(instances.size()));

        // Add expected return code
        if (instanceConfig.isBad()) {
            config.setReturnCode(VulkanReturnCodes.VK_ERROR_INITIALIZATION_FAILED);
        } else {
            config.setReturnCode(VulkanReturnCodes.VK_SUCCESS);
        }

        config.addDependency(instanceConfig.getId());
        config.setInstance(instanceConfig.getInstanceName());
        config.setBad(instanceConfig.isBad());

        globalState.addConfig(VulkanState.VK_ENUMERATE_PHYSICAL_DEVICES,
                config);

        return config;
    }
}
