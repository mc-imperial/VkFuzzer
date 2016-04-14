package tool.serialization.vulkan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.vulkan.states.VulkanState;
import tool.serialization.StateDeserializer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by constantinos on 01/04/2016.
 * Deserializes a string into the collection and config primitives
 */
public class VulkanStateDeserializer implements StateDeserializer {
    private final Gson gson;

    public VulkanStateDeserializer() {
        gson = new Gson();
    }

    // Deserializes a string from Base64 to a collection of Config primitives
    public VulkanGlobalState deserializeConfigs(final String metaFile) {
        VulkanGlobalState globalState = new VulkanGlobalState();

        try {
            byte[] encoded = Files.readAllBytes(Paths.get(metaFile));
            String contents = new String(encoded, Charset.defaultCharset());

            int baseIndex =
                    contents.indexOf(VulkanSerializationConstants.START_CONFIGS);
            int endIndex =
                    contents.indexOf(VulkanSerializationConstants.STOP_CONFIGS);

            String toBeDecoded = contents.substring(
                    baseIndex + VulkanSerializationConstants.START_CONFIGS.length(),
                    endIndex);

            globalState = gson.fromJson(toBeDecoded, VulkanGlobalState.class);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }

        return globalState;
    }

    // Deserializes a string from Base64 to a collection of Config primitives
    public ArrayList<VulkanState> deserializeVisitedStates(final String metaFile) {
        ArrayList<VulkanState> visitedStates = new ArrayList<>();

        try {
            byte[] encoded = Files.readAllBytes(Paths.get(metaFile));
            String contents = new String(encoded, Charset.defaultCharset());

            int baseIndex =
                    contents.indexOf(VulkanSerializationConstants.START_VISITED_STATES);
            int endIndex =
                    contents.indexOf(VulkanSerializationConstants.STOP_VISITED_STATES);

            String toBeDecoded = contents.substring(
                    baseIndex + VulkanSerializationConstants.START_VISITED_STATES.length(),
                    endIndex);

            visitedStates = gson.fromJson(toBeDecoded, visitedStates.getClass());
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }

        return visitedStates;
    }
}
