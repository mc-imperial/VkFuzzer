
    // ID: ${config.id}
    // Create instance
    VkInstance ${config.instanceName};
    VkResult ${config.result} = vkCreateInstance(&${config.instanceCreateInfo}, ${config.alloc}, &${config.instanceName});
    assert(${config.result} == VK_SUCCESS);
