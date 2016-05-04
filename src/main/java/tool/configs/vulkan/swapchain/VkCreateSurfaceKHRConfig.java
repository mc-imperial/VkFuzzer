package tool.configs.vulkan.swapchain;

import tool.configs.Config;

/**
 * Created by Constantinos on 04/05/2016.
 */
public class VkCreateSurfaceKHRConfig extends Config {
    private String result;
    private String surface;
    private String className;
    private String windowClass;
    private String surfaceCreateInfo;
    private String instance;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getWindowClass() {
        return windowClass;
    }

    public void setWindowClass(String windowClass) {
        this.windowClass = windowClass;
    }

    public String getSurfaceCreateInfo() {
        return surfaceCreateInfo;
    }

    public void setSurfaceCreateInfo(String surfaceCreateInfo) {
        this.surfaceCreateInfo = surfaceCreateInfo;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}
