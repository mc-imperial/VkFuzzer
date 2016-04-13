package tool.serialization.vulkan;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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

/**
 * Created by constantinos on 26/03/2016.
 * A mixin to support config serialisation/deserialisation
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes(
{
        @JsonSubTypes.Type(value = VkApplicationInfoConfig.class,
                name = "VkApplicationInfoConfig"),
        @JsonSubTypes.Type(value = VkInstanceCreateInfoConfig.class,
                name = "VkInstanceCreateInfoConfig"),
        @JsonSubTypes.Type(value = VkCreateInstanceConfig.class,
                name = "VkCreateInstanceConfig"),
        @JsonSubTypes.Type(value = VkEnumerateInstanceExtensionPropertiesConfig.class,
                name = "VkEnumerateInstanceExtensionPropertiesConfig"),
        @JsonSubTypes.Type(value = VkEnumerateInstanceLayerPropertiesConfig.class,
                name = "VkEnumerateInstanceLayerPropertiesConfig"),
        @JsonSubTypes.Type(value = VkEnumeratePhysicalDevicesConfig.class,
                name = "VkEnumeratePhysicalDevicesConfig"),
        @JsonSubTypes.Type(value = DevicePropertiesConfig.class,
                name = "DevicePropertiesConfig"),
        @JsonSubTypes.Type(value = DevicesConfig.class,
                name = "DevicesConfig"),
        @JsonSubTypes.Type(value = VkCreateDeviceConfig.class,
                name = "VkCreateDeviceConfig"),
        @JsonSubTypes.Type(value = VkCreateCommandPoolConfig.class,
                name = "VkCreateCommandPoolConfig")
})
public abstract class PolymorphicVulkanConfigMixIn {
}
