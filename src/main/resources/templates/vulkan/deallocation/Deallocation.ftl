
    //ID: ${config.id}
    <#list config.cmdBuffers as cmdBuffer>
    vkFreeCommandBuffers(${cmdBuffer.device}, ${cmdBuffer.commandPool},
            ${cmdBuffer.buffers}.size(), ${cmdBuffer.buffers}.data());
    </#list>

    <#list config.cmdPools as cmdPool>
    vkDestroyCommandPool(${cmdPool.randomDevice}, ${cmdPool.commandPool}, NULL);
    </#list>

    <#list config.devices as device>
    for (VkDevice device : ${device.logicalDevices})
    {
        vkDestroyDevice(device, NULL);
    }
    </#list>

    <#list config.instances as instance>
    vkDestroyInstance(${instance.instanceName}, NULL);
    </#list>
