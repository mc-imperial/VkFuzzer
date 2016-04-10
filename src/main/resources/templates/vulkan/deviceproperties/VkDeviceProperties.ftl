
    // ID: ${config.id}
    <#list config.devices as device>
    std::vector<VkPhysicalDeviceMemoryProperties> ${device.deviceMemoryProperties};
    std::vector<VkPhysicalDeviceFeatures> ${device.deviceFeatures};
    std::vector<VkPhysicalDeviceProperties> ${device.deviceProperties};
    std::vector<std::vector<VkQueueFamilyProperties>> ${device.deviceQueueFamilyProperties};
    std::vector<std::vector<VkExtensionProperties>> ${device.deviceExtensionProperties};
    std::vector<std::vector<VkLayerProperties>> ${device.deviceLayerProperties};

    for (VkPhysicalDevice device : ${device.devices})
    {
        VkPhysicalDeviceMemoryProperties memoryProperties;
        VkPhysicalDeviceFeatures features;
        VkPhysicalDeviceProperties properties;
        std::vector<VkQueueFamilyProperties> queueFamilyProperties;
        std::vector<VkExtensionProperties> extensions;
        std::vector<VkLayerProperties> layers;

        vkGetPhysicalDeviceMemoryProperties(device, &memoryProperties);
        vkGetPhysicalDeviceFeatures(device, &features);
        vkGetPhysicalDeviceProperties(device, &properties);

        // Enumerate device family queues
        VkResult result;
        do
        {
            uint32_t queueCount = 0;
            result = vkGetPhysicalDeviceQueueFamilyProperties(device, &queueCount, NULL);

            // Check that the return code is one of the acceptable ones
            assert((result == VK_SUCCESS)
                    || (result == VK_ERROR_OUT_OF_HOST_MEMORY)
                    || (result == VK_ERROR_OUT_OF_DEVICE_MEMORY));

            if(result != VK_SUCCESS)
            {
                std::cerr << "Failed to enumerate device family queue properties."
                        << "Error code " << result << "." << std::endl;
                return 0;
            }

            if(queueCount == 0)
            {
                std::cout << "No device family queues found." << std::endl;
                break;
            }

            queueFamilyProperties.resize(queueCount);

            result = vkGetPhysicalDeviceQueueFamilyProperties(device,
                    &queueCount, queueFamilyProperties.data());

            // Check that the return code is one of the acceptable ones
            assert((result== VK_SUCCESS)
                    || (result == VK_INCOMPLETE)
                    || (result == VK_ERROR_OUT_OF_HOST_MEMORY)
                    || (result == VK_ERROR_OUT_OF_DEVICE_MEMORY));
        }
        while(result == VK_INCOMPLETE);

        if(result != VK_SUCCESS)
        {
            std::cerr << "Failed to enumerate device family queue properties."
                    << "Error code " << result << "." << std::endl;
            return 0;
        }

        // All Vulkan implementations must expose at least one queue,
        // each of which has at least one queue capability bit set.
        for (VkQueueFamilyProperties vkQueueFamilyproperties : queueFamilyProperties)
        {
            assert(vkQueueFamilyproperties.queueCount > 0);

            assert((vkQueueFamilyproperties.queueFlags & VK_QUEUE_GRAPHICS_BIT)
                    || (vkQueueFamilyproperties.queueFlags & VK_QUEUE_COMPUTE_BIT)
                    || (vkQueueFamilyproperties.queueFlags & VK_QUEUE_TRANSFER_BIT)
                    || (vkQueueFamilyproperties.queueFlags & VK_QUEUE_SPARSE_BINDING_BIT));

            // Allow only valid queue bits
            assert((vkQueueFamilyproperties.queueFlags >= VK_QUEUE_GRAPHICS_BIT)
                    && (vkQueueFamilyproperties.queueFlags <= VK_QUEUE_SPARSE_BINDING_BIT)
                    && (vkQueueFamilyproperties.queueFlags != 0x00000003)
                    && (vkQueueFamilyproperties.queueFlags != 0x00000005)
                    && (vkQueueFamilyproperties.queueFlags != 0x00000006)
                    && (vkQueueFamilyproperties.queueFlags != 0x00000007));
        }

        // Enumerate extensions
        do
        {
            uint32_t extensionCount = 0;
            result = vkEnumerateDeviceExtensionProperties(device, NULL, &extensionCount, NULL);

            // Check that the return code is one of the acceptable ones
            assert((result == VK_SUCCESS)
                    || (result == VK_ERROR_OUT_OF_HOST_MEMORY)
                    || (result == VK_ERROR_OUT_OF_DEVICE_MEMORY));

            if(result != VK_SUCCESS)
            {
                std::cerr << "Failed to enumerate device extension properties."
                        << "Error code " << result << "." << std::endl;
                return 0;
            }

            if(extensionCount == 0)
            {
                std::cout << "No instance layers found." << std::endl;
                break;
            }

            extensions.resize(extensionCount);

            result = vkEnumerateDeviceExtensionProperties(device, NULL,
                    &extensionCount, extensions.data());

            // Check that the return code is one of the acceptable ones
            assert((result== VK_SUCCESS)
                    || (result == VK_INCOMPLETE)
                    || (result == VK_ERROR_OUT_OF_HOST_MEMORY)
                    || (result == VK_ERROR_OUT_OF_DEVICE_MEMORY));
        }
        while(result == VK_INCOMPLETE);

        if(result != VK_SUCCESS)
        {
            std::cerr << "Failed to enumerate device extension properties."
                    << "Error code " << result << "." << std::endl;
            return 0;
        }

        // Enumerate layers
        do
        {
            uint32_t layerCount = 0;
            result = vkEnumerateDeviceLayerProperties(device, &layerCount, NULL);

            // Check that the return code is one of the acceptable ones
            assert((result == VK_SUCCESS)
                    || (result == VK_ERROR_OUT_OF_HOST_MEMORY)
                    || (result == VK_ERROR_OUT_OF_DEVICE_MEMORY));

            if(result != VK_SUCCESS)
            {
                std::cerr << "Failed to enumerate device layer properties."
                        << "Error code " << result << "." << std::endl;
                return 0;
            }

            if(extensionCount == 0)
            {
                std::cout << "No instance layers found." << std::endl;
                break;
            }

            layers.resize(layerCount);

            result = vkEnumerateDeviceLayerProperties(device, &layerCount, layers.data());

            // Check that the return code is one of the acceptable ones
            assert((result== VK_SUCCESS)
                    || (result == VK_INCOMPLETE)
                    || (result == VK_ERROR_OUT_OF_HOST_MEMORY)
                    || (result == VK_ERROR_OUT_OF_DEVICE_MEMORY));
        }
        while(result == VK_INCOMPLETE);

        if(result != VK_SUCCESS)
        {
            std::cerr << "Failed to enumerate device layer properties."
                    << "Error code " << result << "." << std::endl;
            return 0;
        }

        ${device.deviceMemoryProperties}.push_back(memoryProperties);
        ${device.deviceFeatures}.push_back(features);
        ${device.deviceProperties}.push_back(properties);
        ${device.deviceQueueFamilyProperties}.push_back(queueFamilyProperties);
        ${device.deviceExtensionProperties}.push_back(extensions);
        ${device.deviceLayerProperties}.push_back(layers);
    }

    </#list>
