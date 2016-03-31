package tool.configs.vulkan.instance;

import tool.codegen.vulkan.VulkanReturnCodes;
import tool.configs.Config;

/**
 * Created by constantinos on 26/03/2016.
 */
public class VkCreateInstanceConfig extends Config {
    private String instanceName;
    private String instanceCreateInfo;
    private String result;
    private String alloc;
    private String expected = VulkanReturnCodes.VK_SUCCESS;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getInstanceCreateInfo() {
        return instanceCreateInfo;
    }

    public void setInstanceCreateInfo(String instanceCreateInfo) {
        this.instanceCreateInfo = instanceCreateInfo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAlloc() {
        return alloc;
    }

    public void setAlloc(String alloc) {
        this.alloc = alloc;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }
}
