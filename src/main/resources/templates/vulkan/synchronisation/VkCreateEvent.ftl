
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkEventCreateInfo ${config.eventCreateInfo} = {};
    ${config.eventCreateInfo}.sType = VK_STRUCTURE_TYPE_EVENT_CREATE_INFO;
    ${config.eventCreateInfo}.pNext = NULL;
    ${config.eventCreateInfo}.flags = 0;

    VkEvent ${config.event};
    VkResult ${config.result} = vkCreateEvent(${config.device}.device, &${config.eventCreateInfo},
            NULL, &${config.event});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    if ((${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY))
    {
        std::cerr << "Run out of memory. Exiting gracefully." << std::endl;
        return EXIT_RUN_OUT_OF_MEMORY;
    }

    assert(${config.result} == VK_SUCCESS);
    </#if>

