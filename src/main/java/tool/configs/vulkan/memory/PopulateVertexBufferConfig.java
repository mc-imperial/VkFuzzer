package tool.configs.vulkan.memory;

import tool.configs.Config;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class PopulateVertexBufferConfig extends Config {
    private String vertexbuffer;
    private String buffer;
    private String typeIndex;
    private String found;
    private String device;
    private String memoryRequirements;
    private String memoryAllocateInfo;
    private String result;
    private String dataPointer;
    private String randomData;
    private int bufferSize;

    public String getVertexbuffer() {
        return vertexbuffer;
    }

    public void setVertexbuffer(String vertexbuffer) {
        this.vertexbuffer = vertexbuffer;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public String getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(String typeIndex) {
        this.typeIndex = typeIndex;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getMemoryRequirements() {
        return memoryRequirements;
    }

    public void setMemoryRequirements(String memoryRequirements) {
        this.memoryRequirements = memoryRequirements;
    }

    public String getMemoryAllocateInfo() {
        return memoryAllocateInfo;
    }

    public void setMemoryAllocateInfo(String memoryAllocateInfo) {
        this.memoryAllocateInfo = memoryAllocateInfo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDataPointer() {
        return dataPointer;
    }

    public void setDataPointer(String dataPointer) {
        this.dataPointer = dataPointer;
    }

    public String getRandomData() {
        return randomData;
    }

    public void setRandomData(String randomData) {
        this.randomData = randomData;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
