package tool.configs.vulkan.device;

import tool.configs.Config;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkQueueSubmitConfig extends Config {
    private String submitInfo;
    private String commandBuffer;
    private String queue;
    private String result;

    public String getSubmitInfo() {
        return submitInfo;
    }

    public void setSubmitInfo(String submitInfo) {
        this.submitInfo = submitInfo;
    }

    public String getCommandBuffer() {
        return commandBuffer;
    }

    public void setCommandBuffer(String commandBuffer) {
        this.commandBuffer = commandBuffer;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
