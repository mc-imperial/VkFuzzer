
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VertexBuffer ${config.vertexbuffer}= {};
    ${config.vertexbuffer}.buffer = ${config.buffer};

    uint32_t ${config.typeIndex};
    bool ${config.found} = memoryTypeFromProperties(${config.device}.deviceMemoryProperties,
            ${config.memoryRequirements}.memoryTypeBits, VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT,
            &${config.typeIndex});

    if (!${config.found})
    {
        std::cerr << "Could not find suitable memory index" << std::endl;
        exit(NO_MEMORY_TYPE_INDEX_FOUND);
    }

    VkMemoryAllocateInfo  ${config.memoryAllocateInfo} = {};
    ${config.memoryAllocateInfo}.sType = VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO;
    ${config.memoryAllocateInfo}.pNext = NULL;
    ${config.memoryAllocateInfo}.memoryTypeIndex = ${config.typeIndex};
    ${config.memoryAllocateInfo}.allocationSize =  ${config.memoryRequirements}.size;

    VkResult ${config.result} = vkAllocateMemory(${config.device}.device, &${config.memoryAllocateInfo},
            NULL, &${config.vertexbuffer}.deviceMemory);

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_TOO_MANY_OBJECTS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);

    uint8_t *${config.dataPointer};
    ${config.result} = vkMapMemory(${config.device}.device, ${config.vertexbuffer}.deviceMemory, 0,
            ${config.memoryRequirements}.size, 0, (void **)&${config.dataPointer});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_MEMORY_MAP_FAILED)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);

    std::vector<float> ${config.randomData};
    ${config.randomData}.resize(${config.bufferSize} / sizeof(float));

    // Generate random data
    for (uint32_t i = 0; i < ${config.randomData}.size(); ++i)
    {
        ${config.randomData}[i] = dis(gen);
    }

    memcpy(${config.dataPointer}, ${config.randomData}.data(), ${config.randomData}.size());

    <#--vkUnmapMemory(${config.device}.device, ${config.vertexbuffer}.deviceMemory);-->

    ${config.result} = vkBindBufferMemory(${config.device}.device,
            ${config.vertexbuffer}.buffer, ${config.vertexbuffer}.deviceMemory, 0);

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);

    </#if>