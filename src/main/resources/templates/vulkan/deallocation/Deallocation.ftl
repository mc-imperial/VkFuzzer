
    //ID: ${config.id}
    <#list config.cmdBuffers as cmdBuffer>
    vkFreeCommandBuffers(${cmdBuffer.device}, ${cmdBuffer.commandPool},
            ${cmdBuffer.buffers}.size(), ${cmdBuffer.buffers}.data());
    </#list>

    <#list config.cmdPools as cmdPool>
    vkDestroyCommandPool(${cmdPool.randomDevice}, ${cmdPool.commandPool}, NULL);
    </#list>

    <#list config.devices as device>
    <#list device.devicePropertiesConfigs as devicePropertiesConfig>
    <#list devicePropertiesConfig.devices as theDevices>
    for (VkDevice device : ${device.logicalDevices}${devicePropertiesConfig?index}${theDevices?index})
    {
        vkDestroyDevice(device, NULL);
    }
    </#list>
    </#list>
    </#list>

    <#list config.instances as instance>
    vkDestroyInstance(${instance.instanceName}, NULL);
    </#list>
