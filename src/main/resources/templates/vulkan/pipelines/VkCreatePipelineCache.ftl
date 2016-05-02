
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkPipelineCacheCreateInfo ${config.pipelineCacheCreateInfo} = {};
    ${config.pipelineCacheCreateInfo}.sType = VK_STRUCTURE_TYPE_PIPELINE_CACHE_CREATE_INFO;
    ${config.pipelineCacheCreateInfo}.pNext = NULL;
    ${config.pipelineCacheCreateInfo}.flags = 0;
    ${config.pipelineCacheCreateInfo}.initialDataSize = 0;
    ${config.pipelineCacheCreateInfo}.pInitialData = 0;

    VkPipelineCache ${config.pipelineCache};
    VkResult ${config.result} = vkCreatePipelineCache(${config.device}.device, &${config.pipelineCacheCreateInfo},
            NULL, &${config.pipelineCache});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);
    </#if>
