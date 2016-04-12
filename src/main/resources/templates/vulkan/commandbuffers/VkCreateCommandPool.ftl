
    // ID: ${config.id}
    VkCommandPoolCreateInfo ${config.cmdPoolCreateInfo} = {};
    ${config.cmdPoolCreateInfo}.sType = VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO;
    ${config.cmdPoolCreateInfo}.pNext = NULL;
    ${config.cmdPoolCreateInfo}.queueFamilyIndex = ${config.queueFamilyIndex};
    ${config.cmdPoolCreateInfo}.flags = ${config.flags};

    VkCommandPool ${config.commandPool};
    VkResult ${config.result} = vkCreateCommandPool(${config.logicalDevice},
            ${config.cmdPoolCreateInfo}, NULL, &${config.commandPool});

    assert(${config.result} == VK_SUCCESS);
