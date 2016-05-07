
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    uint32_t ${config.nextImage};
    VkResult ${config.result} = vkAcquireNextImageKHR(${config.device}.device, ${config.swapchain},
            0, VK_NULL_HANDLE, VK_NULL_HANDLE, &${config.nextImage});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_NOT_READY)
            || (${config.result} == VK_TIMEOUT)
            || (${config.result} == VK_ERROR_DEVICE_LOST)
            || (${config.result} == VK_ERROR_OUT_OF_DATE_KHR)
            || (${config.result} == VK_ERROR_SURFACE_LOST_KHR)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert((${config.result} == VK_SUCCESS)
    || (${config.result} == VK_SUBOPTIMAL_KHR));

    </#if>