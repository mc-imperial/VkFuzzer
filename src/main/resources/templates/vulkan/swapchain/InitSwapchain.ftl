
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    // Find a suitable device
    FuzzerLogicalDevice ${config.device};
    bool found = false;
    for (FuzzerLogicalDevice fuzzerLogicalDevice: ${config.logicalDevices})
    {
        if (fuzzerLogicalDevice.supportsGraphics)
        {
            ${config.device} = fuzzerLogicalDevice;
            found = true;
            break;
        }
    }

    if (!found)
    {
        std::cerr << "No device found that supports presenting. Exitting." << std::endl;
        return NO_PRESENT_DEVICE_AVAILABLE;
    }

    // Get the list of VkFormats that are supported:
    uint32_t ${config.formatCount};
    VkResult ${config.result} = vkGetPhysicalDeviceSurfaceFormatsKHR(${config.device}.gpu, ${config.surface},
            &${config.formatCount}, NULL);

    assert(${config.result} == VK_SUCCESS);

    std::vector<VkSurfaceFormatKHR> ${config.surfaceFormats};
    ${config.surfaceFormats}.resize(${config.formatCount});

    ${config.result} = vkGetPhysicalDeviceSurfaceFormatsKHR(${config.device}.gpu, ${config.surface},
            &${config.formatCount}, ${config.surfaceFormats}.data());

    assert(${config.result} == VK_SUCCESS);

    // If the format list includes just one entry of VK_FORMAT_UNDEFINED,
    // the surface has no preferred format.  Otherwise, at least one
    // supported format will be returned.

    VkFormat ${config.format};
    if (${config.formatCount} == 1 && ${config.surfaceFormats}[0].format == VK_FORMAT_UNDEFINED)
    {
        ${config.format} = VK_FORMAT_B8G8R8A8_UNORM;
    }
    else
    {
        assert(${config.formatCount} >= 1);
        ${config.format} = ${config.surfaceFormats}[0].format;
    }

    VkSurfaceCapabilitiesKHR ${config.surfCapabilities};

    ${config.result} = vkGetPhysicalDeviceSurfaceCapabilitiesKHR(${config.device}.gpu, ${config.surface},
            &${config.surfCapabilities});

    assert(${config.result} == VK_SUCCESS);

    uint32_t ${config.presentModeCount};
    ${config.result} = vkGetPhysicalDeviceSurfacePresentModesKHR(${config.device}.gpu, ${config.surface},
            &${config.presentModeCount}, NULL);

    assert(${config.result} == VK_SUCCESS);

    std::vector<VkPresentModeKHR> ${config.presentModes};
    ${config.presentModes}.resize(${config.presentModeCount});

    ${config.result} = vkGetPhysicalDeviceSurfacePresentModesKHR(
            ${config.device}.gpu, ${config.surface}, &${config.presentModeCount}, ${config.presentModes}.data());

    assert(${config.result} == VK_SUCCESS);

    VkExtent2D ${config.swapchainExtent};
    // width and height are either both -1, or both not -1.
    if (${config.surfCapabilities}.currentExtent.width == (uint32_t)-1)
    {
        // If the surface size is undefined, the size is set to
        // the size of the images requested.
        ${config.swapchainExtent}.width = WIDTH;
        ${config.swapchainExtent}.height = HEIGHT;
    }
    else
    {
        // If the surface size is defined, the swap chain size must match
        ${config.swapchainExtent} = ${config.surfCapabilities}.currentExtent;
    }

    // If mailbox mode is available, use it, as is the lowest-latency non-
    // tearing mode.  If not, try IMMEDIATE which will usually be available,
    // and is fastest (though it tears).  If not, fall back to FIFO which is
    // always available.
    VkPresentModeKHR ${config.swapchainPresentMode} = VK_PRESENT_MODE_FIFO_KHR;
    for (size_t i = 0; i < ${config.presentModeCount}; i++)
    {
        if (${config.presentModes}[i] == VK_PRESENT_MODE_MAILBOX_KHR)
        {
            ${config.swapchainPresentMode} = VK_PRESENT_MODE_MAILBOX_KHR;
            break;
        }
        if ((${config.swapchainPresentMode} != VK_PRESENT_MODE_MAILBOX_KHR) &&
                (${config.presentModes}[i] == VK_PRESENT_MODE_IMMEDIATE_KHR))
        {
            ${config.swapchainPresentMode} = VK_PRESENT_MODE_IMMEDIATE_KHR;
        }
    }

    // Determine the number of VkImage's to use in the swap chain (we desire to
    // own only 1 image at a time, besides the images being displayed and
    // queued for display):
    uint32_t ${config.desiredNumberOfSwapChainImages} = ${config.surfCapabilities}.minImageCount + 1;
    if ((${config.surfCapabilities}.maxImageCount > 0) &&
            (${config.desiredNumberOfSwapChainImages} > ${config.surfCapabilities}.maxImageCount))
    {
        // Application must settle for fewer images than desired:
        ${config.desiredNumberOfSwapChainImages} = ${config.surfCapabilities}.maxImageCount;
    }

    VkSurfaceTransformFlagBitsKHR ${config.preTransform};
    if (${config.surfCapabilities}.supportedTransforms &
            VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR)
    {
        ${config.preTransform} = VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR;
    }
    else
    {
        ${config.preTransform} = ${config.surfCapabilities}.currentTransform;
    }

    VkSwapchainCreateInfoKHR ${config.swapchainCreateInfo} = {};
    ${config.swapchainCreateInfo}.sType = VK_STRUCTURE_TYPE_SWAPCHAIN_CREATE_INFO_KHR;
    ${config.swapchainCreateInfo}.pNext = NULL;
    ${config.swapchainCreateInfo}.surface = ${config.surface};
    ${config.swapchainCreateInfo}.minImageCount = ${config.desiredNumberOfSwapChainImages};
    ${config.swapchainCreateInfo}.imageFormat = ${config.format};
    ${config.swapchainCreateInfo}.imageExtent.width = ${config.swapchainExtent}.width;
    ${config.swapchainCreateInfo}.imageExtent.height = ${config.swapchainExtent}.height;
    ${config.swapchainCreateInfo}.preTransform = ${config.preTransform};
    ${config.swapchainCreateInfo}.compositeAlpha = VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR;
    ${config.swapchainCreateInfo}.imageArrayLayers = 1;
    ${config.swapchainCreateInfo}.presentMode = ${config.swapchainPresentMode};
    ${config.swapchainCreateInfo}.oldSwapchain = VK_NULL_HANDLE;
    ${config.swapchainCreateInfo}.clipped = true;
    ${config.swapchainCreateInfo}.imageColorSpace = VK_COLORSPACE_SRGB_NONLINEAR_KHR;
    ${config.swapchainCreateInfo}.imageUsage = VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT;
    ${config.swapchainCreateInfo}.imageSharingMode = VK_SHARING_MODE_EXCLUSIVE;
    ${config.swapchainCreateInfo}.queueFamilyIndexCount = 0;
    ${config.swapchainCreateInfo}.pQueueFamilyIndices = NULL;

    VkSwapchainKHR ${config.swapchain};
    ${config.result} = vkCreateSwapchainKHR(${config.device}.device,
            &${config.swapchainCreateInfo}, NULL, &${config.swapchain});

    assert(${config.result} == VK_SUCCESS);

    uint32_t ${config.swapchainImageCount};
    ${config.result} = vkGetSwapchainImagesKHR(${config.device}.device, ${config.swapchain},
            &${config.swapchainImageCount}, NULL);

    assert(${config.result} == VK_SUCCESS);
    assert(${config.swapchainImageCount} > 0);

    std::vector<VkImage> ${config.swapchainImages};
    ${config.swapchainImages}.resize(${config.swapchainImageCount});

    ${config.result} = vkGetSwapchainImagesKHR(${config.device}.device, ${config.swapchain},
            &${config.swapchainImageCount}, ${config.swapchainImages}.data());
    assert(${config.result} == VK_SUCCESS);

    std::vector<SwapchainBuffer> ${config.swapchainBuffers};
    ${config.swapchainBuffers}.resize(${config.swapchainImageCount});

    </#if>