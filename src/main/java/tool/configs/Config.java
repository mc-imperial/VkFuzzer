package tool.configs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import tool.configs.vulkan.commandbuffers.VkAllocateCommandBuffersConfig;
import tool.configs.vulkan.commandbuffers.VkBeginCommandBufferConfig;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.commandbuffers.VkEndCommandBufferConfig;
import tool.configs.vulkan.commands.DrawBuffersConfig;
import tool.configs.vulkan.commands.VkCmdClearColorImageConfig;
import tool.configs.vulkan.device.*;
import tool.configs.vulkan.enumeration.VkEnumerateInstanceExtensionPropertiesConfig;
import tool.configs.vulkan.enumeration.VkEnumerateInstanceLayerPropertiesConfig;
import tool.configs.vulkan.enumeration.VkEnumeratePhysicalDevicesConfig;
import tool.configs.vulkan.instance.VkApplicationInfoConfig;
import tool.configs.vulkan.instance.VkCreateInstanceConfig;
import tool.configs.vulkan.instance.VkInstanceCreateInfoConfig;
import tool.configs.vulkan.memory.PopulateVertexBufferConfig;
import tool.configs.vulkan.pipelines.VkCreateGraphicsPipelinesConfig;
import tool.configs.vulkan.pipelines.VkCreatePipelineCacheConfig;
import tool.configs.vulkan.pipelines.VkCreatePipelineLayoutConfig;
import tool.configs.vulkan.pipelines.VkGetPipelineCacheDataConfig;
import tool.configs.vulkan.resources.*;
import tool.configs.vulkan.shaders.VkCreateShaderModuleConfig;
import tool.configs.vulkan.swapchain.InitSwapchainConfig;
import tool.configs.vulkan.swapchain.VkAcquireNextImageKHRConfig;
import tool.configs.vulkan.swapchain.VkCreateSurfaceKHRConfig;
import tool.configs.vulkan.swapchain.VkQueuePresentKHRConfig;
import tool.configs.vulkan.synchronisation.*;
import tool.configs.vulkan.utils.DeviceExtensionsConfig;
import tool.configs.vulkan.utils.InstanceExtensionsConfig;
import tool.configs.vulkan.utils.RandomSeedConfig;

import java.util.ArrayList;

/**
 * Created by constantinos on 26/03/2016.
 * Base class for configs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY)
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
                name = "VkCreateCommandPoolConfig"),
        @JsonSubTypes.Type(value = VkAllocateCommandBuffersConfig.class,
                name = "VkAllocateCommandBuffers"),
        @JsonSubTypes.Type(value = VkCreateFenceConfig.class,
                name = "VkCreateFenceConfig"),
        @JsonSubTypes.Type(value = VkCreateEventConfig.class,
                name = "VkCreateEventConfig"),
        @JsonSubTypes.Type(value = VkCreateSemaphoreConfig.class,
                name = "VkCreateSemaphoreConfig"),
        @JsonSubTypes.Type(value = VkGetEventStatusConfig.class,
                name = "VkGetEventStatusConfig"),
        @JsonSubTypes.Type(value = VkGetFenceStatusConfig.class,
                name = "VkGetFenceStatusConfig"),
        @JsonSubTypes.Type(value = VkResetFencesConfig.class,
                name = "VkResetFencesConfig"),
        @JsonSubTypes.Type(value = VkResetEventConfig.class,
                name = "VkResetEventConfig"),
        @JsonSubTypes.Type(value = VkSetEventConfig.class,
                name = "VkSetEventConfig"),
        @JsonSubTypes.Type(value = VkGetDeviceQueueConfig.class,
                name = "VkGetDeviceQueueConfig"),
        @JsonSubTypes.Type(value = VkCreateBufferConfig.class,
                name = "VkCreateBufferConfig"),
        @JsonSubTypes.Type(value = VkCreateBufferViewConfig.class,
                name = "VkCreateBufferViewConfig"),
        @JsonSubTypes.Type(value = VkCreateImageConfig.class,
                name = "VkCreateImageConfig"),
        @JsonSubTypes.Type(value = VkCreateImageViewConfig.class,
                name = "VkCreateImageViewConfig"),
        @JsonSubTypes.Type(value = VkGetBufferMemoryRequirementsConfig.class,
                name = "VkGetBufferMemoryRequirementsConfig"),
        @JsonSubTypes.Type(value = VkGetImageMemoryRequirementsConfig.class,
                name = "VkGetImageMemoryRequirementsConfig"),
        @JsonSubTypes.Type(value = VkGetImageSubResourceLayoutConfig.class,
                name = "VkGetImageSubResourceLayoutConfig"),
        @JsonSubTypes.Type(value = VkCreateShaderModuleConfig.class,
                name = "VkCreateShaderModuleConfig"),
        @JsonSubTypes.Type(value = VkCreateRenderpassConfig.class,
                name = "VkCreateRenderpassConfig"),
        @JsonSubTypes.Type(value = VkCreateFrameBufferConfig.class,
                name = "VkCreateFrameBufferConfig"),
        @JsonSubTypes.Type(value = VkCreateGraphicsPipelinesConfig.class,
                name = "VkCreateGraphicsPipelinesConfig"),
        @JsonSubTypes.Type(value = VkCreatePipelineCacheConfig.class,
                name = "VkCreatePipelineCacheConfig"),
        @JsonSubTypes.Type(value = VkCreatePipelineLayoutConfig.class,
                name = "VkCreatePipelineLayoutConfig"),
        @JsonSubTypes.Type(value = VkGetPipelineCacheDataConfig.class,
                name = "VkGetPipelineCacheDataConfig"),
        @JsonSubTypes.Type(value = VkBeginCommandBufferConfig.class,
                name = "VkBeginCommandBufferConfig"),
        @JsonSubTypes.Type(value = VkEndCommandBufferConfig.class,
                name = "VkEndCommandBufferConfig"),
        @JsonSubTypes.Type(value = DrawBuffersConfig.class,
                name = "DrawBuffersConfig"),
        @JsonSubTypes.Type(value = VkCmdClearColorImageConfig.class,
                name = "VkClearColorImageConfig"),
        @JsonSubTypes.Type(value = SelectDeviceConfig.class,
                name = "SelectDeviceConfig"),
        @JsonSubTypes.Type(value = VkDeviceWaitIdleConfig.class,
                name = "VkDeviceWaitIdleConfig"),
        @JsonSubTypes.Type(value = VkQueueSubmitConfig.class,
                name = "VkQueueSubmitConfig"),
        @JsonSubTypes.Type(value = PopulateVertexBufferConfig.class,
                name = "PopulateVertexBufferConfig"),
        @JsonSubTypes.Type(value = VkCmdBeginRenderPassConfig.class,
                name = "VkCmdBeginRenderPassConfig"),
        @JsonSubTypes.Type(value = VkCreateFrameBufferConfig.class,
                name = "VkCreateFrameBufferConfig"),
        @JsonSubTypes.Type(value = InitSwapchainConfig.class,
                name = "InitSwapchainConfig"),
        @JsonSubTypes.Type(value = VkAcquireNextImageKHRConfig.class,
                name = "VkAcquireNextImageKHRConfig"),
        @JsonSubTypes.Type(value = VkCreateSurfaceKHRConfig.class,
                name = "VkCreateSurfaceKHRConfig"),
        @JsonSubTypes.Type(value = VkQueuePresentKHRConfig.class,
                name = "VkQueuePresentKHRConfig"),
        @JsonSubTypes.Type(value = DeviceExtensionsConfig.class,
                name = "DeviceExtensionsConfig"),
        @JsonSubTypes.Type(value = InstanceExtensionsConfig.class,
                name = "InstanceExtensionsConfig"),
        @JsonSubTypes.Type(value = RandomSeedConfig.class,
                name = "RandomSeedConfig")
})
public class Config {
    private ArrayList<Integer> dependencies;
    private int id;
    private boolean isBad;

    public Config() {
        dependencies = new ArrayList<>();
        isBad = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getDependencies() {
        return dependencies;
    }

    public void addDependency(int id) { this.dependencies.add(id); }

    public void setDependencies(ArrayList<Integer> dependencies) {
        this.dependencies = dependencies;
    }

    public boolean isBad() {
        return isBad;
    }

    public void setBad(boolean bad) {
        isBad = bad;
    }
}
