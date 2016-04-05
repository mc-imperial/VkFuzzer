package tool.configs.vulkan.enumeration;

import tool.configs.Config;

/**
 * Created by constantinos on 05/04/2016.
 */
public class VkEnumerateInstanceExtensionPropertiesConfig extends Config {
    private String instanceExtensionProperties;
    private String instanceExtensionNames;
    private String instanceExtensionCount;
    private String result;

    public String getInstanceExtensionProperties() {
        return instanceExtensionProperties;
    }

    public void setInstanceExtensionProperties(String instanceExtensionProperties) {
        this.instanceExtensionProperties = instanceExtensionProperties;
    }

    public String getInstanceExtensionNames() {
        return instanceExtensionNames;
    }

    public void setInstanceExtensionNames(String instanceExtensionNames) {
        this.instanceExtensionNames = instanceExtensionNames;
    }

    public String getInstanceExtensionCount() {
        return instanceExtensionCount;
    }

    public void setInstanceExtensionCount(String instanceExtensionCount) {
        this.instanceExtensionCount = instanceExtensionCount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
