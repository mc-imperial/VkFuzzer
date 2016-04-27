
    <#if config.isBad()>
    <#else>
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

        if ((${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
                || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY))
        {
            std::cerr << "Run out of memory. Exiting gracefully." << std::endl;
            return EXIT_RUN_OUT_OF_MEMORY;
        }

        ${config.gpus}.resize(${config.gpuCount});

        // Get information of available GPUs
        ${config.result} = vkEnumeratePhysicalDevices(${config.instance},
                &${config.gpuCount}, ${config.gpus}.data());

        assert((${config.result} == VK_SUCCESS)
                || (${config.result} == VK_INCOMPLETE)
                || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
                || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY)
                || (${config.result} == VK_ERROR_INITIALIZATION_FAILED));

        if ((${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
                || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY))
        {
            std::cerr << "Run out of memory. Exiting gracefully." << std::endl;
            return EXIT_RUN_OUT_OF_MEMORY;
        }
    }
    while(${config.result} == VK_INCOMPLETE);

    assert(${config.result} == ${config.returnCode});
    </#if>
