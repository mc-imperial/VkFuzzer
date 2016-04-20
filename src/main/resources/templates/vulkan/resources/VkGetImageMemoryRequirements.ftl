
    //ID: ${config.id}
    VkMemoryRequirements ${config.memoryRequirements};
    vkGetImageMemoryRequirements(${config.device}, ${config.image}, &${config.memoryRequirements});
