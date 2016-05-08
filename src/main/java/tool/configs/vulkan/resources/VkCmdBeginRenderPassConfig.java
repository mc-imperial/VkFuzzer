package tool.configs.vulkan.resources;

import tool.configs.Config;

/**
 * Created by constantinos on 08/05/2016.
 */
public class VkCmdBeginRenderPassConfig extends Config {
    private String clearValues;
    private String renderpassBeginInfo;
    private String renderpass;
    private String framebuffers;
    private String commandBuffer;
    private String nextImage;

    public String getNextImage() {
        return nextImage;
    }

    public void setNextImage(String nextImage) {
        this.nextImage = nextImage;
    }

    public String getClearValues() {
        return clearValues;
    }

    public void setClearValues(String clearValues) {
        this.clearValues = clearValues;
    }

    public String getRenderpassBeginInfo() {
        return renderpassBeginInfo;
    }

    public void setRenderpassBeginInfo(String renderpassBeginInfo) {
        this.renderpassBeginInfo = renderpassBeginInfo;
    }

    public String getRenderpass() {
        return renderpass;
    }

    public void setRenderpass(String renderpass) {
        this.renderpass = renderpass;
    }

    public String getFramebuffers() {
        return framebuffers;
    }

    public void setFramebuffers(String framebuffers) {
        this.framebuffers = framebuffers;
    }

    public String getCommandBuffer() {
        return commandBuffer;
    }

    public void setCommandBuffer(String commandBuffer) {
        this.commandBuffer = commandBuffer;
    }
}
