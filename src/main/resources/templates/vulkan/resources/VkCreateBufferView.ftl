
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkBufferViewCreateInfo ${config.bufferViewCreateInfo} = {};
    ${config.bufferViewCreateInfo}.sType = VK_STRUCTURE_TYPE_BUFFER_VIEW_CREATE_INFO;
    ${config.bufferViewCreateInfo}.pNext = NULL;
    ${config.bufferViewCreateInfo}.flags = 0;
    ${config.bufferViewCreateInfo}.buffer = ${config.buffer};
    ${config.bufferViewCreateInfo}.format = ${config.format};
    ${config.bufferViewCreateInfo}.offset = ${config.offset};
    ${config.bufferViewCreateInfo}.range = ${config.range};

    VkBufferView ${config.bufferView};
    VkResult ${config.result} = vkCreateBufferView(${config.device}.device, &${config.bufferViewCreateInfo},
            NULL, &${config.bufferView});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    if ((${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY))
    {
        std::cerr << "Run out of memory. Exiting gracefully."
        return EXIT_RUN_OUT_OF_MEMORY;
    }

    assert(${config.result} == VK_SUCCESS);

    </#if>