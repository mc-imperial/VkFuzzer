
    // ID: ${id}
    // Initialize the VkApplicationInfo structure
    VkApplicationInfo ${variableName} = {};
    ${variableName}.sType = ${type};
    ${variableName}.pNext = ${next};
    ${variableName}.pApplicationName = "${applicationName}";
    ${variableName}.applicationVersion = ${applicationVersion};
    ${variableName}.pEngineName = "${engineName}";
    ${variableName}.engineVersion = ${engineVersion};
    ${variableName}.apiVersion = ${apiVersion};
