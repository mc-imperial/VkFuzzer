
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkResult ${config.result} = vkGetEventStatus(${config.device}.device, ${config.event});

    assert((${config.result} == VK_EVENT_SET)
            || (${config.result} == VK_EVENT_RESET)
            || (${config.result} == VK_ERROR_DEVICE_LOST)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    if ((${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY))
    {
        std::cerr << "Run out of memory. Exiting gracefully." << std::endl;
        return EXIT_RUN_OUT_OF_MEMORY;
    }

    assert(${config.result} == ${config.expectedReturnCode});
    </#if>
