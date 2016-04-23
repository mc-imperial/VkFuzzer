package tool.configs.vulkan.resources;

import tool.configs.Config;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkGetImageSubResourceLayoutConfig extends Config {
    private String imageSubresource;
    private String aspectMask;
    private String mipLevel;
    private String arrayLayer;
    private String subresourceLayout;
    private String image;
    private String device;

    public String getImageSubresource() {
        return imageSubresource;
    }

    public void setImageSubresource(String imageSubresource) {
        this.imageSubresource = imageSubresource;
    }

    public String getAspectMask() {
        return aspectMask;
    }

    public void setAspectMask(String aspectMask) {
        this.aspectMask = aspectMask;
    }

    public String getMipLevel() {
        return mipLevel;
    }

    public void setMipLevel(String mipLevel) {
        this.mipLevel = mipLevel;
    }

    public String getArrayLayer() {
        return arrayLayer;
    }

    public void setArrayLayer(String arrayLayer) {
        this.arrayLayer = arrayLayer;
    }

    public String getSubresourceLayout() {
        return subresourceLayout;
    }

    public void setSubresourceLayout(String subresourceLayout) {
        this.subresourceLayout = subresourceLayout;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
