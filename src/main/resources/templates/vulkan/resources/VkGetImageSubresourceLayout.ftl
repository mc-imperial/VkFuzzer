
    //ID: ${config.id}
    VkImageSubresource ${config.imageSubresource} = {};
    ${config.imageSubresource}.aspectMask = ${config.aspeckMask};
    ${config.imageSubresource}.mipLevel = ${config.mipLevel};
    ${config.imageSubresource}.arrayLayer = ${config.arrayLayer};

    VkSubresourceLayout ${config.subresourceLayout};
    vkCreateImageView(${config.device}, &${config.image},
            ${config.imageSubresource}, &${config.subresourceLayout});
