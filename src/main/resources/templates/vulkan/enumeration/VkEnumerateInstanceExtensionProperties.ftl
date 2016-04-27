
    // ID: ${config.id}
    // Enumerate Instance extensions
    std::vector<VkExtensionProperties> ${config.instanceExtensionProperties};
    uint32_t ${config.instanceExtensionCount} = 0;
    VkResult ${config.result};

    do
    {
        ${config.result} = vkEnumerateInstanceExtensionProperties(
                NULL, &${config.instanceExtensionCount}, NULL);

        // Check that the return code is one of the acceptable ones
        assert((${config.result} == VK_SUCCESS)
                || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
                || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

        checkResultOutOfMemory(${config.result});

        if(${config.instanceExtensionCount} == 0)
        {
            std::cout << "No instance layers found." << std::endl;
            break;
        }

        ${config.instanceExtensionProperties}.resize(${config.instanceExtensionCount});

        ${config.result} = vkEnumerateInstanceExtensionProperties(NULL,
                &${config.instanceExtensionCount}, ${config.instanceExtensionProperties}.data());

        // Check that the return code is one of the acceptable ones
        assert((${config.result} == VK_SUCCESS)
                || (${config.result} == VK_INCOMPLETE)
                || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
                || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));
    }
    while(${config.result} == VK_INCOMPLETE);

    checkResultOutOfMemory(${config.result});
