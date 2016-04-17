
    //ID: ${config.id}
    VkQueue ${config.queue};
    uint32_t ${config.queueCountIndex} = ${config.queueIndex};
    uint32_t ${config.queueFamilyIndex} = ${config.familyIndex};

    vkGetDeviceQueue(${config.device}, ${config.queueFamilyIndex},
            ${config.queueCountIndex}, &${config.queue});