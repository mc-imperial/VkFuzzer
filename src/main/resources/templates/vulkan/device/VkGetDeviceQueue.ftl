
    //ID: ${config.id}
    VkQueue ${config.queue};
    uint32_t ${config.queueCountIndex} = rand() % ${config.device}.maxQueueCount;
    uint32_t ${config.queueFamilyIndex} = ${config.device}.queueFamilyIndex;

    vkGetDeviceQueue(${config.device}.device, ${config.queueFamilyIndex},
            ${config.queueCountIndex}, &${config.queue});
