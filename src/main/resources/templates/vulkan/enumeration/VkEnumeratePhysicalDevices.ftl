
    // ID: ${config.id}
    // Get number of available GPUs
    std::vector<VkPhysicalDevice> ${config.gpus};
    uint32_t ${config.gpuCount} = 0;
    VkResult ${config.result};

    do
    {
        ${config.result} = vkEnumeratePhysicalDevices(${config.instance},
                &${config.gpuCount}, NULL);

        assert((${config.result} == VK_SUCCESS)
                || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
                || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY)
                || (${config.result} == VK_ERROR_INITIALIZATION_FAILED));

        ${config.gpus}.resize(${config.gpuCount});

        // Get information of available GPUs
        ${config.result} = vkEnumeratePhysicalDevices(${config.instance},
                &${config.gpuCount}, ${config.gpus}.data());

        assert((${config.result} == VK_SUCCESS)
                || (${config.result} == VK_INCOMPLETE)
                || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
                || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY)
                || (${config.result} == VK_ERROR_INITIALIZATION_FAILED));
    }
    while(${config.result} == VK_INCOMPLETE);

    assert(${config.result} == ${config.returnCode});
