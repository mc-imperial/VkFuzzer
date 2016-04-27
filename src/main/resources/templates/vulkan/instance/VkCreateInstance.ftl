
    // ID: ${config.id}
    // Create instance
    VkInstance ${config.instanceName};
    VkResult ${config.result} = vkCreateInstance(&${config.instanceCreateInfo}, ${config.alloc}, &${config.instanceName});
    assert(${config.result} ${config.isBad()?then('!=','==')} VK_SUCCESS);

    if ((${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY))
    {
        std::cerr << "Run out of memory. Exiting gracefully."
        return EXIT_RUN_OUT_OF_MEMORY;
    }
