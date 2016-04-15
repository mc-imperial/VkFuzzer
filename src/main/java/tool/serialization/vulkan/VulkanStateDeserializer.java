package tool.serialization.vulkan;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.device.DevicePropertiesConfig;
import tool.configs.vulkan.device.DevicesConfig;
import tool.configs.vulkan.device.VkCreateDeviceConfig;
import tool.configs.vulkan.enumeration.VkEnumerateInstanceExtensionPropertiesConfig;
import tool.configs.vulkan.enumeration.VkEnumerateInstanceLayerPropertiesConfig;
import tool.configs.vulkan.enumeration.VkEnumeratePhysicalDevicesConfig;
import tool.configs.vulkan.instance.VkApplicationInfoConfig;
import tool.configs.vulkan.instance.VkCreateInstanceConfig;
import tool.configs.vulkan.instance.VkInstanceCreateInfoConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.serialization.RuntimeTypeAdapterFactory;
import tool.serialization.StateDeserializer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by constantinos on 01/04/2016.
 * Deserializes a string into the collection and config primitives
 */
public class VulkanStateDeserializer implements StateDeserializer {
    private final Gson gson;
    private final ObjectMapper objectMapper;

    public VulkanStateDeserializer() {
        RuntimeTypeAdapterFactory<Config> adapter =
                RuntimeTypeAdapterFactory
                        .of(Config.class)
                        .registerSubtype(VkCreateCommandPoolConfig.class)
                        .registerSubtype(DevicePropertiesConfig.class)
                        .registerSubtype(DevicesConfig.class)
                        .registerSubtype(VkCreateDeviceConfig.class)
                .registerSubtype(VkEnumerateInstanceExtensionPropertiesConfig.class)
                .registerSubtype(VkEnumerateInstanceLayerPropertiesConfig.class)
                .registerSubtype(VkEnumeratePhysicalDevicesConfig.class)
                .registerSubtype(VkApplicationInfoConfig.class)
                .registerSubtype(VkCreateInstanceConfig.class)
                .registerSubtype(VkInstanceCreateInfoConfig.class);

        gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(adapter).create();
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
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

            globalState =
                    objectMapper.readValue(toBeDecoded, VulkanGlobalState.class);
            //globalState = gson.fromJson(toBeDecoded, VulkanGlobalState.class);
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

            TypeReference<ArrayList<VulkanState>> typeReference =
                    new TypeReference<ArrayList<VulkanState>>(){};

            ArrayList<VulkanState> bla =
                    objectMapper.readValue(toBeDecoded, typeReference);
            visitedStates = bla;
            //visitedStates = gson.fromJson(toBeDecoded, visitedStates.getClass());
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }

        return visitedStates;
    }
}
