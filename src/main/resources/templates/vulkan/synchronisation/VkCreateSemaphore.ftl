
    //ID: ${config.id}
    VkSemaphoreCreateInfo ${config.semaphoreCreateInfo} = {};
    ${config.semaphoreCreateInfo}.sType = ;
    ${config.semaphoreCreateInfo}.pNext = ;
    ${config.semaphoreCreateInfo}.flags = ;

    VkSemaphore ${config.fence};
    VkResult ${config.result} = vkCreateSemaphore(${config.device}, &${config.semaphoreCreateInfo},
            NULL, &${config.fence});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    assert(${config.result} == VK_SUCCESS);
