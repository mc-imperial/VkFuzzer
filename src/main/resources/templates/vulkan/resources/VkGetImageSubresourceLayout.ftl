
    //ID: ${config.id}
    <#if config.isBad()>
    <#else>
    VkImageSubresource ${config.imageSubresource} = {};
    ${config.imageSubresource}.aspectMask = ${config.aspectMask};
    ${config.imageSubresource}.mipLevel = ${config.mipLevel};
    ${config.imageSubresource}.arrayLayer = ${config.arrayLayer};

    VkSubresourceLayout ${config.subresourceLayout};
    vkGetImageSubresourceLayout(${config.device}.device, ${config.image},
            &${config.imageSubresource}, &${config.subresourceLayout});
    </#if>
