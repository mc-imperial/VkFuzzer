
    //ID: ${config.id}
    VkFenceCreateInfo ${config.fenceCreateInfo} = {};
    ${config.fenceCreateInfo}.sType = VK_STRUCTURE_TYPE_FENCE_CREATE_INFO;
    ${config.fenceCreateInfo}.pNext = NULL;
    ${config.fenceCreateInfo}.flags = ${config.flags};

    VkFence ${config.fence};
    VkResult ${config.result} = vkCreateFence(${config.device}.device, &${config.fenceCreateInfo},
            NULL, &${config.fence});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    assert(${config.result} == VK_SUCCESS);
