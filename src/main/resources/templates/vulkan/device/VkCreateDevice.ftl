
    //ID: ${config.id}
    <#list config.devicePropertiesConfigs as devicePropertiesConfig>
    <#list devicePropertiesConfig.devices as deviceProperties>
    <#if deviceProperties.isBad()>
    <#else>
    std::vector<VkDevice> logicalDevices;

    for (std::vector<VkQueueFamilyProperties> vkQueueFamilyProperties :
            ${deviceProperties.deviceQueueFamilyProperties})
    {
        VkDeviceQueueCreateInfo queueInfo = {};

        bool found = false;
        for (unsigned int i = 0; i < info.queue_count; i++)
        {
            if (info.queue_props[i].queueFlags & VK_QUEUE_GRAPHICS_BIT)
            {
                queueInfo.queueFamilyIndex = i;
                found = true;
                break;
            }
        }

        // If no graphics queue found then stop
        assert(found);

        std::vector<float> queuePriorities;
        queuePriorities.resize(1);

        for (int i = 0; i < queuePriorities.size(); ++i)
        {
            queuePriorities[i] = static_cast<float>(rand()) / static_cast<float>(RAND_MAX);
        }

        queueInfo.sType = VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO;
        queueInfo.pNext = NULL;
        queueInfo.queueCount = 1;
        queueInfo.pQueuePriorities = queuePriorities;

        VkDeviceCreateInfo deviceInfo = {};
        deviceInfo.sType = VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO;
        deviceInfo.pNext = NULL;
        deviceInfo.queueCreateInfoCount = 1;
        deviceInfo.pQueueCreateInfos = &queueInfo;
        deviceInfo.enabledExtensionCount = 0;
        deviceInfo.ppEnabledExtensionNames = NULL;
        deviceInfo.enabledLayerCount = 0;
        deviceInfo.ppEnabledLayerNames = NULL;
        deviceInfo.pEnabledFeatures = NULL;
        VkDevice device;
        VkResult res = vkCreateDevice(info.gpus[0], &device_info, NULL, &device);
        assert(res == VK_SUCCESS);
    }

    </#if>
    </#list>
    </#list>