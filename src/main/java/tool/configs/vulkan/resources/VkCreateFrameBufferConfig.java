package tool.configs.vulkan.resources;

import tool.configs.Config;

/**
 * Created by kyriaki on 30/04/2016.
 */
public class VkCreateFrameBufferConfig extends Config {
    private String framebufferCreateInfo;
    private String renderpass;
    private int attachmentsCount;
    private String attachments;
    private int width;
    private int height;
    private int layers;
    private String framebuffer;
    private String framebuffers;
    private String swapchainBuffers;
    private String device;
    private String result;
    private String swapchainExtent;

    public String getSwapchainExtent() {
        return swapchainExtent;
    }

    public void setSwapchainExtent(String swapchainExtent) {
        this.swapchainExtent = swapchainExtent;
    }

    public String getSwapchainBuffers() {
        return swapchainBuffers;
    }

    public void setSwapchainBuffers(String swapchainBuffers) {
        this.swapchainBuffers = swapchainBuffers;
    }

    public String getFramebuffers() {
        return framebuffers;
    }

    public void setFramebuffers(String framebuffers) {
        this.framebuffers = framebuffers;
    }

    public String getFramebufferCreateInfo() {
        return framebufferCreateInfo;
    }

    public void setFramebufferCreateInfo(String framebufferCreateInfo) {
        this.framebufferCreateInfo = framebufferCreateInfo;
    }

    public String getRenderpass() {
        return renderpass;
    }

    public void setRenderpass(String renderpass) {
        this.renderpass = renderpass;
    }

    public int getAttachmentsCount() {
        return attachmentsCount;
    }

    public void setAttachmentsCount(int attachmentsCount) {
        this.attachmentsCount = attachmentsCount;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLayers() {
        return layers;
    }

    public void setLayers(int layers) {
        this.layers = layers;
    }

    public String getFramebuffer() {
        return framebuffer;
    }

    public void setFramebuffer(String framebuffer) {
        this.framebuffer = framebuffer;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
