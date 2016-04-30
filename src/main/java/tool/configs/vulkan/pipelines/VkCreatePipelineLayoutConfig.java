package tool.configs.vulkan.pipelines;

import tool.configs.Config;

/**
 * Created by kyriaki on 30/04/2016.
 */
public class VkCreatePipelineLayoutConfig extends Config {
    private String pipelineLayoutCreateInfo;
    private String pipelineLayout;
    private String device;
    private String result;

    public String getPipelineLayoutCreateInfo() {
        return pipelineLayoutCreateInfo;
    }

    public void setPipelineLayoutCreateInfo(String pipelineLayoutCreateInfo) {
        this.pipelineLayoutCreateInfo = pipelineLayoutCreateInfo;
    }

    public String getPipelineLayout() {
        return pipelineLayout;
    }

    public void setPipelineLayout(String pipelineLayout) {
        this.pipelineLayout = pipelineLayout;
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
