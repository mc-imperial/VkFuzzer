
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkCommandBufferAllocateInfo ${config.cmdBufferAllocInfo} = {};
    ${config.cmdBufferAllocInfo}.sType = VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO;
    ${config.cmdBufferAllocInfo}.pNext = ${config.next};
    ${config.cmdBufferAllocInfo}.commandPool = ${config.commandPool};
    ${config.cmdBufferAllocInfo}.level = ${config.bufferType};
    ${config.cmdBufferAllocInfo}.commandBufferCount = ${config.count};

    std::vector<VkCommandBuffer> ${config.buffers};
    ${config.buffers}.resize(${config.count});

    VkResult ${config.result} = vkAllocateCommandBuffers(${config.device}.device,
            &${config.cmdBufferAllocInfo}, ${config.buffers}.data());

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
