package tool.serialization.vulkan;

import com.google.gson.Gson;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.vulkan.states.VulkanState;
import tool.serialization.StateSerializer;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by constantinos on 31/03/2016.
 * Serializes a collection of configs
 */
public class VulkanStateSerializer implements StateSerializer {
    private final Gson gson;

    public VulkanStateSerializer() {
        gson = new Gson();
    }

    // Serializes the state into Base64
    public void serializeState(final VulkanGlobalState globalState,
                               final Writer writer) {
        try {
            writer.append(VulkanSerializationConstants.START_CONFIGS);
            writer.append(gson.toJson(globalState));
            writer.append(VulkanSerializationConstants.STOP_CONFIGS);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    // Serializes visited states so that the program can be generated in order in the future
    public void serializeVisitedStates(final ArrayList<VulkanState> states, final Writer writer) {
        try {
            writer.append(VulkanSerializationConstants.START_VISITED_STATES);
            writer.append(gson.toJson(states));
            writer.append(VulkanSerializationConstants.STOP_VISITED_STATES);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
