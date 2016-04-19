
    //ID: ${config.id}
    VkResult ${config.result} = vkResetFences(${config.device}.device, ${config.fenceCount}, &${config.fence});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    assert(${config.result} == VK_SUCCESS);