
    //ID: ${config.id}
    VkCommandBufferAllocateInfo ${config.cmdBufferAllocInfo} = {};
    ${config.cmdBufferAllocInfo}.sType = VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO;
    ${config.cmdBufferAllocInfo}.pNext = ${config.next};
    ${config.cmdBufferAllocInfo}.commandPool = ${config.commandPool};
    ${config.cmdBufferAllocInfo}.level = ${config.bufferType};
    ${config.cmdBufferAllocInfo}.bufferCount = ${config.count};

    std::vector<VkCommandBuffer> ${config.buffers};
    ${config.buffers}.resize(${config.count});

    VkResult ${config.result} = vkAllocateCommandBuffers(${config.device},
            &${config.cmdBufferAllocInfo}, ${config.buffers}.data());

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    assert(${config.result} == VK_SUCCESS);
