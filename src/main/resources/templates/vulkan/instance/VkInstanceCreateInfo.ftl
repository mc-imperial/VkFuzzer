
    // ID: ${config.id}
    // Initialize the VkInstanceCreateInfo structure
    VkInstanceCreateInfo ${config.variableName} = {};
    ${config.variableName}.sType = ${config.type};
    ${config.variableName}.pNext = ${config.next};
    ${config.variableName}.flags = ${config.flags};
    ${config.variableName}.pApplicationInfo = &${config.applicationInfo};
    ${config.variableName}.enabledExtensionCount = instanceExtensions.size();
    ${config.variableName}.ppEnabledExtensionNames = instanceExtensions.data();
    ${config.variableName}.enabledLayerCount = ${config.enabledLayerCount};
    ${config.variableName}.ppEnabledLayerNames = ${config.enabledLayerNames};
