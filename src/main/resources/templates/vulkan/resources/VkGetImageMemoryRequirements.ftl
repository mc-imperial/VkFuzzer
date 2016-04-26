
    //ID: ${config.id}
    <#if config.isBad()>
    <#else>
    VkMemoryRequirements ${config.memoryRequirements};
    vkGetImageMemoryRequirements(${config.device}.device, ${config.image}, &${config.memoryRequirements});
    </#if>
