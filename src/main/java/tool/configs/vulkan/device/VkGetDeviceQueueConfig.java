package tool.configs.vulkan.device;

import tool.configs.Config;

/**
 * Created by constantinos on 17/04/2016.
 */
public class VkGetDeviceQueueConfig extends Config {
    private String queue;
    private String device;
    private String queueFamilyIndex;
    private String queueIndex;
    private String familyIndex;
    private String queueCountIndex;

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getQueueFamilyIndex() {
        return queueFamilyIndex;
    }

    public void setQueueFamilyIndex(String queueFamilyIndex) {
        this.queueFamilyIndex = queueFamilyIndex;
    }

    public String getQueueIndex() {
        return queueIndex;
    }

    public void setQueueIndex(String queueIndex) {
        this.queueIndex = queueIndex;
    }

    public String getQueueCountIndex() {
        return queueCountIndex;
    }

    public void setQueueCountIndex(String queueCountIndex) {
        this.queueCountIndex = queueCountIndex;
    }

    public String getFamilyIndex() {
        return familyIndex;
    }

    public void setFamilyIndex(String familyIndex) {
        this.familyIndex = familyIndex;
    }
}
