package tool.configs.vulkan.enumeration;

import tool.configs.Config;

/**
 * Created by constantinos on 06/04/2016.
 */
public class VkEnumeratePhysicalDevicesConfig extends Config {
    private String gpus;
    private String gpuCount;
    private String result;
    private String instance;
    private String returnCode;

    public String getGpus() {
        return gpus;
    }

    public void setGpus(String gpus) {
        this.gpus = gpus;
    }

    public String getGpuCount() {
        return gpuCount;
    }

    public void setGpuCount(String gpuCount) {
        this.gpuCount = gpuCount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
