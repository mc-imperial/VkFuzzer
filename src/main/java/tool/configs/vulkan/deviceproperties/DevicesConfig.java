package tool.configs.vulkan.deviceproperties;

import tool.configs.Config;

import java.util.ArrayList;

/**
 * Created by constantinos on 08/04/2016.
 */
public class DevicesConfig extends Config {
    private ArrayList<Config> devices;

    public DevicesConfig() {
        devices = new ArrayList<>();
    }

    public ArrayList<Config> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Config> devices) {
        this.devices = devices;
    }
}
