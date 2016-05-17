
    //ID: ${config.id}
    FuzzerLogicalDevice ${config.device};
    bool found = false;

    if (${config.logicalDevices}.empty())
    {
        exit(EXIT_SUCCESS);
    }

    for (FuzzerLogicalDevice fuzzerLogicalDevice: ${config.logicalDevices})
    {
#ifdef _FUZZER_COMPUTE_ONLY_
        if (fuzzerLogicalDevice.supportsCompute)
        {
            ${config.device} = fuzzerLogicalDevice;
            found = true;
            break;
        }
#else
        VkBool32 supportsPresent;
        vkGetPhysicalDeviceSurfaceSupportKHR(fuzzerLogicalDevice.gpu, fuzzerLogicalDevice.queueFamilyIndex,
                ${config.surface}, &supportsPresent);
        if (fuzzerLogicalDevice.supportsGraphics && supportsPresent)
        {
            ${config.device} = fuzzerLogicalDevice;
            found = true;
            break;
        }
#endif
    }

    if (!found)
    {
        std::cerr << "No suitable device found. Exiting." << std::endl;
        exit(NO_SUITABLE_DEVICE_AVAILABLE);
    }
