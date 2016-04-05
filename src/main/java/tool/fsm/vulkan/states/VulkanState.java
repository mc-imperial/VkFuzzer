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

    STOP
}
