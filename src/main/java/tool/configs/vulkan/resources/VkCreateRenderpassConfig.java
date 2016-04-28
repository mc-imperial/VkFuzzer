package tool.configs.vulkan.resources;

import tool.configs.Config;

/**
 * Created by constantinos on 28/04/2016.
 */
public class VkCreateRenderpassConfig extends Config {
    private String attachments;
    private String colour;
    private String depth;
    private String subpass;
    private String renderpassCreateInfo;
    private String renderpass;
    private String device;
    private String result;

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getSubpass() {
        return subpass;
    }

    public void setSubpass(String subpass) {
        this.subpass = subpass;
    }

    public String getRenderpassCreateInfo() {
        return renderpassCreateInfo;
    }

    public void setRenderpassCreateInfo(String renderpassCreateInfo) {
        this.renderpassCreateInfo = renderpassCreateInfo;
    }

    public String getRenderpass() {
        return renderpass;
    }

    public void setRenderpass(String renderpass) {
        this.renderpass = renderpass;
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
