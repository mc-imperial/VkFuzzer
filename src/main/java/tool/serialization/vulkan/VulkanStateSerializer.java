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
import tool.serialization.StateSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by constantinos on 31/03/2016.
 * Serializes a collection of configs
 */
public class VulkanStateSerializer implements StateSerializer {
    private final Gson gson;
    private final ObjectMapper objectMapper;

    public VulkanStateSerializer() {
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

    // Serializes the state into Base64
    public void serializeState(final VulkanGlobalState globalState,
                               final Writer writer) {
        try {
            writer.append(VulkanSerializationConstants.START_CONFIGS);

            objectMapper.writeValue(writer, globalState);


            // writer.append(gson.toJson(globalState));

            writer.append(VulkanSerializationConstants.STOP_CONFIGS);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    // Serializes visited states so that the program can be generated in order in the future
    public void serializeVisitedStates(final ArrayList<VulkanState> states, final Writer writer) {
        try {
            writer.append(VulkanSerializationConstants.START_VISITED_STATES);

            objectMapper.writeValue(writer,states);

            // writer.append(gson.toJson(states));

            writer.append(VulkanSerializationConstants.STOP_VISITED_STATES);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
