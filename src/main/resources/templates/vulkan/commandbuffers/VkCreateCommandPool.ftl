
    <#if config.isBad()>
    <#else>
    // ID: ${config.id}
    VkCommandPoolCreateInfo ${config.cmdPoolCreateInfo} = {};
    ${config.cmdPoolCreateInfo}.sType = VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO;
    ${config.cmdPoolCreateInfo}.pNext = NULL;
    ${config.cmdPoolCreateInfo}.queueFamilyIndex = ${config.randomDevice}.queueFamilyIndex;
    ${config.cmdPoolCreateInfo}.flags = ${config.flags};

    VkCommandPool ${config.commandPool};
    VkResult ${config.result} = vkCreateCommandPool(${config.randomDevice}.device,
            &${config.cmdPoolCreateInfo}, NULL, &${config.commandPool});

    assert((${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_SUCCESS));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);
    </#if>
