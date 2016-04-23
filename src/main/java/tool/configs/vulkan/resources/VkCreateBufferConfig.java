package tool.configs.vulkan.resources;

import tool.configs.Config;

import java.util.ArrayList;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkCreateBufferConfig extends Config {
    private String bufferCreateInfo;
    private ArrayList<String> flags;
    private int size;
    private ArrayList<String> usage;
    private String sharingMode;
    private int queueFamilyIndexCount;
    private String pQueueFamilyIndices;
    private String buffer;
    private String result;
    private String device;

    public String getBufferCreateInfo() {
        return bufferCreateInfo;
    }

    public void setBufferCreateInfo(String bufferCreateInfo) {
        this.bufferCreateInfo = bufferCreateInfo;
    }

    public ArrayList<String> getFlags() {
        return flags;
    }

    public void setFlags(ArrayList<String> flags) {
        this.flags = flags;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public int getQueueFamilyIndexCount() {
        return queueFamilyIndexCount;
    }

    public void setQueueFamilyIndexCount(int queueFamilyIndexCount) {
        this.queueFamilyIndexCount = queueFamilyIndexCount;
    }

    public String getpQueueFamilyIndices() {
        return pQueueFamilyIndices;
    }

    public void setpQueueFamilyIndices(String pQueueFamilyIndices) {
        this.pQueueFamilyIndices = pQueueFamilyIndices;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
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
}
