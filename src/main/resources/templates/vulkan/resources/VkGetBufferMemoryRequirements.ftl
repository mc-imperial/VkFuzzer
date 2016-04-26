
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkMemoryRequirements ${config.memoryRequirements};
    vkGetBufferMemoryRequirements(${config.device}.device, ${config.buffer}, &${config.memoryRequirements});
    </#if>
