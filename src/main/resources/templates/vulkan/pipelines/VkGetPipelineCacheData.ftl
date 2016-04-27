
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    std::vector<char> ${config.pipelineCacheData};
    size_t ${config.size} = 0;

    VkResult ${config.result} = vkGetPipelineCacheData(${config.device}.device,
            ${config.pipelineCache}, &${config.size}, NULL);

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    VkResult ${config.result} = vkGetPipelineCacheData(${config.device}.device,
            ${config.pipelineCache}, &${config.size}, ${config.pipelineCacheData}.data());

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});
    </#if>
