package tool.configs.vulkan;

import tool.configs.Config;

import java.util.ArrayList;

/**
 * Created by constantinos on 26/03/2016.
 */
public class VulkanGlobalState {
    private ArrayList<Config> configs;

    public VulkanGlobalState() {
        configs = new ArrayList<>();
    }

    public ArrayList<Config> getConfigs() {
        return configs;
    }

    public void setConfigs(ArrayList<Config> configs) {
        this.configs = configs;
    }

    public void addConfig(Config config) {
        configs.add(config);
    }
}
