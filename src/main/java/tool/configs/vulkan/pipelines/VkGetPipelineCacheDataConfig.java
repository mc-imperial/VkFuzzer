package tool.configs.vulkan.pipelines;

import tool.configs.Config;

/**
 * Created by kyriaki on 30/04/2016.
 */
public class VkGetPipelineCacheDataConfig extends Config {
    private String pipelineCacheData;
    private String size;
    private String device;
    private String pipelineCache;
    private String result;

    public String getPipelineCacheData() {
        return pipelineCacheData;
    }

    public void setPipelineCacheData(String pipelineCacheData) {
        this.pipelineCacheData = pipelineCacheData;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
