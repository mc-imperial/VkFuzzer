package tool.codegen.vulkan.synchronisation;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.synchronisation.VkCreateSemaphoreConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 19/04/2016.
 */
public class VkCreateSemaphoreGenerator extends VulkanCodeGenerator {
    private final String SEMAPHORE_CREATE_INFO = "semaphoreCreateInfo";
    private final String SEMAPHORE = "semaphore";

    public VkCreateSemaphoreGenerator(RandomStringGenerator randomStringGenerator,
                                      RandomNumberGanerator randomNumberGanerator,
                                      FreshMap freshMap,
                                      Coverage coverage,
                                      VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_SEMAPHORE, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateSemaphoreConfig config = new VkCreateSemaphoreConfig();

        // Generate random names
        config.setId(generateConfigId());
        config.setSemaphore(SEMAPHORE + freshMap.getFreshId(SEMAPHORE));
        config.setSemaphoreCreateInfo(SEMAPHORE_CREATE_INFO +
                freshMap.getFreshId(SEMAPHORE_CREATE_INFO));
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_COMMAND_POOL);

        VkCreateCommandPoolConfig cmdPool =
                (VkCreateCommandPoolConfig)
                        configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setDevice(cmdPool.getRandomDevice());
        config.setBad(cmdPool.isBad());
        config.addDependency(cmdPool.getId());

        globalState.addConfig(VulkanState.VK_CREATE_SEMAPHORE, config);

        return config;
    }
}
