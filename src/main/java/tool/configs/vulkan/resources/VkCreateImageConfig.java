package tool.configs.vulkan.resources;

import tool.configs.Config;

import java.util.ArrayList;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkCreateImageConfig extends Config {
    private String vkImageCreateInfo;
    private ArrayList<String> flags;
    private String imageType;
    private String format;
    private String extent;
    private int mipLevels;
    private int arrayLayers;
    private String samples;
    private String tiling;
    private ArrayList<String> usage;
    private String sharingMode;
    private String queueFamilyIndexCount;
    private String pQueueFamilyIndices;
    private String initialLayout;
    private String result;
    private String device;
    private String image;
    private int width;
    private int height;
    private int depth;

    public String getVkImageCreateInfo() {
        return vkImageCreateInfo;
    }

    public void setVkImageCreateInfo(String vkImageCreateInfo) {
        this.vkImageCreateInfo = vkImageCreateInfo;
    }

    public ArrayList<String> getFlags() {
        return flags;
    }

    public void setFlags(ArrayList<String> flags) {
        this.flags = flags;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getExtent() {
        return extent;
    }

    public void setExtent(String extent) {
        this.extent = extent;
    }

    public int getMipLevels() {
        return mipLevels;
    }

    public void setMipLevels(int mipLevels) {
        this.mipLevels = mipLevels;
    }

    public int getArrayLayers() {
        return arrayLayers;
    }

    public void setArrayLayers(int arrayLayers) {
        this.arrayLayers = arrayLayers;
    }

    public String getSamples() {
        return samples;
    }

    public void setSamples(String samples) {
        this.samples = samples;
    }

    public String getTiling() {
        return tiling;
    }

    public void setTiling(String tiling) {
        this.tiling = tiling;
    }

    public ArrayList<String> getUsage() {
        return usage;
    }

    public void setUsage(ArrayList<String> usage) {
        this.usage = usage;
    }

    public String getSharingMode() {
        return sharingMode;
    }

    public void setSharingMode(String sharingMode) {
        this.sharingMode = sharingMode;
    }

    public String getQueueFamilyIndexCount() {
        return queueFamilyIndexCount;
    }

    public void setQueueFamilyIndexCount(String queueFamilyIndexCount) {
        this.queueFamilyIndexCount = queueFamilyIndexCount;
    }

    public String getpQueueFamilyIndices() {
        return pQueueFamilyIndices;
    }

    public void setpQueueFamilyIndices(String pQueueFamilyIndices) {
        this.pQueueFamilyIndices = pQueueFamilyIndices;
    }

    public String getInitialLayout() {
        return initialLayout;
    }

    public void setInitialLayout(String initialLayout) {
        this.initialLayout = initialLayout;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
