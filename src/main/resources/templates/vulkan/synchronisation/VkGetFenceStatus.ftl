
    //ID: ${config.id}
    VkResult ${config.result} = vkGetFenceStatus(${config.device}.device, ${config.fence});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_NOT_READY)
            || (${config.result} == VK_ERROR_DEVICE_LOST)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    assert(${config.result} == VK_SUCCESS);
