
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkCommandBuffer ${config.commandBuffer} = ${config.commandBuffers}[rand() % ${config.commandBuffers}.size()];
    VkCommandBufferBeginInfo ${config.commandBufferBeginInfo} = {};
    ${config.commandBufferBeginInfo}.sType = VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO;
    ${config.commandBufferBeginInfo}.pNext = NULL;
    ${config.commandBufferBeginInfo}.flags = 0;

    VkResult ${config.result} = vkBeginCommandBuffer(${config.commandBuffer}, &${config.commandBufferBeginInfo});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);

    </#if>