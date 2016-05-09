
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    vkCmdEndRenderPass(${config.commandBuffer});

    </#if>