package tool.configs.vulkan.device;

import tool.configs.Config;

import java.util.ArrayList;

/**
 * Created by cvryo on 10/04/2016.
 */
public class VkCreateDeviceConfig extends Config {
    private ArrayList<Config> devicePropertiesConfigs;

    public VkCreateDeviceConfig() {
        devicePropertiesConfigs = new ArrayList<>();
    }

    public ArrayList<Config> getDevicePropertiesConfigs() {
        return devicePropertiesConfigs;
    }

    public void setDevicePropertiesConfigs(ArrayList<Config> devicePropertiesConfigs) {
        this.devicePropertiesConfigs = devicePropertiesConfigs;
    }
}
