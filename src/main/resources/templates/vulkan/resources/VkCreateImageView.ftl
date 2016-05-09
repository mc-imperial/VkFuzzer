
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    std::vector<SwapchainBuffer> ${config.swapchainBuffers};
    VkResult ${config.result};

    VkCommandBufferBeginInfo ${config.commandBufferBeginInfo} = {};
    ${config.commandBufferBeginInfo}.sType = VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO;
    ${config.commandBufferBeginInfo}.pNext = NULL;
    ${config.commandBufferBeginInfo}.flags = 0;
    ${config.commandBufferBeginInfo}.pInheritanceInfo = NULL;

    ${config.result} = vkBeginCommandBuffer(${config.commandBuffer}.data()[0], &${config.commandBufferBeginInfo});
    assert(${config.result} == VK_SUCCESS);

    for (uint32_t i = 0; i < ${config.swapchainImageCount}; i++)
    {
        VkImageSubresourceRange ${config.subresourceRange} = {};
        ${config.subresourceRange}.aspectMask = ${config.aspectMask};
        ${config.subresourceRange}.baseMipLevel = ${config.baseMipLevel};
        ${config.subresourceRange}.levelCount = ${config.levelCount};
        ${config.subresourceRange}.baseArrayLayer = ${config.baseArrayLayer};
        ${config.subresourceRange}.layerCount = ${config.layerCount};

        VkComponentMapping ${config.components} = {};
        ${config.components}.a = VK_COMPONENT_SWIZZLE_A;
        ${config.components}.b = VK_COMPONENT_SWIZZLE_B;
        ${config.components}.g = VK_COMPONENT_SWIZZLE_G;
        ${config.components}.r = VK_COMPONENT_SWIZZLE_R;

        VkImageViewCreateInfo ${config.vkImageViewCreateInfo} = {};
        ${config.vkImageViewCreateInfo}.sType = VK_STRUCTURE_TYPE_IMAGE_VIEW_CREATE_INFO;
        ${config.vkImageViewCreateInfo}.pNext = NULL;
        ${config.vkImageViewCreateInfo}.flags = 0;
        ${config.vkImageViewCreateInfo}.image = ${config.swapchainImages}[i];
        ${config.vkImageViewCreateInfo}.viewType = ${config.viewType};
        ${config.vkImageViewCreateInfo}.format = ${config.format};
        ${config.vkImageViewCreateInfo}.components = ${config.components};
        ${config.vkImageViewCreateInfo}.subresourceRange = ${config.subresourceRange};

        SwapchainBuffer swapchainBuffer;
        swapchainBuffer.image = ${config.swapchainImages}[i];

        ${config.result} = vkCreateImageView(${config.device}.device, &${config.vkImageViewCreateInfo},
                NULL, &swapchainBuffer.view);

        assert((${config.result} == VK_SUCCESS)
                || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
                || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

        checkResultOutOfMemory(${config.result});

        assert(${config.result} == VK_SUCCESS);

        ${config.swapchainBuffers}.push_back(swapchainBuffer);

        setImageLayout(${config.commandBuffer}[0], swapchainBuffer.image,
                VK_IMAGE_ASPECT_COLOR_BIT, VK_IMAGE_LAYOUT_UNDEFINED,
                VK_IMAGE_LAYOUT_PRESENT_SRC_KHR);
    }

    ${config.result} = vkEndCommandBuffer(${config.commandBuffer}[0]);
    assert(${config.result} == VK_SUCCESS);

    VkSubmitInfo submitInfo = {};
    submitInfo.sType = VK_STRUCTURE_TYPE_SUBMIT_INFO,
    submitInfo.pNext = NULL,
    submitInfo.waitSemaphoreCount = 0,
    submitInfo.pWaitSemaphores = NULL,
    submitInfo.pWaitDstStageMask = NULL,
    submitInfo.commandBufferCount = 1,
    submitInfo.pCommandBuffers = ${config.commandBuffer}.data(),
    submitInfo.signalSemaphoreCount = 0,
    submitInfo.pSignalSemaphores = NULL;

    VkQueue theQueue;
    vkGetDeviceQueue(${config.device}.device, ${config.device}.queueFamilyIndex,
            0, &theQueue);

    ${config.result} = vkQueueSubmit(theQueue, 1, &submit_info, VK_NULL_HANDLE);
    assert(!${config.result});

    ${config.result} = vkQueueWaitIdle(theQueue);
    assert(!${config.result});

    </#if>