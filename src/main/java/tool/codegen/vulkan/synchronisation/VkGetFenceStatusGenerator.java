package tool.codegen.vulkan.synchronisation;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanReturnCodes;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.synchronisation.VkCreateFenceConfig;
import tool.configs.vulkan.synchronisation.VkGetFenceStatusConfig;
import tool.configs.vulkan.synchronisation.VkResetFencesConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 19/04/2016.
 */
public class VkGetFenceStatusGenerator extends VulkanCodeGenerator {
    private final String NO_FLAGS = "0";

    public VkGetFenceStatusGenerator(RandomStringGenerator randomStringGenerator,
                                     RandomNumberGanerator randomNumberGanerator,
                                     FreshMap freshMap,
                                     Coverage coverage,
                                     VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_GET_FENCE_STATUS, globalState);
    }

    @Override
    public Config generateConfig() {
        VkGetFenceStatusConfig config = new VkGetFenceStatusConfig();

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_FENCE);

        VkCreateFenceConfig fenceConfig =
                (VkCreateFenceConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setExpectedResult(!fenceConfig.getFlags().equals(NO_FLAGS) ?
                VulkanReturnCodes.VK_SUCCESS : VulkanReturnCodes.VK_NOT_READY);

        ArrayList<Config> resetConfigs =
                globalState.getConfig(VulkanState.VK_RESET_FENCES);

        for (Config resetConfig : resetConfigs) {
            VkResetFencesConfig resetFenceConfig =  (VkResetFencesConfig)resetConfig;
            if (resetFenceConfig.getFence().equals(fenceConfig.getFence())) {
                config.setExpectedResult(VulkanReturnCodes.VK_NOT_READY);
            }
        }

        config.setDevice(fenceConfig.getDevice());
        config.setFence(fenceConfig.getFence());
        config.addDependency(fenceConfig.getId());
        config.setBad(fenceConfig.isBad());

        globalState.addConfig(VulkanState.VK_GET_FENCE_STATUS, config);

        return config;
    }
}
