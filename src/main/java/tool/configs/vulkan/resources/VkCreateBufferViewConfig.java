package tool.configs.vulkan.resources;

import tool.configs.Config;

/**
 * Created by constantinos on 22/04/2016.
 */
public class VkCreateBufferViewConfig extends Config {
    private String bufferViewCreateInfo;
    private String buffer;
    private String format;
    private String offset;
    private String range;
    private String bufferView;
    private String result;
    private String device;

    public String getBufferViewCreateInfo() {
        return bufferViewCreateInfo;
    }

    public void setBufferViewCreateInfo(String bufferViewInfo) {
        this.bufferViewCreateInfo = bufferViewInfo;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getBufferView() {
        return bufferView;
    }

    public void setBufferView(String bufferView) {
        this.bufferView = bufferView;
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
