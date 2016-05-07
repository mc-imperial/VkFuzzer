package tool.configs.vulkan.swapchain;

import tool.configs.Config;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkAcquireNextImageKHRConfig extends Config {
    private String nextImage;
    private String device;
    private String swapchain;
    private String result;

    public String getNextImage() {
        return nextImage;
    }

    public void setNextImage(String nextImage) {
        this.nextImage = nextImage;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSwapchain() {
        return swapchain;
    }

    public void setSwapchain(String swapchain) {
        this.swapchain = swapchain;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
