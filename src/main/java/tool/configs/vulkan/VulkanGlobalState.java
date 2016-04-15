package tool.configs.vulkan;

import tool.configs.Config;
import tool.configs.GlobalState;
import tool.fsm.vulkan.states.VulkanState;

import java.util.ArrayList;

/**
 * Created by constantinos on 26/03/2016.
 * A vulkan specific GlobalState object
 */
public class VulkanGlobalState extends GlobalState {
    public VulkanGlobalState() {
        super();
    }

    // Adds a config
    public void addConfig(final VulkanState state, final Config config) {
        ArrayList<Config> stateConfigs = configs.getOrDefault(state.toString(),
                new ArrayList<>());
        stateConfigs.add(config);
        configs.putIfAbsent(state.toString(), stateConfigs);
    }

    // Returns the array of configs
    public ArrayList<Config> getConfig(final VulkanState state) {
        ArrayList<Config> stateConfigs = configs.getOrDefault(state.toString(),
                new ArrayList<>());
        configs.putIfAbsent(state.toString(), stateConfigs);
        return stateConfigs;
    }}
