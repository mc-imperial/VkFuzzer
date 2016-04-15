package tool.serialization.vulkan;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import tool.configs.GlobalState;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.vulkan.states.VulkanState;
import tool.serialization.StateDeserializer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by constantinos on 01/04/2016.
 * Deserializes a string into the collection and config primitives
 */
public class VulkanStateDeserializer implements StateDeserializer {
    private final ObjectMapper objectMapper;

    public VulkanStateDeserializer() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
    }

    // Deserializes the config primitives of a metafile
    public GlobalState deserializeConfigs(final String metaFile) {
        GlobalState globalState = new GlobalState();

        try {
            // Extract file contents
            String toBeDecoded = getContent(metaFile,
                    VulkanSerializationConstants.START_CONFIGS,
                    VulkanSerializationConstants.STOP_CONFIGS);

            // Deserialize
            globalState = objectMapper.readValue(toBeDecoded, VulkanGlobalState.class);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }

        return globalState;
    }

    // Deserializes the visitedStates of a metafile
    public ArrayList<String> deserializeVisitedStates(final String metaFile) {
        ArrayList<String> visitedStates = new ArrayList<>();

        try {
            // Extract file contents
            String toBeDecoded = getContent(metaFile,
                    VulkanSerializationConstants.START_VISITED_STATES,
                    VulkanSerializationConstants.STOP_VISITED_STATES);

            // Deserialize
            TypeReference<ArrayList<VulkanState>> typeReference =
                    new TypeReference<ArrayList<VulkanState>>(){};
            ArrayList<VulkanState> deserializedStates =
                    objectMapper.readValue(toBeDecoded, typeReference);

            for (VulkanState state : deserializedStates) {
                visitedStates.add(state.toString());
            }
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }

        return visitedStates;
    }

    // Extracts the content we need from the metafile
    private String getContent(final String metaFile,
                              final String startSection,
                              final String endSection) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(metaFile));
        String contents = new String(encoded, Charset.defaultCharset());

        // Find the required sections from the file
        int baseIndex =
                contents.indexOf(startSection);
        int endIndex =
                contents.indexOf(endSection);

        return contents.substring(
                baseIndex + startSection.length(),
                endIndex);
    }
}
