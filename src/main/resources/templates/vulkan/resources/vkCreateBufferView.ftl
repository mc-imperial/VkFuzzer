
    //ID: ${config.id}
    <#if config.isBad()>
    <#else>
    VkBufferViewCreateInfo ${config.bufferViewCreateInfo} = {};
    ${config.bufferViewCreateInfo}.sType = VK_STRUCTURE_TYPE_BUFFER_VIEW_CREATE_INFO;
    ${config.bufferViewCreateInfo}.pNext = NULL;
    ${config.bufferViewCreateInfo}.flags = 0;
    ${config.bufferViewCreateInfo}.buffer = ${config.buffer};
    ${config.bufferViewCreateInfo}.format = ${config.format};
    ${config.bufferViewCreateInfo}.offset = ${config.offset};
    ${config.bufferViewCreateInfo}.range = ${config.range};

    VkBuffer ${config.bufferView};
    VkResult ${config.result} = vkCreateBufferView(${config.device}.device, &${config.bufferViewCreateInfo},
            NULL, &${config.bufferView});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    assert(${config.result} == VK_SUCCESS);

    </#if>