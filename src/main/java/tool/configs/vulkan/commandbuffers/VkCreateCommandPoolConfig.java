package tool.configs.vulkan.commandbuffers;

import tool.configs.Config;

/**
 * Created by constantinos on 11/04/2016.
 */
public class VkCreateCommandPoolConfig extends Config {
    private String cmdPoolCreateInfo;
    private String queueFamilyIndex;
    private String flags;
    private String logicalDevice;
    private String result;
    private String commandPool;
    private String randomIndex;
    private String randomQueueIndex;
    private String randomDevice;

    public String getCmdPoolCreateInfo() {
        return cmdPoolCreateInfo;
    }

    public void setCmdPoolCreateInfo(String cmdPoolCreateInfo) {
        this.cmdPoolCreateInfo = cmdPoolCreateInfo;
    }

    public String getQueueFamilyIndex() {
        return queueFamilyIndex;
    }

    public void setQueueFamilyIndex(String queueFamilyIndex) {
        this.queueFamilyIndex = queueFamilyIndex;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getLogicalDevice() {
        return logicalDevice;
    }

    public void setLogicalDevice(String logicalDevice) {
        this.logicalDevice = logicalDevice;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCommandPool() {
        return commandPool;
    }

    public void setCommandPool(String commandPool) {
        this.commandPool = commandPool;
    }

    public String getRandomIndex() {
        return randomIndex;
    }

    public void setRandomIndex(String randomIndex) {
        this.randomIndex = randomIndex;
    }

    public String getRandomQueueIndex() {
        return randomQueueIndex;
    }

    public void setRandomQueueIndex(String randomQueueIndex) {
        this.randomQueueIndex = randomQueueIndex;
    }

    public String getRandomDevice() {
        return randomDevice;
    }

    public void setRandomDevice(String randomDevice) {
        this.randomDevice = randomDevice;
    }
}
