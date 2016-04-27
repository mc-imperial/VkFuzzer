
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkPipelineLayoutCreateInfo ${config.pipelineLayoutCreateInfo} = {};
    ${config.pipelineLayoutCreateInfo}.sType = VK_STRUCTURE_TYPE_PIPELINE_LAYOUT_CREATE_INFO;
    ${config.pipelineLayoutCreateInfo}.pNext = NULL;
    ${config.pipelineLayoutCreateInfo}.pushConstantRangeCount = 0;
    ${config.pipelineLayoutCreateInfo}.pPushConstantRanges = NULL;
    ${config.pipelineLayoutCreateInfo}.setLayoutCount = 0;
    ${config.pipelineLayoutCreateInfo}.pSetLayouts = NULL;

    VkPipelineLayout ${config.pipelineLayout};
    VkResult ${config.result} = vkCreatePipelineLayout(${config.device}.device, ${config.pipelineLayoutCreateInfo},
            NULL, &${config.pipelineLayout});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);
    </#if>
