package tool.configs.vulkan.commandbuffers;

import tool.configs.Config;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkEndCommandBufferConfig extends Config {
    private String result;
    private String commandBuffer;

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
}
