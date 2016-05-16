
    <#if config.isBad()>
    <#else>

    // Generate random data
    for (uint32_t i = 0; i < ${config.randomData}.size(); ++i)
    {
        ${config.randomData}[i] = randomFloat();
    }

    memcpy(${config.dataPointer}, ${config.randomData}.data(), ${config.randomData}.size());

    const VkDeviceSize ${config.offsets}[1] = {0};
    vkCmdBindVertexBuffers(${config.commandBuffer}, 0, 1, &${config.vertexbuffer}.buffer, ${config.offsets});

    vkCmdBindPipeline(${config.commandBuffer}, VK_PIPELINE_BIND_POINT_GRAPHICS,
            ${config.pipeline});

    VkViewport ${config.viewport};
    ${config.viewport}.height = (float)${config.swapchainExtent}.height;
    ${config.viewport}.width = (float)${config.swapchainExtent}.width;
    ${config.viewport}.minDepth = 0.0f;
    ${config.viewport}.maxDepth = 1.0f;
    ${config.viewport}.x = 0;
    ${config.viewport}.y = 0;

    vkCmdSetViewport(${config.commandBuffer}, 0, 1, &${config.viewport});

    VkRect2D ${config.scissor};
    ${config.scissor}.extent.width = ${config.swapchainExtent}.width;
    ${config.scissor}.extent.height = ${config.swapchainExtent}.height;
    ${config.scissor}.offset.x = 0;
    ${config.scissor}.offset.y = 0;

    vkCmdSetScissor(${config.commandBuffer}, 0, 1, &${config.scissor});

    vkCmdDraw(${config.commandBuffer}, ${config.randomData}.size(), 1, 0, 0);

    </#if>