package tool.codegen.vulkan.device;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.device.VkCreateDeviceConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;

/**
 * Created by cvryo on 10/04/2016.
 */
public class VkCreateDeviceGenerator extends VulkanCodeGenerator {
    public VkCreateDeviceGenerator(RandomStringGenerator randomStringGenerator,
                                   RandomNumberGanerator randomNumberGanerator,
                                   FreshMap freshMap,
                                   Coverage coverage,
                                   VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.VK_CREATE_DEVICE, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateDeviceConfig config = new VkCreateDeviceConfig();
        ArrayList<Config> enumeratedPhysicalDeviceProperties =
                globalState.getConfig(VulkanState.GET_DEVICE_PROPERTIES);

        config.setId(generateConfigId());
        config.setDevicePropertiesConfigs(enumeratedPhysicalDeviceProperties);

        return config;
    }
}
