package tool.configs.vulkan.device;

import tool.configs.Config;

/**
 * Created by constantinos on 17/05/2016.
 */
public class SelectDeviceConfig extends Config {
    private String device;
    private String logicalDevices;
    private String surface;
    private String found;

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLogicalDevices() {
        return logicalDevices;
    }

    public void setLogicalDevices(String logicalDevices) {
        this.logicalDevices = logicalDevices;
    }
}
