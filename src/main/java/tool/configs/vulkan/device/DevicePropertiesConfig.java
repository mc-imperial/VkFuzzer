package tool.configs.vulkan.device;

import tool.configs.Config;

/**
 * Created by constantinos on 08/04/2016.
 */
public class DevicePropertiesConfig extends Config {
    private String deviceMemoryProperties;
    private String deviceFeatures;
    private String deviceExtensionProperties;
    private String deviceQueueFamilyProperties;
    private String deviceLayerProperties;
    private String deviceProperties;
    private String devices;

    public String getDeviceMemoryProperties() {
        return deviceMemoryProperties;
    }

    public void setDeviceMemoryProperties(String deviceMemoryProperties) {
        this.deviceMemoryProperties = deviceMemoryProperties;
    }

    public String getDeviceFeatures() {
        return deviceFeatures;
    }

    public void setDeviceFeatures(String deviceFeatures) {
        this.deviceFeatures = deviceFeatures;
    }

    public String getDeviceExtensionProperties() {
        return deviceExtensionProperties;
    }

    public void setDeviceExtensionProperties(String deviceExtensionProperties) {
        this.deviceExtensionProperties = deviceExtensionProperties;
    }

    public String getDeviceQueueFamilyProperties() {
        return deviceQueueFamilyProperties;
    }

    public void setDeviceQueueFamilyProperties(String deviceQueueFamilyProperties) {
        this.deviceQueueFamilyProperties = deviceQueueFamilyProperties;
    }

    public String getDeviceLayerProperties() {
        return deviceLayerProperties;
    }

    public void setDeviceLayerProperties(String deviceLayerProperties) {
        this.deviceLayerProperties = deviceLayerProperties;
    }

    public String getDeviceProperties() {
        return deviceProperties;
    }

    public void setDeviceProperties(String deviceProperties) {
        this.deviceProperties = deviceProperties;
    }

    public String getDevices() {
        return devices;
    }

    public void setDevices(String devices) {
        this.devices = devices;
    }
}
