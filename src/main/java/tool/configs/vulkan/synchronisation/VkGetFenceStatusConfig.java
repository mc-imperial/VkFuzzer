package tool.configs.vulkan.synchronisation;

import tool.configs.Config;

/**
 * Created by constantinos on 19/04/2016.
 */
public class VkGetFenceStatusConfig extends Config {
    private String expectedReturnCode;
    private String fence;
    private String result;
    private String device;

    public String getExpectedReturnCode() {
        return expectedReturnCode;
    }

    public void setExpectedReturnCode(String expectedReturnCode) {
        this.expectedReturnCode = expectedReturnCode;
    }

    public String getFence() {
        return fence;
    }

    public void setFence(String fence) {
        this.fence = fence;
    }

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
