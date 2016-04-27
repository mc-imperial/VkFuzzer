package tool.configs.vulkan.shaders;

import tool.configs.Config;

/**
 * Created by constantinos on 27/04/2016.
 */
public class VkCreateShaderModuleConfig extends Config {
    private String vert;
    private String frag;
    private String vertContents;
    private String fragContents;
    private String vertexPipelineShaderStageCreateInfo;
    private String vertexModuleCreateInfo;
    private String vertexShaderModule;
    private String fragmentPipelineShaderStageCreateInfo;
    private String fragmentModuleCreateInfo;
    private String fragmentShaderModule;
    private String result;
    private String length;
    private String device;

    public String getVert() {
        return vert;
    }

    public void setVert(String vert) {
        this.vert = vert;
    }

    public String getFrag() {
        return frag;
    }

    public void setFrag(String frag) {
        this.frag = frag;
    }

    public String getVertContents() {
        return vertContents;
    }

    public void setVertContents(String vertContents) {
        this.vertContents = vertContents;
    }

    public String getFragContents() {
        return fragContents;
    }

    public void setFragContents(String fragContents) {
        this.fragContents = fragContents;
    }

    public String getVertexPipelineShaderStageCreateInfo() {
        return vertexPipelineShaderStageCreateInfo;
    }

    public void setVertexPipelineShaderStageCreateInfo(String vertexPipelineShaderStageCreateInfo) {
        this.vertexPipelineShaderStageCreateInfo = vertexPipelineShaderStageCreateInfo;
    }

    public String getVertexModuleCreateInfo() {
        return vertexModuleCreateInfo;
    }

    public void setVertexModuleCreateInfo(String vertexModuleCreateInfo) {
        this.vertexModuleCreateInfo = vertexModuleCreateInfo;
    }

    public String getVertexShaderModule() {
        return vertexShaderModule;
    }

    public void setVertexShaderModule(String vertexShaderModule) {
        this.vertexShaderModule = vertexShaderModule;
    }

    public String getFragmentPipelineShaderStageCreateInfo() {
        return fragmentPipelineShaderStageCreateInfo;
    }

    public void setFragmentPipelineShaderStageCreateInfo(String fragmentPipelineShaderStageCreateInfo) {
        this.fragmentPipelineShaderStageCreateInfo = fragmentPipelineShaderStageCreateInfo;
    }

    public String getFragmentModuleCreateInfo() {
        return fragmentModuleCreateInfo;
    }

    public void setFragmentModuleCreateInfo(String fragmentModuleCreateInfo) {
        this.fragmentModuleCreateInfo = fragmentModuleCreateInfo;
    }

    public String getFragmentShaderModule() {
        return fragmentShaderModule;
    }

    public void setFragmentShaderModule(String fragmentShaderModule) {
        this.fragmentShaderModule = fragmentShaderModule;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
