package tool.configs.vulkan.synchronisation;

import tool.configs.Config;

/**
 * Created by constantinos on 19/04/2016.
 */
public class VkCreateFenceConfig extends Config {
    private String fenceCreateInfo;
    private String fence;
    private String result;
    private String device;
    private String flags;

    public String getFenceCreateInfo() {
        return fenceCreateInfo;
    }

    public void setFenceCreateInfo(String fenceCreateInfo) {
        this.fenceCreateInfo = fenceCreateInfo;
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

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }
}
