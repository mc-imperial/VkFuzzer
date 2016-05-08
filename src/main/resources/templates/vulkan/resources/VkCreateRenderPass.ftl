
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkAttachmentDescription ${config.attachments}[1];
    ${config.attachments}[0].format = ${config.format};
    ${config.attachments}[0].samples = VK_SAMPLE_COUNT_1_BIT;
    ${config.attachments}[0].loadOp = VK_ATTACHMENT_LOAD_OP_CLEAR;
    ${config.attachments}[0].storeOp = VK_ATTACHMENT_STORE_OP_STORE;
    ${config.attachments}[0].stencilLoadOp = VK_ATTACHMENT_LOAD_OP_DONT_CARE;
    ${config.attachments}[0].stencilStoreOp = VK_ATTACHMENT_STORE_OP_DONT_CARE;
    ${config.attachments}[0].initialLayout = VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL;
    ${config.attachments}[0].finalLayout = VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL;
    ${config.attachments}[0].flags = 0;

    <#--${config.attachments}[1].format = VK_FORMAT_D16_UNORM;-->
    <#--${config.attachments}[1].samples = VK_SAMPLE_COUNT_1_BIT;-->
    <#--${config.attachments}[1].loadOp = VK_ATTACHMENT_LOAD_OP_CLEAR;-->
    <#--${config.attachments}[1].storeOp = VK_ATTACHMENT_STORE_OP_DONT_CARE;-->
    <#--${config.attachments}[1].stencilLoadOp = VK_ATTACHMENT_LOAD_OP_DONT_CARE;-->
    <#--${config.attachments}[1].stencilStoreOp = VK_ATTACHMENT_STORE_OP_DONT_CARE;-->
    <#--${config.attachments}[1].initialLayout = VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL;-->
    <#--${config.attachments}[1].finalLayout = VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL;-->
    <#--${config.attachments}[1].flags = 0;-->

    VkAttachmentReference ${config.colour} = {};
    ${config.colour}.attachment = 0;
    ${config.colour}.layout = VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL;

    VkAttachmentReference ${config.depth} = {};
    ${config.depth}.attachment = 1;
    ${config.depth}.layout = VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL;

    VkSubpassDescription ${config.subpass} = {};
    ${config.subpass}.pipelineBindPoint = VK_PIPELINE_BIND_POINT_GRAPHICS;
    ${config.subpass}.flags = 0;
    ${config.subpass}.inputAttachmentCount = 0;
    ${config.subpass}.pInputAttachments = NULL;
    ${config.subpass}.colorAttachmentCount = 1;
    ${config.subpass}.pColorAttachments = &${config.colour};
    ${config.subpass}.pResolveAttachments = NULL;
    ${config.subpass}.pDepthStencilAttachment = NULL;
    ${config.subpass}.preserveAttachmentCount = 0;
    ${config.subpass}.pPreserveAttachments = NULL;

    VkRenderPassCreateInfo ${config.renderpassCreateInfo} = {};
    ${config.renderpassCreateInfo}.sType = VK_STRUCTURE_TYPE_RENDER_PASS_CREATE_INFO;
    ${config.renderpassCreateInfo}.pNext = NULL;
    ${config.renderpassCreateInfo}.attachmentCount = 1;
    ${config.renderpassCreateInfo}.pAttachments = ${config.attachments};
    ${config.renderpassCreateInfo}.subpassCount = 1;
    ${config.renderpassCreateInfo}.pSubpasses = &${config.subpass};
    ${config.renderpassCreateInfo}.dependencyCount = 0;
    ${config.renderpassCreateInfo}.pDependencies = NULL;

    VkRenderPass ${config.renderpass};
    VkResult ${config.result} = vkCreateRenderPass(${config.device}.device, &${config.renderpassCreateInfo},
           NULL, &${config.renderpass});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);
    </#if>
