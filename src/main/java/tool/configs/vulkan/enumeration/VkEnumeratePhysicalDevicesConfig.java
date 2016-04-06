package tool.configs.vulkan.enumeration;

import tool.configs.Config;

import java.util.ArrayList;

/**
 * Created by constantinos on 06/04/2016.
 */
public class VkEnumeratePhysicalDevicesConfig extends Config {
    private String gpus;
    private String gpuCount;
    private String result;
    private String instance;
    private String returnCode;
    private boolean checkOther;
    private ArrayList<String> otherDeviceVectors;

    public VkEnumeratePhysicalDevicesConfig() {
        otherDeviceVectors = new ArrayList<>();
    }

    public String getGpus() {
        return gpus;
    }

    public void setGpus(String gpus) {
        this.gpus = gpus;
    }

    public String getGpuCount() {
        return gpuCount;
    }

    public void setGpuCount(String gpuCount) {
        this.gpuCount = gpuCount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public boolean isCheckOther() {
        return checkOther;
    }

    public void setCheckOther(boolean checkOther) {
        this.checkOther = checkOther;
    }

    public ArrayList<String> getOtherDeviceVectors() {
        return otherDeviceVectors;
    }

    public void setOtherDeviceVectors(ArrayList<String> otherDeviceVectors) {
        this.otherDeviceVectors = otherDeviceVectors;
    }
}
