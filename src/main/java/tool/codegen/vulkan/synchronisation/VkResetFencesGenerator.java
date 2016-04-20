package tool.codegen.vulkan.synchronisation;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.synchronisation.VkCreateFenceConfig;
import tool.configs.vulkan.synchronisation.VkResetFencesConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 19/04/2016.
 */
public class VkResetFencesGenerator extends VulkanCodeGenerator {
    private final String RESULT = "result";

    public VkResetFencesGenerator(RandomStringGenerator randomStringGenerator,
                                  RandomNumberGanerator randomNumberGanerator,
                                  FreshMap freshMap,
                                  Coverage coverage,
                                  VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_RESET_FENCES, globalState);
    }

    @Override
    public Config generateConfig() {
        VkResetFencesConfig config = new VkResetFencesConfig();

        config.setId(generateConfigId());

        ArrayList<Config> fences =
                globalState.getConfig(VulkanState.VK_CREATE_FENCE);

        VkCreateFenceConfig fence =
                (VkCreateFenceConfig)
                fences.get(randomNumberGanerator.randomNumber(fences.size()));

        config.setBad(fence.isBad());
        config.setDevice(fence.getDevice());
        config.setFence(fence.getFence());
        config.addDependency(fence.getId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        globalState.addConfig(VulkanState.VK_RESET_FENCES, config);

        return config;
    }
}
