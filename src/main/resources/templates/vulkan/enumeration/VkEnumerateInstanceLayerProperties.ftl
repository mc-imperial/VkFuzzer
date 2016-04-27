
    // ID: ${config.id}
    // Enumerate Instance Layer Properties
    std::vector<VkLayerProperties> ${config.instanceLayerProperties};
    uint32_t ${config.instanceLayerCount} = 0;
    VkResult ${config.result};

    do
    {
        ${config.result} = vkEnumerateInstanceLayerProperties(&${config.instanceLayerCount}, NULL);

        // Check that the return code is one of the acceptable ones
        assert((${config.result} == VK_SUCCESS)
                || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
                || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

        checkResultOutOfMemory(${config.result});

        if(${config.instanceLayerCount} == 0)
        {
            std::cout << "No instance layers found." << std::endl;
            break;
        }

        ${config.instanceLayerProperties}.resize(${config.instanceLayerCount});

        ${config.result} = vkEnumerateInstanceLayerProperties(&${config.instanceLayerCount},
                ${config.instanceLayerProperties}.data());

        // Check that the return code is one of the acceptable ones
        assert((${config.result} == VK_SUCCESS)
                || (${config.result} == VK_INCOMPLETE)
                || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
                || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));
    }
    while(${config.result} == VK_INCOMPLETE);

    checkResultOutOfMemory(${config.result});
