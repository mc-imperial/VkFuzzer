
    //ID: ${config.id}
    <#list config.cmdBuffers as cmdBuffer>
        <#if cmdBuffer.isBad()>
        <#else>
        vkFreeCommandBuffers(${cmdBuffer.device}.device, ${cmdBuffer.commandPool},
                ${cmdBuffer.buffers}.size(), ${cmdBuffer.buffers}.data());
        </#if>
    </#list>

    <#list config.cmdPools as cmdPool>
        <#if cmdPool.isBad()>
        <#else>
            vkDestroyCommandPool(${cmdPool.randomDevice}.device, ${cmdPool.commandPool}, NULL);
        </#if>
    </#list>

    <#list config.devices as device>
        <#if device.isBad()>
        <#else>
            <#list device.devicePropertiesConfigs as devicePropertiesConfig>
                <#if devicePropertiesConfig.isBad()>
                <#else>
                    <#list devicePropertiesConfig.devices as theDevices>
                        <#if theDevices.isBad()>
                        <#else>
                        for (FuzzerLogicalDevice fuzzerLogicalDevice : ${device.logicalDevices}${devicePropertiesConfig?index}${theDevices?index})
                        {
                            vkDestroyDevice(fuzzerLogicalDevice.device, NULL);
                        }
                        </#if>
                    </#list>
                </#if>
            </#list>
        </#if>
    </#list>

    <#list config.instances as instance>
        <#if instance.isBad()>
        <#else>
        vkDestroyInstance(${instance.instanceName}, NULL);
        </#if>
    </#list>
