package tool.serialization.vulkan;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import tool.configs.GlobalState;
import tool.serialization.StateSerializer;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by constantinos on 31/03/2016.
 * Serializes a collection of configs
 */
public class VulkanStateSerializer implements StateSerializer {
    private final ObjectMapper objectMapper;

    public VulkanStateSerializer() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
    }

    // Serializes the state into Base64
    public void serializeState(final GlobalState globalState,
                               final Writer writer) {
        try {
            writer.append(VulkanSerializationConstants.START_CONFIGS);

            // Avoid nulls
            if (globalState != null) {
                objectMapper.writeValue(writer, globalState);
            }

            writer.append(VulkanSerializationConstants.STOP_CONFIGS);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    // Serializes visited states so that the program can be generated in order in the future
    public void serializeVisitedStates(final ArrayList<String> states, final Writer writer) {
        try {
            writer.append(VulkanSerializationConstants.START_VISITED_STATES);

            // Avoid nulls
            if (states != null) {
                objectMapper.writeValue(writer, states);
            }

            writer.append(VulkanSerializationConstants.STOP_VISITED_STATES);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
