
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkSubmitInfo ${config.submitInfo}[1] = {};
    ${config.submitInfo}[0].pNext = NULL;
    ${config.submitInfo}[0].sType = VK_STRUCTURE_TYPE_SUBMIT_INFO;
    ${config.submitInfo}[0].waitSemaphoreCount = 0;
    ${config.submitInfo}[0].pWaitSemaphores = NULL;
    ${config.submitInfo}[0].commandBufferCount = 1;
    ${config.submitInfo}[0].pCommandBuffers = &${config.commandBuffer};
    ${config.submitInfo}[0].signalSemaphoreCount = 0;
    ${config.submitInfo}[0].pSignalSemaphores = NULL;

    VkResult ${config.result} = vkQueueSubmit(${config.queue}, 1,
            ${config.submitInfo}, VK_NULL_HANDLE);

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_DEVICE_LOST)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);

    </#if>
