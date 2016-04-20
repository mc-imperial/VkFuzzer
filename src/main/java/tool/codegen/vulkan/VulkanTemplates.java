package tool.codegen.vulkan;

/**
 * Created by constantinos on 30/03/2016.
 */
public class VulkanTemplates {
    public static final String TEMPLATE_FOLDER = "/templates/vulkan";
    public static final String SCRIPT_FOLDER = "/scripts";
    public static final String MAIN = "Main.ftl";

    public static final String VK_ENUMERATE_INSTANCE_EXTENSION_PROPERTIES =
            "enumeration/VkEnumerateInstanceExtensionProperties.ftl";
    public static final String VK_ENUMERATE_INSTANCE_LAYER_PROPERTIES =
            "enumeration/VkEnumerateInstanceLayerProperties.ftl";
    public static final String VK_APPLICATION_INFO =
            "instance/VkApplicationInfo.ftl";
    public static final String VK_INSTANCE_CREATE_INFO =
            "instance/VkInstanceCreateInfo.ftl";
    public static final String VK_CREATE_INSTANCE =
            "instance/VkCreateInstance.ftl";
    public static final String VK_ENUMERATE_PHYSICAL_DEVICES =
            "enumeration/VkEnumeratePhysicalDevices.ftl";
    public static final String GET_DEVICE_PROPERTIES =
            "device/VkDeviceProperties.ftl";
    public static final String VK_CREATE_DEVICE =
            "device/VkCreateDevice.ftl";
    public static final String VK_CREATE_COMMAND_POOL =
            "commandbuffers/VkCreateCommandPool.ftl";
    public static final String VK_ALLOCATE_COMMAND_BUFFERS =
            "commandbuffers/VkAllocateCommandBuffers.ftl";
    public static final String VK_GET_DEVICE_QUEUE =
            "device/VkGetDeviceQueue.ftl";
    public static final String DEALLOCATION =
            "deallocation/Deallocation.ftl";
    public static final String VK_CREATE_EVENT =
            "synchronisation/VkCreateEvent.ftl";
    public static final String VK_CREATE_FENCE =
            "synchronisation/VkCreateFence.ftl";
    public static final String VK_CREATE_SEMAPHORE =
            "synchronisation/VkCreateSemaphore.ftl";
    public static final String VK_GET_EVENT_STATUS =
            "synchronisation/VkGetEventStatus.ftl";
    public static final String VK_GET_FENCE_STATUS =
            "synchronisation/VkGetFenceStatus.ftl";
    public static final String VK_RESET_EVENT =
            "synchronisation/VkResetEvent.ftl";
    public static final String VK_RESET_FENCES =
            "synchronisation/VkResetFences.ftl";
    public static final String VK_SET_EVENT =
            "synchronisation/VkSetEvent.ftl";
}
