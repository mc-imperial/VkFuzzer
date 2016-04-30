
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkFrameBufferCreateInfo ${config.framebufferCreateInfo} = {};
    ${config.framebufferCreateInfo}.sType = VK_STRUCTURE_TYPE_FRAMEBUFFER_CREATE_INFO;
    ${config.framebufferCreateInfo}.pNext = NULL;
    ${config.framebufferCreateInfo}.flags = 0;
    ${config.framebufferCreateInfo}.renderPass = ${config.renderpass};
    ${config.framebufferCreateInfo}.attachmentCount = ${config.attachmentsCount};
    ${config.framebufferCreateInfo}.pAttachments = &${config.attachments};
    ${config.framebufferCreateInfo}.width = ${config.width};
    ${config.framebufferCreateInfo}.height = ${config.height};
    ${config.framebufferCreateInfo}.layers = ${config.layers};

    VkFrameBuffer ${config.framebuffer};
    VkResult ${config.result} = vkCreateFramebuffer(${config.device}.device,
            ${config.framebufferCreateInfo}, NULL, &${config.framebuffer});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);
    </#if>
