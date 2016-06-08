
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkResult ${config.result} = vkEndCommandBuffer(${config.commandBuffer});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);

    stats.print("vkCommandBuffer recording");

    </#if>