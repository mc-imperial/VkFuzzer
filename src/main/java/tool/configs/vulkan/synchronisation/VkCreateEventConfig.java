package tool.configs.vulkan.synchronisation;

import tool.configs.Config;

/**
 * Created by constantinos on 19/04/2016.
 */
public class VkCreateEventConfig extends Config {
    private String eventCreateInfo;
    private String event;
    private String result;
    private String device;

    public String getEventCreateInfo() {
        return eventCreateInfo;
    }

    public void setEventCreateInfo(String eventCreateInfo) {
        this.eventCreateInfo = eventCreateInfo;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
