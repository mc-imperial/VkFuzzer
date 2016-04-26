
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkBufferCreateInfo ${config.bufferCreateInfo} = {};
    ${config.bufferCreateInfo}.sType = VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO;
    ${config.bufferCreateInfo}.pNext = NULL;
    ${config.bufferCreateInfo}.flags = ${config.flags?join(" | ")};
    ${config.bufferCreateInfo}.size = ${config.size};
    ${config.bufferCreateInfo}.usage = ${config.usage?join(" | ")};
    ${config.bufferCreateInfo}.sharingMode = ${config.sharingMode};
    ${config.bufferCreateInfo}.queueFamilyIndexCount = ${config.queueFamilyIndexCount};
    ${config.bufferCreateInfo}.pQueueFamilyIndices = ${config.pQueueFamilyIndices};

    VkBuffer ${config.buffer};
    VkResult ${config.result} = vkCreateBuffer(${config.device}.device, &${config.bufferCreateInfo},
            NULL, &${config.buffer});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    assert(${config.result} == VK_SUCCESS);
    </#if>
