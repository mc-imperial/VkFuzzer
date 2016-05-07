package tool.configs.vulkan.swapchain;

import tool.configs.Config;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class VkQueuePresentKHRConfig extends Config {
    private String presentInfo;
    private String swapchain;
    private String imageIndex;
    private String queue;
    private String result;

    public String getPresentInfo() {
        return presentInfo;
    }

    public void setPresentInfo(String presentInfo) {
        this.presentInfo = presentInfo;
    }

    public String getSwapchain() {
        return swapchain;
    }

    public void setSwapchain(String swapchain) {
        this.swapchain = swapchain;
    }

    public String getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(String imageIndex) {
        this.imageIndex = imageIndex;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }
}
