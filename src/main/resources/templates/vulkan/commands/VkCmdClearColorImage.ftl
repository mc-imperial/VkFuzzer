
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkImageSubresourceRange ${config.subresourceRange} = {};
    ${config.subresourceRange}.aspectMask = VK_IMAGE_ASPECT_COLOR_BIT;
    ${config.subresourceRange}.baseMipLevel = 0;
    ${config.subresourceRange}.levelCount = VK_REMAINING_MIP_LEVELS;
    ${config.subresourceRange}.baseArrayLayer = 0;
    ${config.subresourceRange}.layerCount = VK_REMAINING_ARRAY_LAYERS;

    VkClearColorValue ${config.clearColor}[1];
    ${config.clearColor}[0].float32[0] = dis(gen);
    ${config.clearColor}[0].float32[1] = dis(gen);
    ${config.clearColor}[0].float32[2] = dis(gen);
    ${config.clearColor}[0].float32[3] = dis(gen);

    vkCmdClearColorImage(${config.commandBuffer},
            ${config.swapchainBuffers}[${config.nextImage}].image,
            VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL, ${config.clearColor}, 1, &${config.subresourceRange});

    </#if>
