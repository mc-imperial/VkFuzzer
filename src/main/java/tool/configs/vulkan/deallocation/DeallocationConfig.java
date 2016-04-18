package tool.configs.vulkan.deallocation;

import tool.configs.Config;

import java.util.ArrayList;

/**
 * Created by constantinos on 18/04/2016.
 */
public class DeallocationConfig extends Config {
    private ArrayList<Config> instances;
    private ArrayList<Config> devices;
    private ArrayList<Config> cmdPools;
    private ArrayList<Config> cmdBuffers;

    public ArrayList<Config> getInstances() {
        return instances;
    }

    public void setInstances(ArrayList<Config> instances) {
        this.instances = instances;
    }

    public ArrayList<Config> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Config> devices) {
        this.devices = devices;
    }

    public ArrayList<Config> getCmdPools() {
        return cmdPools;
    }

    public void setCmdPools(ArrayList<Config> cmdPools) {
        this.cmdPools = cmdPools;
    }

    public ArrayList<Config> getCmdBuffers() {
        return cmdBuffers;
    }

    public void setCmdBuffers(ArrayList<Config> cmdBuffers) {
        this.cmdBuffers = cmdBuffers;
    }
}
