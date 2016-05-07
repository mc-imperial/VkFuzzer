
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkPresentInfoKHR ${config.presentInfo};
    ${config.presentInfo}.sType = VK_STRUCTURE_TYPE_PRESENT_INFO_KHR;
    ${config.presentInfo}.pNext = NULL;
    ${config.presentInfo}.swapchainCount = 1;
    ${config.presentInfo}.pSwapchains = &${config.swapchain};
    ${config.presentInfo}.pImageIndices = &${config.imageIndex};
    ${config.presentInfo}.pWaitSemaphores = NULL;
    ${config.presentInfo}.waitSemaphoreCount = 0;
    ${config.presentInfo}.pResults = NULL;

    VkResult ${config.result} = vkQueuePresentKHR(${config.queue}, &${config.presentInfo});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_SUBOPTIMAL_KHR)
            || (${config.result} == VK_ERROR_DEVICE_LOST)
            || (${config.result} == VK_ERROR_OUT_OF_DATE_KHR)
            || (${config.result} == VK_ERROR_SURFACE_LOST_KHR)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_SUBOPTIMAL_KHR));

    </#if>