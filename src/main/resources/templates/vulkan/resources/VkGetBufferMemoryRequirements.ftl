
    //ID: ${config.id}
    <#if config.isBad()>
    <#else>
    VkMemoryRequirements ${config.memoryRequirements};
    vkGetBufferMemoryRequirements(${config.device}.device, ${config.buffer}, &${config.memoryRequirements});
    </#if>
