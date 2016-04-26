package tool.configs.vulkan.resources;

import tool.configs.Config;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkCreateImageViewConfig extends Config {
    private String vkImageViewCreateInfo;
    private String image;
    private String viewType;
    private String format;
    private String components;
    private String subresourceRange;
    private String imageView;
    private String result;
    private String device;
    private String aspectMask;
    private int baseMipLevel;
    private int levelCount;
    private int baseArrayLayer;
    private int layerCount;

    public String getVkImageViewCreateInfo() {
        return vkImageViewCreateInfo;
    }

    public void setVkImageViewCreateInfo(String vkImageViewCreateInfo) {
        this.vkImageViewCreateInfo = vkImageViewCreateInfo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getSubresourceRange() {
        return subresourceRange;
    }

    public void setSubresourceRange(String subresourceRange) {
        this.subresourceRange = subresourceRange;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getAspectMask() {
        return aspectMask;
    }

    public void setAspectMask(String aspectMask) {
        this.aspectMask = aspectMask;
    }

    public int getBaseMipLevel() {
        return baseMipLevel;
    }

    public void setBaseMipLevel(int baseMipLevel) {
        this.baseMipLevel = baseMipLevel;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(int levelCount) {
        this.levelCount = levelCount;
    }

    public int getBaseArrayLayer() {
        return baseArrayLayer;
    }

    public void setBaseArrayLayer(int baseArrayLayer) {
        this.baseArrayLayer = baseArrayLayer;
    }

    public int getLayerCount() {
        return layerCount;
    }

    public void setLayerCount(int layerCount) {
        this.layerCount = layerCount;
    }
}
