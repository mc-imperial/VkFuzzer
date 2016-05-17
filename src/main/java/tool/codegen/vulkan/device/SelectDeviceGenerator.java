package tool.codegen.vulkan.device;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.device.DevicesConfig;
import tool.configs.vulkan.device.SelectDeviceConfig;
import tool.configs.vulkan.device.VkCreateDeviceConfig;
import tool.configs.vulkan.swapchain.VkCreateSurfaceKHRConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 17/05/2016.
 */
public class SelectDeviceGenerator extends VulkanCodeGenerator {
    private final String DEVICE = "device";
    private final String NULL_SURFACE = "NULL";

    public SelectDeviceGenerator(RandomStringGenerator randomStringGenerator,
                                 RandomNumberGanerator randomNumberGanerator,
                                 FreshMap freshMap,
                                 Coverage coverage,
                                 VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.SELECT_DEVICE, globalState);
    }

    @Override
    public Config generateConfig() {
        SelectDeviceConfig config = new SelectDeviceConfig();

        config.setId(generateConfigId());
        config.setDevice(DEVICE + freshMap.getFreshId(DEVICE));

        //
        ArrayList<Config> surfaces =
                globalState.getConfig(VulkanState.VK_CREATE_SURFACE_KHR);

        if (!surfaces.isEmpty()) {
            VkCreateSurfaceKHRConfig surface = (VkCreateSurfaceKHRConfig)
                    surfaces.get(randomNumberGanerator.randomNumber(surfaces.size()));
            config.setSurface(surface.getSurface());
        } else {
            config.setSurface(NULL_SURFACE);
        }


        // Get random device
        // Select a random logical device
        ArrayList<Config> vkCreateDeviceConfigs =
                globalState.getConfig(VulkanState.VK_CREATE_DEVICE);

        int random1 = randomNumberGanerator.randomNumber(vkCreateDeviceConfigs.size());
        VkCreateDeviceConfig randomConfig = (VkCreateDeviceConfig)
                vkCreateDeviceConfigs.get(random1);

        // Randomly find an index for logical device and queue index
        int random2 = randomNumberGanerator.randomNumber(randomConfig.getDevicePropertiesConfigs().size());
        DevicesConfig devicePropertiesConfigs = (DevicesConfig)randomConfig.getDevicePropertiesConfigs().get(random2);
        int random3 = randomNumberGanerator.randomNumber(devicePropertiesConfigs.getDevices().size());

        config.setLogicalDevices(randomConfig.getLogicalDevices() + random2 + random3);
        config.addDependency(randomConfig.getId());
        config.setBad(config.isBad() || randomConfig.isBad());

        globalState.addConfig(VulkanState.SELECT_DEVICE, config);

        return config;
    }
}
