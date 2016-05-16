
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    <#list config.devicePropertiesConfigs as devicePropertiesConfig>
    <#list devicePropertiesConfig.devices as deviceProperties>
    <#if deviceProperties.isBad()>
    <#else>
    std::vector<FuzzerLogicalDevice> ${config.logicalDevices}${devicePropertiesConfig?index}${deviceProperties?index};

    for (int i = 0; i < ${deviceProperties.devices}.size(); ++i)
    {
        for (int j = 0; j < ${deviceProperties.deviceQueueFamilyProperties}[i].size(); ++j)
        {
            FuzzerLogicalDevice fuzzerLogicalDevice = {};
            fuzzerLogicalDevice.gpuQueueFamilyCount = ${deviceProperties.deviceQueueFamilyProperties}[i].size();
            fuzzerLogicalDevice.gpu = ${deviceProperties.devices}[i];
            fuzzerLogicalDevice.queueFamilyIndex = j;
            fuzzerLogicalDevice.maxQueueCount = ${deviceProperties.deviceQueueFamilyProperties}[i][j].queueCount;
            fuzzerLogicalDevice.deviceMemoryProperties = ${deviceProperties.deviceMemoryProperties}[i];

            if (${deviceProperties.deviceQueueFamilyProperties}[i][j].queueFlags & VK_QUEUE_GRAPHICS_BIT)
            {
                fuzzerLogicalDevice.supportsGraphics = true;
                fuzzerLogicalDevice.supportsTransfer = true;
            }

            if (${deviceProperties.deviceQueueFamilyProperties}[i][j].queueFlags & VK_QUEUE_COMPUTE_BIT)
            {
                fuzzerLogicalDevice.supportsCompute = true;
                fuzzerLogicalDevice.supportsTransfer = true;
            }

            if (${deviceProperties.deviceQueueFamilyProperties}[i][j].queueFlags & VK_QUEUE_TRANSFER_BIT)
            {
                fuzzerLogicalDevice.supportsTransfer = true;
            }

            if (${deviceProperties.deviceQueueFamilyProperties}[i][j].queueFlags & VK_QUEUE_SPARSE_BINDING_BIT)
            {
                fuzzerLogicalDevice.supportsSparseBinding = true;
            }

            // Assign random priorities
            std::vector<float> queuePriorities;
            float sum = 0.0f;
            for (unsigned int k = 0; k < ${deviceProperties.deviceQueueFamilyProperties}[i][j].queueCount; ++k)
            {
                float priority = static_cast<float>(rand()) / static_cast<float>(RAND_MAX);
                queuePriorities.push_back(priority);
                sum += priority;
            }

            // Normalise priorities
            for (unsigned int k = 0; k < queuePriorities.size(); ++k)
            {
                queuePriorities[k] /= sum;
            }

            VkDeviceQueueCreateInfo queueInfo = {};
            queueInfo.queueFamilyIndex = j;
            queueInfo.sType = VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO;
            queueInfo.pNext = NULL;
            queueInfo.queueCount = fuzzerLogicalDevice.maxQueueCount;
            queueInfo.pQueuePriorities = queuePriorities.data();

            VkDeviceCreateInfo deviceInfo = {};
            deviceInfo.sType = VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO;
            deviceInfo.flags = 0;
            deviceInfo.pNext = NULL;
            deviceInfo.queueCreateInfoCount = 1;
            deviceInfo.pQueueCreateInfos = &queueInfo;
            deviceInfo.enabledExtensionCount = deviceExtensions.size();
            deviceInfo.ppEnabledExtensionNames = deviceExtensions.data();
            deviceInfo.enabledLayerCount = 0;
            deviceInfo.ppEnabledLayerNames = NULL;
            deviceInfo.pEnabledFeatures = NULL;

            if (totalLogicalDevices > MAX_LOGICAL_DEVICES)
            {
                break;
            }

            VkResult res = vkCreateDevice(${deviceProperties.devices}[i], &deviceInfo,
                    NULL, &(fuzzerLogicalDevice.device));

            totalLogicalDevices++;

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

            checkResultOutOfMemory(res);

            assert(res == VK_SUCCESS);

            ${config.logicalDevices}${devicePropertiesConfig?index}${deviceProperties?index}.push_back(fuzzerLogicalDevice);
        }
    }

    </#if>
    </#list>
    </#list>
    </#if>
