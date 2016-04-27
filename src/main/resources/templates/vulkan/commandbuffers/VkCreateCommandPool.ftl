
    <#if config.isBad()>
    <#else>
    // ID: ${config.id}
    uint32_t ${config.randomIndex} = rand() % ${config.logicalDevice}.size();
    FuzzerLogicalDevice ${config.randomDevice} = ${config.logicalDevice}[${config.randomIndex}];

    VkCommandPoolCreateInfo ${config.cmdPoolCreateInfo} = {};
    ${config.cmdPoolCreateInfo}.sType = VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO;
    ${config.cmdPoolCreateInfo}.pNext = NULL;
    ${config.cmdPoolCreateInfo}.queueFamilyIndex = ${config.randomDevice}.queueFamilyIndex;
    ${config.cmdPoolCreateInfo}.flags = ${config.flags};

    VkCommandPool ${config.commandPool};
    VkResult ${config.result} = vkCreateCommandPool(${config.randomDevice}.device,
            &${config.cmdPoolCreateInfo}, NULL, &${config.commandPool});

    assert((${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_SUCCESS));

    if ((${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY))
    {
        std::cerr << "Run out of memory. Exiting gracefully."
        return EXIT_RUN_OUT_OF_MEMORY;
    }

    assert(${config.result} == VK_SUCCESS);
    </#if>
