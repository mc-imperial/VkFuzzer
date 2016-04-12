
    <#if config.isBad()>
    <#else>
    // ID: ${config.id}
    uint32_t ${config.randomIndex} = rand() % ${config.queueFamilyIndex}.size();
    uint32_t ${config.randomQueueIndex} = ${config.queueFamilyIndex}[${config.randomIndex}];
    VkDevice ${config.randomDevice} = ${config.logicalDevice}[${config.randomIndex}];

    VkCommandPoolCreateInfo ${config.cmdPoolCreateInfo} = {};
    ${config.cmdPoolCreateInfo}.sType = VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO;
    ${config.cmdPoolCreateInfo}.pNext = NULL;
    ${config.cmdPoolCreateInfo}.queueFamilyIndex = ${config.randomQueueIndex};
    ${config.cmdPoolCreateInfo}.flags = ${config.flags};

    VkCommandPool ${config.commandPool};
    VkResult ${config.result} = vkCreateCommandPool(${config.randomDevice},
            &${config.cmdPoolCreateInfo}, NULL, &${config.commandPool});

    assert((${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_SUCCESS));

    assert(${config.result} == VK_SUCCESS);
    </#if>
