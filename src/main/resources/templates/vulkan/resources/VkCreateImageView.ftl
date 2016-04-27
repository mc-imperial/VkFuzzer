
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkImageSubresourceRange ${config.subresourceRange} = {};
    ${config.subresourceRange}.aspectMask = ${config.aspectMask};
    ${config.subresourceRange}.baseMipLevel = ${config.baseMipLevel};
    ${config.subresourceRange}.levelCount = ${config.levelCount};
    ${config.subresourceRange}.baseArrayLayer = ${config.baseArrayLayer};
    ${config.subresourceRange}.layerCount = ${config.layerCount};

    VkComponentMapping ${config.components} = {};

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
    VkResult ${config.result} = vkCreateImageView(${config.device}.device, &${config.vkImageViewCreateInfo},
            NULL, &${config.imageView});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    if ((${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY))
    {
        std::cerr << "Run out of memory. Exiting gracefully."
        return EXIT_RUN_OUT_OF_MEMORY;
    }

    assert(${config.result} == VK_SUCCESS);
    </#if>