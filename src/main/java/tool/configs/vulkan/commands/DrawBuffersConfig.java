package tool.configs.vulkan.commands;

import tool.configs.Config;

/**
 * Created by constantinos on 09/05/2016.
 */
public class DrawBuffersConfig extends Config {
    private String randomData;
    private String dataPointer;
    private String offsets;
    private String commandBuffer;
    private String vertexbuffer;
    private String swapchainExtent;
    private String viewport;
    private String scissor;
    private String pipeline;

    public String getPipeline() {
        return pipeline;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    public String getRandomData() {
        return randomData;
    }

    public void setRandomData(String randomData) {
        this.randomData = randomData;
    }

    public String getDataPointer() {
        return dataPointer;
    }

    public void setDataPointer(String dataPointer) {
        this.dataPointer = dataPointer;
    }

    public String getOffsets() {
        return offsets;
    }

    public void setOffsets(String offsets) {
        this.offsets = offsets;
    }

    public String getCommandBuffer() {
        return commandBuffer;
    }

    public void setCommandBuffer(String commandBuffer) {
        this.commandBuffer = commandBuffer;
    }

    public String getVertexbuffer() {
        return vertexbuffer;
    }

    public void setVertexbuffer(String vertexbuffer) {
        this.vertexbuffer = vertexbuffer;
    }

    public String getSwapchainExtent() {
        return swapchainExtent;
    }

    public void setSwapchainExtent(String swapchainExtent) {
        this.swapchainExtent = swapchainExtent;
    }

    public String getViewport() {
        return viewport;
    }

    public void setViewport(String viewport) {
        this.viewport = viewport;
    }

    public String getScissor() {
        return scissor;
    }

    public void setScissor(String scissor) {
        this.scissor = scissor;
    }
}
