
    //ID: ${config.id}
    <#list config.descriptors as descriptor>
        <#if descriptor.isBad()>
        <#else>
        vkFreeDescriptorSets((${descriptor.device}.device, ${descriptor.descriptorPool}, ${descriptor.descriptor}, NULL);
        </#if>
    </#list>

    <#list config.shaders as shader>
        <#if shader.isBad()>
        <#else>
        vkDestroyShaderModule((${shader.device}.device, ${shader.shader}, NULL);
        </#if>
    </#list>

    <#list config.computePipelines as computePipeline>
        <#if computePipeline.isBad()>
        <#else>
    vkDestroyPipeline(${computePipeline.device}.device, ${computePipeline.computePipeline}, NULL);
        </#if>
    </#list>

    <#list config.graphicsPipelines as graphicsPipeline>
        <#if graphicsPipeline.isBad()>
        <#else>
    vkDestroyPipeline(${graphicsPipeline.device}.device, ${graphicsPipeline.graphicsPipeline}, NULL);
        </#if>
    </#list>

    <#list config.pipelineCaches as pipelineCache>
        <#if pipelineCache.isBad()>
        <#else>
    vkDestroyPipelineCache(${pipelineCache.device}.device, ${pipelineCache.pipelineCache}, NULL);
        </#if>
    </#list>

    <#list config.pipelineLayouts as pipelineLayout>
        <#if pipelineLayout.isBad()>
        <#else>
        vkDestroyPipelineLayout((${pipelineLayout.device}.device, ${pipelineLayout.pipelineLayout}, NULL);
        </#if>
    </#list>

    <#list config.imageViews as imageView>
        <#if imageView.isBad()>
        <#else>
    vkDestroyImageView(${imageView.device}.device, ${imageView.imageView}, NULL);
        </#if>
    </#list>

    <#list config.images as image>
        <#if image.isBad()>
        <#else>
    vkDestroyImage(${image.device}.device, ${image.image}, NULL);
        </#if>
    </#list>

    <#list config.bufferViews as bufferView>
        <#if bufferView.isBad()>
        <#else>
    vkDestroyBufferView(${bufferView.device}.device, ${bufferView.bufferView}, NULL);
        </#if>
    </#list>

    <#list config.buffers as buffer>
        <#if buffer.isBad()>
        <#else>
    vkDestroyBuffer(${buffer.device}.device, ${buffer.buffer}, NULL);
        </#if>
    </#list>

    <#list config.fences as fence>
        <#if fence.isBad()>
        <#else>
    vkDestroyFence(${fence.device}.device, ${fence.fence}, NULL);
        </#if>
    </#list>

    <#list config.events as event>
        <#if event.isBad()>
        <#else>
    vkDestroyEvent(${event.device}.device, ${event.event}, NULL);
        </#if>
    </#list>

    <#list config.semaphores as semaphore>
        <#if fence.isBad()>
        <#else>
    vkDestroySemaphore(${semaphore.device}.device, ${semaphore.semaphore}, NULL);
        </#if>
    </#list>

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
