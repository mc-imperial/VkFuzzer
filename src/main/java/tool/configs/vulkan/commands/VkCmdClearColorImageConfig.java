package tool.configs.vulkan.commands;

import tool.configs.Config;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkCmdClearColorImageConfig extends Config {
    private String subresourceRange;
    private String clearColor;
    private String commandBuffer;
    private String swapchainBuffers;
    private String nextImage;

    public String getSubresourceRange() {
        return subresourceRange;
    }

    public void setSubresourceRange(String subresourceRange) {
        this.subresourceRange = subresourceRange;
    }

    public String getClearColor() {
        return clearColor;
    }

    public void setClearColor(String clearColor) {
        this.clearColor = clearColor;
    }

    public String getCommandBuffer() {
        return commandBuffer;
    }

    public void setCommandBuffer(String commandBuffer) {
        this.commandBuffer = commandBuffer;
    }

    public String getSwapchainBuffers() {
        return swapchainBuffers;
    }

    public void setSwapchainBuffers(String swapchainBuffers) {
        this.swapchainBuffers = swapchainBuffers;
    }

    public String getNextImage() {
        return nextImage;
    }

    public void setNextImage(String nextImage) {
        this.nextImage = nextImage;
    }
}
