package tool.configs.vulkan.enumeration;

import tool.configs.Config;

/**
 * Created by constantinos on 05/04/2016.
 */
public class VkEnumerateInstanceLayerPropertiesConfig extends Config {
    private String instanceLayerProperties;
    private String instanceLayerNames;
    private String instanceLayerCount;
    private String result;

    public String getInstanceLayerProperties() {
        return instanceLayerProperties;
    }

    public void setInstanceLayerProperties(String instanceLayerProperties) {
        this.instanceLayerProperties = instanceLayerProperties;
    }

    public String getInstanceLayerNames() {
        return instanceLayerNames;
    }

    public void setInstanceLayerNames(String instanceLayerNames) {
        this.instanceLayerNames = instanceLayerNames;
    }

    public String getInstanceLayerCount() {
        return instanceLayerCount;
    }

    public void setInstanceLayerCount(String instanceLayerCount) {
        this.instanceLayerCount = instanceLayerCount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
