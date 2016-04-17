package tool.configs.vulkan.commandbuffers;

import tool.configs.Config;

/**
 * Created by constantinos on 15/04/2016.
 */
public class VkAllocateCommandBuffersConfig extends Config {
    private String cmdBufferAllocInfo;
    private String next;
    private String commandPool;
    private String bufferType;
    private String device;
    private int count;
    private String buffers;
    private String result;

    public String getCmdBufferAllocInfo() {
        return cmdBufferAllocInfo;
    }

    public void setCmdBufferAllocInfo(String cmdBufferAllocInfo) {
        this.cmdBufferAllocInfo = cmdBufferAllocInfo;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getCommandPool() {
        return commandPool;
    }

    public void setCommandPool(String commandPool) {
        this.commandPool = commandPool;
    }

    public String getBufferType() {
        return bufferType;
    }

    public void setBufferType(String bufferType) {
        this.bufferType = bufferType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBuffers() {
        return buffers;
    }

    public void setBuffers(String buffers) {
        this.buffers = buffers;
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
