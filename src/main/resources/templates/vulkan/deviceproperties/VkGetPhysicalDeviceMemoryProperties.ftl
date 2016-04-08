
    // ID: ${config.id}
    std::vector<VkPhysicalDeviceMemoryProperties> deviceMemoryProperties;
    std::vector<VkPhysicalDeviceFeatures> deviceFeatures;
    std::vector<VkPhysicalDeviceProperties> deviceProperties;
    std::vector<VkPhysicalDeviceMemoryProperties> memoryProperties;

    for (VkPhysicalDevice device : ${config.devices})
    {
        VkPhysicalDeviceMemoryProperties memoryProperties;
        VkPhysicalDeviceFeatures features;
        VkPhysicalDeviceProperties properties;

        vkGetPhysicalDeviceMemoryProperties(device, &memoryProperties);
        vkGetPhysicalDeviceFeatures(device, &features);
        vkGetPhysicalDeviceProperties(device, &properties);

        deviceMemoryProperties.push_back(memoryProperties);
        deviceFeatures.push_back(features);
        deviceProperties.push_back(properties);
    }