
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkSemaphoreCreateInfo ${config.semaphoreCreateInfo} = {};
    ${config.semaphoreCreateInfo}.sType = VK_STRUCTURE_TYPE_SEMAPHORE_CREATE_INFO;
    ${config.semaphoreCreateInfo}.pNext = NULL;
    ${config.semaphoreCreateInfo}.flags = 0;

    VkSemaphore ${config.semaphore};
    VkResult ${config.result} = vkCreateSemaphore(${config.device}.device, &${config.semaphoreCreateInfo},
            NULL, &${config.semaphore});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);
    </#if>
