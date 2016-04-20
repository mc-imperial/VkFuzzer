
    //ID: ${config.id}
    VkMemoryRequirements ${config.memoryRequirements};
    vkGetBufferMemoryRequirements(${config.device}, ${config.buffer}, &${config.memoryRequirements});
