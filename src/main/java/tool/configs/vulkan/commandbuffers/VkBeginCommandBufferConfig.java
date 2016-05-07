package tool.configs.vulkan.commandbuffers;

import tool.configs.Config;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkBeginCommandBufferConfig extends Config {
    private String commandBufferBeginInfo;
    private String result;
    private String commandBuffer;
    private String commandBuffers;

    public String getCommandBufferBeginInfo() {
        return commandBufferBeginInfo;
    }

    public void setCommandBufferBeginInfo(String commandBufferBeginInfo) {
        this.commandBufferBeginInfo = commandBufferBeginInfo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCommandBuffer() {
        return commandBuffer;
    }

    public void setCommandBuffer(String commandBuffer) {
        this.commandBuffer = commandBuffer;
    }

    public String getCommandBuffers() {
        return commandBuffers;
    }

    public void setCommandBuffers(String commandBuffers) {
        this.commandBuffers = commandBuffers;
    }
}
