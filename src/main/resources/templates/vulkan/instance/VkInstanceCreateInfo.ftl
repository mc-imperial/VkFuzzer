
    // ID: ${config.id}
    // Initialize the VkInstanceCreateInfo structure
    VkInstanceCreateInfo ${config.variableName} = {};
    ${config.variableName}.sType = ${config.type};
    ${config.variableName}.pNext = ${config.next};
    ${config.variableName}.flags = ${config.flags};
    ${config.variableName}.pApplicationInfo = &${config.applicationInfo};
    ${config.variableName}.enabledExtensionCount = ${config.enabledExtensionCount};
    ${config.variableName}.ppEnabledExtensionNames = ${config.enabledExtensionNames};
    ${config.variableName}.enabledLayerCount = ${config.enabledLayerCount};
    ${config.variableName}.ppEnabledLayerNames = ${config.enabledLayerNames};
