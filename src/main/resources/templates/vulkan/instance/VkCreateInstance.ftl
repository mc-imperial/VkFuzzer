
    // ID: ${config.id}
    // Create instance
    VkInstance ${config.instanceName};
    VkResult ${config.result} = vkCreateInstance(&${config.instanceCreateInfo}, ${config.alloc}, &${config.instanceName});

    assert(${config.result} ${config.isBad()?then('!=','==')} VK_SUCCESS);

    checkResultOutOfMemory(${config.result});
