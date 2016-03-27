
    // ID: ${id}
    // Initialize the VkInstanceCreateInfo structure
    VkInstanceCreateInfo ${variableName} = {};
    ${variableName}.sType = ${type}};
    ${variableName}.pNext = ${next}};
    ${variableName}.flags = ${flags}};
    ${variableName}.pApplicationInfo = &${applicationInfo};
    ${variableName}.enabledExtensionCount = ${enabledExtensionCount};
    ${variableName}.ppEnabledExtensionNames = ${enabledExtensionNames};
    ${variableName}.enabledLayerCount = ${enabledLayerCount};
    ${variableName}.ppEnabledLayerNames = ${enabledLayerNames};
