package tool.configs.vulkan.device;

import tool.configs.Config;

import java.util.ArrayList;

/**
 * Created by cvryo on 10/04/2016.
 */
public class VkCreateDeviceConfig extends Config {
    private ArrayList<Config> devicePropertiesConfigs;
    private String logicalDevices;
    private String queueIndex;

    public VkCreateDeviceConfig() {
        devicePropertiesConfigs = new ArrayList<>();
    }

    public ArrayList<Config> getDevicePropertiesConfigs() {
        return devicePropertiesConfigs;
    }

    public void setDevicePropertiesConfigs(ArrayList<Config> devicePropertiesConfigs) {
        this.devicePropertiesConfigs = devicePropertiesConfigs;
    }

    public String getLogicalDevices() {
        return logicalDevices;
    }

    public void setLogicalDevices(String logicalDevices) {
        this.logicalDevices = logicalDevices;
    }

    public String getQueueIndex() {
        return queueIndex;
    }

    public void setQueueIndex(String queueIndex) {
        this.queueIndex = queueIndex;
    }
}
