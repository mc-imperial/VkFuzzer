
    // ID: ${id}
    // Create instance
    VkInstance ${instanceName};
    VkResult ${result} = vkCreateInstance(&${instanceInfo}, ${alloc}, &${instanceName});
    assert(${result} == VK_SUCCESS);
