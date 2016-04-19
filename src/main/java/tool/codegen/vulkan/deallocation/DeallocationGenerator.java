package tool.codegen.vulkan.deallocation;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.deallocation.DeallocationConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 18/04/2016.
 */
public class DeallocationGenerator extends VulkanCodeGenerator {
    public DeallocationGenerator(RandomStringGenerator randomStringGenerator,
                                 RandomNumberGanerator randomNumberGanerator,
                                 FreshMap freshMap,
                                 Coverage coverage,
                                 VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.DEALLOCATION, globalState);
    }

    @Override
    public Config generateConfig() {
        DeallocationConfig config = new DeallocationConfig();

        config.setId(generateConfigId());

        // Dealloc instances
        ArrayList<Config> instances =
                globalState.getConfig(VulkanState.VK_CREATE_INSTANCE);
        config.setInstances(instances);

        // Dealloc devices
        ArrayList<Config> devices =
                globalState.getConfig(VulkanState.VK_CREATE_DEVICE);
        config.setDevices(devices);

        // Dealloc command pools
        ArrayList<Config> commandPools =
                globalState.getConfig(VulkanState.VK_CREATE_COMMAND_POOL);
        config.setCmdPools(commandPools);

        // Dealloc command buffers
        ArrayList<Config> commandBuffers =
                globalState.getConfig(VulkanState.VK_ALLOCATE_COMMAND_BUFFERS);
        config.setCmdBuffers(commandBuffers);

        return config;
    }
}
