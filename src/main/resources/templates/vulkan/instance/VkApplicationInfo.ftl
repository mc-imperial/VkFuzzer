
    // ID: ${config.id}
    // Initialize the VkApplicationInfo structure
    VkApplicationInfo ${config.variableName} = {};
    ${config.variableName}.sType = ${config.type};
    ${config.variableName}.pNext = ${config.next};
    ${config.variableName}.pApplicationName = "${config.applicationName}";
    ${config.variableName}.applicationVersion = ${config.applicationVersion};
    ${config.variableName}.pEngineName = "${config.engineName}";
    ${config.variableName}.engineVersion = ${config.engineVersion};
    ${config.variableName}.apiVersion = ${config.apiVersion};
