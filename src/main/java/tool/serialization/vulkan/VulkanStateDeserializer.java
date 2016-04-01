package tool.serialization.vulkan;

import com.fasterxml.jackson.databind.ObjectMapper;
import tool.configs.Config;
import tool.fsm.vulkan.states.VulkanState;
import tool.serialization.StateDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

/**
 * Created by constantinos on 01/04/2016.
 * Deserializes a string into the collection and config primitives
 */
public class VulkanStateDeserializer implements StateDeserializer {
    private final ObjectMapper objectMapper;

    public VulkanStateDeserializer() {
        objectMapper = new ObjectMapper();
    }

    // Deserializes a string from Base64 to a collection of Config primitives
    public HashMap<VulkanState, ArrayList<Config>> deserialize(String content) {
        HashMap<VulkanState, ArrayList<Config>> configs = new HashMap<>();

        try {
            String decoded = new String(Base64.getDecoder().decode(content),
                    "UTF-8");
            configs = objectMapper.readValue(decoded,
                            new HashMap<VulkanState, ArrayList<Config>>().getClass());
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }

        return configs;
    }
}
