
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
        uint32_t queueCount = 0;
        vkGetPhysicalDeviceQueueFamilyProperties(device, &queueCount, NULL);

        queueFamilyProperties.resize(queueCount);

        vkGetPhysicalDeviceQueueFamilyProperties(device, &queueCount, queueFamilyProperties.data());

        // Eumerate extesions
        uint32_t extensionCount = 0;
        vkEnumerateDeviceExtensionProperties(device, NULL, &extensionCount, NULL);

        extensions.resize(extensionCount);

        vkEnumerateDeviceExtensionProperties(device, NULL, &extensionCount, extensions.data());

        // Enumerate layers
        uint32_t layerCount = 0;
        vkEnumerateDeviceLayerProperties(device, &layerCount, NULL);

        layers.resize(layerCount);

        vkEnumerateDeviceLayerProperties(device, &layerCount, layers.data());

        ${device.deviceMemoryProperties}.push_back(memoryProperties);
        ${device.deviceFeatures}.push_back(features);
        ${device.deviceProperties}.push_back(properties);
        ${device.deviceQueueFamilyProperties}.push_back(queueFamilyProperties);
        ${device.deviceExtensionProperties}.push_back(extensions);
        ${device.deviceLayerProperties}.push_back(layers);
    }

    </#list>
