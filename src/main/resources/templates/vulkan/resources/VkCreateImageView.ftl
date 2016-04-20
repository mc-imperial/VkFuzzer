
    //ID: ${config.id}
    VkImageViewCreateInfo ${config.vkImageViewCreateInfo} = {};
    ${config.vkImageViewCreateInfo}.sType = VK_STRUCTURE_TYPE_IMAGE_VIEW_CREATE_INFO;
    ${config.vkImageViewCreateInfo}.pNext = NULL;
    ${config.vkImageViewCreateInfo}.flags = 0;
    ${config.vkImageViewCreateInfo}.image = ${config.image};
    ${config.vkImageViewCreateInfo}.viewType = ${config.viewType};
    ${config.vkImageViewCreateInfo}.format = ${config.format};
    ${config.vkImageViewCreateInfo}.components = ${config.components};
    ${config.vkImageViewCreateInfo}.subresourceRange = ${config.subresourceRange};

    VkImageView ${config.imageView};
    VkResult ${config.result} = vkCreateImageView(${config.device}, &${config.vkImageViewCreateInfo},
            NULL, &${config.imageView});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    assert(${config.result} == VK_SUCCESS);
