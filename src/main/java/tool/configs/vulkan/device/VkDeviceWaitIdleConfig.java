package tool.configs.vulkan.device;

import tool.configs.Config;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkDeviceWaitIdleConfig extends Config {
    private String result;
    private String device;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
