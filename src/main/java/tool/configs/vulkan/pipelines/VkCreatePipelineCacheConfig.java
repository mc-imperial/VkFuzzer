package tool.configs.vulkan.pipelines;

import tool.configs.Config;

/**
 * Created by kyriaki on 30/04/2016.
 */
public class VkCreatePipelineCacheConfig extends Config {
    private String pipelineCacheCreateInfo;
    private String device;
    private String pipelineCache;
    private String result;

    public String getPipelineCacheCreateInfo() {
        return pipelineCacheCreateInfo;
    }

    public void setPipelineCacheCreateInfo(String pipelineCacheCreateInfo) {
        this.pipelineCacheCreateInfo = pipelineCacheCreateInfo;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPipelineCache() {
        return pipelineCache;
    }

    public void setPipelineCache(String pipelineCache) {
        this.pipelineCache = pipelineCache;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
