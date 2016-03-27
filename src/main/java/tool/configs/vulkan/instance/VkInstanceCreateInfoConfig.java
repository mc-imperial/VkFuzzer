package tool.configs.vulkan.instance;

import tool.configs.Config;

/**
 * Created by constantinos on 26/03/2016.
 */
public class VkInstanceCreateInfoConfig extends Config {
    private String variableName;
    private String type;
    private String next;
    private String flags;
    private String applicationInfo;
    private String enabledExtensionNames;
    private String enabledLayerNames;
    private int enabledLayerCount;
    private int enabledExtensionCount;

    public VkInstanceCreateInfoConfig() {

    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(String applicationInfo) {
        this.applicationInfo = applicationInfo;
    }

    public String getEnabledExtensionNames() {
        return enabledExtensionNames;
    }

    public void setEnabledExtensionNames(String enabledExtensionNames) {
        this.enabledExtensionNames = enabledExtensionNames;
    }

    public String getEnabledLayerNames() {
        return enabledLayerNames;
    }

    public void setEnabledLayerNames(String enabledLayerNames) {
        this.enabledLayerNames = enabledLayerNames;
    }

    public int getEnabledLayerCount() {
        return enabledLayerCount;
    }

    public void setEnabledLayerCount(int enabledLayerCount) {
        this.enabledLayerCount = enabledLayerCount;
    }

    public int getEnabledExtensionCount() {
        return enabledExtensionCount;
    }

    public void setEnabledExtensionCount(int enabledExtensionCount) {
        this.enabledExtensionCount = enabledExtensionCount;
    }
}
