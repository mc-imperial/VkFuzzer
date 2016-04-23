package tool.configs.vulkan.resources;

import tool.configs.Config;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkGetBufferMemoryRequirementsConfig extends Config {
    private String memoryRequirements;
    private String device;
    private String buffer;

    public String getMemoryRequirements() {
        return memoryRequirements;
    }

    public void setMemoryRequirements(String memoryRequirements) {
        this.memoryRequirements = memoryRequirements;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }
}
