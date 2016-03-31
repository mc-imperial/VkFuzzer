package tool.configs.vulkan;

import tool.configs.Config;
import tool.fsm.vulkan.states.VulkanState;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by constantinos on 26/03/2016.
 */
public class VulkanGlobalState {
    private HashMap<VulkanState, ArrayList<Config>> configs;

    public VulkanGlobalState() {
        configs = new HashMap<>();
    }

    // Adds a config
    public void addConfig(final VulkanState state, final Config config) {
        ArrayList<Config> stateConfigs = configs.getOrDefault(state,
                new ArrayList<>());
        stateConfigs.add(config);
        configs.put(state, stateConfigs);
    }

    // Returns the array of configs
    public ArrayList<Config> getConfig(final VulkanState state) {
        ArrayList<Config> stateConfigs = configs.getOrDefault(state,
                new ArrayList<>());
        configs.putIfAbsent(state, stateConfigs);
        return stateConfigs;
    }

    public HashMap<VulkanState, ArrayList<Config>> getConfigs() {
        return configs;
    }
}
