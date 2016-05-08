package tool.configs.vulkan.resources;

import tool.configs.Config;

/**
 * Created by constantinos on 08/05/2016.
 */
public class VkCmdEndRenderPassConfig extends Config {
    private String commandBuffer;

    public String getCommandBuffer() {
        return commandBuffer;
    }

    public void setCommandBuffer(String commandBuffer) {
        this.commandBuffer = commandBuffer;
    }
}
