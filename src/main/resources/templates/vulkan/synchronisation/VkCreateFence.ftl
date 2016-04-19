
    //ID: ${config.id}
    VkFenceCreateInfo ${config.fenceCreateInfo} = {};
    ${config.fenceCreateInfo}.sType = ;
    ${config.fenceCreateInfo}.pNext = ;
    ${config.fenceCreateInfo}.flags = ;

    VkFence ${config.fence};
    VkResult ${config.result} = vkCreateFence(${config.device}, &${config.fenceCreateInfo},
            NULL, &${config.fence});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    assert(${config.result} == VK_SUCCESS);
