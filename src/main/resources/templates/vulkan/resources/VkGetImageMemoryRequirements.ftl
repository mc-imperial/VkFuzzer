
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkMemoryRequirements ${config.memoryRequirements};
    //vkGetImageMemoryRequirements(${config.device}.device, ${config.image}, &${config.memoryRequirements});
    </#if>
