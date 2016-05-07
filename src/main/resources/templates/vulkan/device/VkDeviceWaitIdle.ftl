
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkResult ${config.result} = vkDeviceWaitIdle(${config.device}.device);

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_DEVICE_LOST)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);

    </#if>