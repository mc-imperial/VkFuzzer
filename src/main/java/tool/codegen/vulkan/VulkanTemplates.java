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
}
