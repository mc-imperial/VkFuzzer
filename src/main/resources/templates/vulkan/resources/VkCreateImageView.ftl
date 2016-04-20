
    //ID: ${config.id}
    VkImageViewCreateInfo ${config.vkImageViewCreateInfo} = {};

    VkImageView ${config.imageView};
    VkResult ${config.result} = vkCreateImage(${config.device}, &${config.vkImageViewCreateInfo},
            NULL, &${config.imageView});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    assert(${config.result} == VK_SUCCESS);
