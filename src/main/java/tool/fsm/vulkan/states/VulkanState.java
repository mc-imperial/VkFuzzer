package tool.fsm.vulkan.states;

/**
 * Created by constantinos on 29/03/2016.
 */
public enum VulkanState {
    START,

    VK_ENUMERATE_INSTANCE_EXTENSION_PROPERTIES,
    VK_ENUMERATE_INSTANCE_LAYER_PROPERTIES,

    VK_APPLICATION_INFO,
    VK_INSTANCE_CREATE_INFO,
    VK_CREATE_INSTANCE,

    VK_ENUMERATE_PHYSICAL_DEVICES,
    GET_DEVICE_PROPERTIES,
    VK_CREATE_DEVICE,

    VK_CREATE_COMMAND_POOL,
    VK_ALLOCATE_COMMAND_BUFFERS,
    VK_GET_DEVICE_QUEUE,

    VK_CREATE_EVENT,
    VK_CREATE_FENCE,
    VK_CREATE_SEMAPHORE,
    VK_GET_EVENT_STATUS,
    VK_GET_FENCE_STATUS,
    VK_RESET_EVENT,
    VK_RESET_FENCES,
    VK_SET_EVENT,

    VK_CREATE_BUFFER,
    VK_CREATE_BUFFER_VIEW,
    VK_GET_BUFFER_MEMORY_REQUIREMENTS,

    VK_CREATE_IMAGE,
    VK_CREATE_IMAGE_VIEW,
    VK_GET_IMAGE_MEMORY_REQUIREMENTS,
    VK_GET_IMAGE_SUBRESOURCE_LAYOUT,

    VK_CREATE_SHADER_MODULE,
    VK_CREATE_RENDERPASS,
    VK_CREATE_FRAMEBUFFER,
    VK_CREATE_PIPELINE_CACHE,
    VK_CREATE_PIPELINE_LAYOUT,
    VK_CREATE_GRAPHICS_PIPELINES,
    VK_GET_PIPELINE_CACHE_DATA,

    VK_CREATE_SURFACE_KHR,
    INIT_SWAPCHAIN,

    DEALLOCATION,

    STOP
}
