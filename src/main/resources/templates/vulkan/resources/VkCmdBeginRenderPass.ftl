
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}

    VkClearValue ${config.clearValues}[2];
    ${config.clearValues}[0].color.float32[0] = dis(gen);
    ${config.clearValues}[0].color.float32[1] = dis(gen);
    ${config.clearValues}[0].color.float32[2] = dis(gen);
    ${config.clearValues}[0].color.float32[3] = dis(gen);
    ${config.clearValues}[1].depthStencil.depth = dis(gen);
    ${config.clearValues}[1].depthStencil.stencil = dis(gen);

    VkRenderPassBeginInfo ${config.renderpassBeginInfo} = {};
    ${config.renderpassBeginInfo}.sType = VK_STRUCTURE_TYPE_RENDER_PASS_BEGIN_INFO;
    ${config.renderpassBeginInfo}.pNext = NULL;
    ${config.renderpassBeginInfo}.renderPass = ${config.renderpass};
    ${config.renderpassBeginInfo}.framebuffer = ${config.framebuffers}[${config.nextImage}];
    ${config.renderpassBeginInfo}.renderArea.offset.x = 0;
    ${config.renderpassBeginInfo}.renderArea.offset.y = 0;
    ${config.renderpassBeginInfo}.renderArea.extent.width = config->width;
    ${config.renderpassBeginInfo}.renderArea.extent.height = config->height;
    ${config.renderpassBeginInfo}.clearValueCount = 2;
    ${config.renderpassBeginInfo}.pClearValues = ${config.clearValues};

    vkCmdBeginRenderPass(${config.commandBuffer}, &${config.renderpassBeginInfo}, VK_SUBPASS_CONTENTS_INLINE);
    </#if>