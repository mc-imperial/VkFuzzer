package tool.codegen.vulkan.device;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.device.DevicePropertiesConfig;
import tool.configs.vulkan.device.DevicesConfig;
import tool.configs.vulkan.enumeration.VkEnumeratePhysicalDevicesConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by constantinos on 08/04/2016.
 */
public class DevicePropertiesGenerator extends VulkanCodeGenerator {
    private final String DEVICE_MEMORY_PROPERTIES = "deviceMemoryProperties";
    private final String DEVICE_FEATURES = "deviceFeatures";
    private final String DEVICE_PROPERTIES = "deviceProperties";
    private final String DEVICE_QUEUE_FAMILY_PROPERTIES = "deviceQueueFamilyProperties";
    private final String DEVICE_EXTENSION_PROPERTIES = "deviceExtensionProperties";
    private final String DEVICE_LAYER_PROPERTIES = "deviceLayerProperties";

    public DevicePropertiesGenerator(RandomStringGenerator randomStringGenerator,
                                     RandomNumberGanerator randomNumberGanerator,
                                     FreshMap freshMap,
                                     Coverage coverage,
                                     VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.GET_DEVICE_PROPERTIES, globalState);
    }

    @Override
    public Config generateConfig() {
        ArrayList<Config> physicalDevices =
                globalState.getConfig(VulkanState.VK_ENUMERATE_PHYSICAL_DEVICES);
        DevicesConfig devicesConfig = new DevicesConfig();

        ArrayList<Config> devices = new ArrayList<>();
        for (Config physicalDevice : physicalDevices) {
            DevicePropertiesConfig config = new DevicePropertiesConfig();

            VkEnumeratePhysicalDevicesConfig physicalDevicesConfig =
                    (VkEnumeratePhysicalDevicesConfig)physicalDevice;

            config.setId(generateConfigId());
            config.setDeviceMemoryProperties(DEVICE_MEMORY_PROPERTIES +
                    freshMap.getFreshId(DEVICE_MEMORY_PROPERTIES));
            config.setDeviceFeatures(DEVICE_FEATURES +
                    freshMap.getFreshId(DEVICE_FEATURES));
            config.setDeviceProperties(DEVICE_PROPERTIES +
                    freshMap.getFreshId(DEVICE_PROPERTIES));
            config.setDeviceQueueFamilyProperties(DEVICE_QUEUE_FAMILY_PROPERTIES +
                    freshMap.getFreshId(DEVICE_QUEUE_FAMILY_PROPERTIES));
            config.setDeviceExtensionProperties(DEVICE_EXTENSION_PROPERTIES +
                    freshMap.getFreshId(DEVICE_EXTENSION_PROPERTIES));
            config.setDeviceLayerProperties(DEVICE_LAYER_PROPERTIES +
                    freshMap.getFreshId(DEVICE_LAYER_PROPERTIES));
            config.setDevices(physicalDevicesConfig.getGpus());
            config.setBad(physicalDevice.isBad());

            devices.add(config);
            devicesConfig.addDependency(config.getId());
        }

        devicesConfig.setId(generateConfigId());
        devicesConfig.setDevices(devices);

        globalState.addConfig(VulkanState.GET_DEVICE_PROPERTIES, devicesConfig);

        return devicesConfig;
    }
}
