package tool.configs.vulkan.synchronisation;

import tool.configs.Config;

/**
 * Created by constantinos on 19/04/2016.
 */
public class VkCreateSemaphoreConfig extends Config {
    private String semaphoreCreateInfo;
    private String semaphore;
    private String result;
    private String device;

    public String getSemaphoreCreateInfo() {
        return semaphoreCreateInfo;
    }

    public void setSemaphoreCreateInfo(String semaphoreCreateInfo) {
        this.semaphoreCreateInfo = semaphoreCreateInfo;
    }

    public String getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(String semaphore) {
        this.semaphore = semaphore;
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
