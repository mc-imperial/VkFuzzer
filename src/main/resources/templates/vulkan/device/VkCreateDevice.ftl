
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    <#list config.devicePropertiesConfigs as devicePropertiesConfig>
    <#list devicePropertiesConfig.devices as deviceProperties>
    <#if deviceProperties.isBad()>
    <#else>
    std::vector<VkDevice> ${config.logicalDevices}${devicePropertiesConfig?index}${deviceProperties?index};
    std::vector<int> ${config.queueIndex}${devicePropertiesConfig?index}${deviceProperties?index};

    for (int i = 0; i < ${deviceProperties.devices}.size(); ++i)
    {
        for (int j = 0; j < ${deviceProperties.deviceQueueFamilyProperties}[i].size(); ++j)
        {
            VkDeviceQueueCreateInfo queueInfo = {};
            std::vector<float> queuePriorities;

            bool found = false;
            for (unsigned int k = 0; k < ${deviceProperties.deviceQueueFamilyProperties}[i].size(); ++k)
            {
                if (${deviceProperties.deviceQueueFamilyProperties}[i][j].queueFlags & VK_QUEUE_GRAPHICS_BIT)
                {
                    queueInfo.queueFamilyIndex = k;
                    queuePriorities.resize(${deviceProperties.deviceQueueFamilyProperties}[i][j].queueCount);
                    ${config.queueIndex}${devicePropertiesConfig?index}${deviceProperties?index}.push_back(k);
                    found = true;
                    break;
                }
            }

            // If no queue was found that supports graphics then indicate that with a negative index
            if (!found)
            {
                ${config.queueIndex}${devicePropertiesConfig?index}${deviceProperties?index}.push_back(-1);
            }

            // If no graphics queue found then stop
            // assert(found);

            for (int k = 0; k < ${deviceProperties.deviceQueueFamilyProperties}[i].size(); ++k)
            {
                queuePriorities.push_back(static_cast<float>(rand()) / static_cast<float>(RAND_MAX));
            }

            queueInfo.sType = VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO;
            queueInfo.pNext = NULL;
            queueInfo.queueCount = 1;
            queueInfo.pQueuePriorities = queuePriorities.data();

            VkDeviceCreateInfo deviceInfo = {};
            deviceInfo.sType = VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO;
            deviceInfo.flags = 0;
            deviceInfo.pNext = NULL;
            deviceInfo.queueCreateInfoCount = 1;
            deviceInfo.pQueueCreateInfos = &queueInfo;
            deviceInfo.enabledExtensionCount = 0;
            deviceInfo.ppEnabledExtensionNames = NULL;
            deviceInfo.enabledLayerCount = 0;
            deviceInfo.ppEnabledLayerNames = NULL;
            deviceInfo.pEnabledFeatures = NULL;

            VkDevice device;
            VkResult res = vkCreateDevice(${deviceProperties.devices}[i], &deviceInfo, NULL, &device);

            // Check result
            assert((res == VK_SUCCESS)
                    || (res == VK_ERROR_OUT_OF_HOST_MEMORY)
                    || (res == VK_ERROR_OUT_OF_DEVICE_MEMORY)
                    || (res == VK_ERROR_INITIALIZATION_FAILED)
                    || (res == VK_ERROR_LAYER_NOT_PRESENT)
                    || (res == VK_ERROR_EXTENSION_NOT_PRESENT)
                    || (res == VK_ERROR_FEATURE_NOT_PRESENT)
                    || (res == VK_ERROR_TOO_MANY_OBJECTS)
                    || (res == VK_ERROR_DEVICE_LOST));

            assert(res == VK_SUCCESS);

            ${config.logicalDevices}${devicePropertiesConfig?index}${deviceProperties?index}.push_back(device);
        }
    }

    </#if>
    </#list>
    </#list>
    </#if>
