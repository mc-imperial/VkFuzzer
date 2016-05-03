
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}

    VkVertexInputBindingDescription ${config.vertexBindings};
    ${config.vertexBindings}.binding = 0;
    ${config.vertexBindings}.inputRate = VK_VERTEX_INPUT_RATE_VERTEX;
    ${config.vertexBindings}.stride = sizeof(triData[0]);

    VkVertexInputAttributeDescription ${config.vertexAttributes}[2];
    ${config.vertexAttributes}[0].binding = 0;
    ${config.vertexAttributes}[0].location = 0;
    ${config.vertexAttributes}[0].format = VK_FORMAT_R32G32B32A32_SFLOAT;
    ${config.vertexAttributes}[0].offset = 0;
    ${config.vertexAttributes}[1].binding = 0;
    ${config.vertexAttributes}[1].location = 1;
    ${config.vertexAttributes}[1].format = VK_FORMAT_R32G32B32A32_SFLOAT;
    ${config.vertexAttributes}[1].offset = 16;

    VkDynamicState ${config.dynamicStatesEnables}[VK_DYNAMIC_STATE_RANGE_SIZE];
    memset(${config.dynamicStatesEnables}, 0, sizeof(${config.dynamicStatesEnables}));

    VkPipelineDynamicStateCreateInfo ${config.dynamicStateCreateInfo} = {};
    ${config.dynamicStateCreateInfo}.sType = VK_STRUCTURE_TYPE_PIPELINE_DYNAMIC_STATE_CREATE_INFO;
    ${config.dynamicStateCreateInfo}.pNext = NULL;
    ${config.dynamicStateCreateInfo}.pDynamicStates = ${config.dynamicStatesEnables};
    ${config.dynamicStateCreateInfo}.dynamicStateCount = 0;

    VkPipelineVertexInputStateCreateInfo ${config.vertexInputStateCreateInfo};
    ${config.vertexInputStateCreateInfo}.sType = VK_STRUCTURE_TYPE_PIPELINE_VERTEX_INPUT_STATE_CREATE_INFO;
    ${config.vertexInputStateCreateInfo}.pNext = NULL;
    ${config.vertexInputStateCreateInfo}.flags = 0;
    ${config.vertexInputStateCreateInfo}.vertexBindingDescriptionCount = 1;
    ${config.vertexInputStateCreateInfo}.pVertexBindingDescriptions = &${config.vertexBindings};
    ${config.vertexInputStateCreateInfo}.vertexAttributeDescriptionCount = 2;
    ${config.vertexInputStateCreateInfo}.pVertexAttributeDescriptions = ${config.vertexAttributes};

    VkPipelineInputAssemblyStateCreateInfo ${config.inputAssemblyStateCreateInfo};
    ${config.inputAssemblyStateCreateInfo}.sType = VK_STRUCTURE_TYPE_PIPELINE_INPUT_ASSEMBLY_STATE_CREATE_INFO;
    ${config.inputAssemblyStateCreateInfo}.pNext = NULL;
    ${config.inputAssemblyStateCreateInfo}.flags = 0;
    ${config.inputAssemblyStateCreateInfo}.primitiveRestartEnable = VK_FALSE;
    ${config.inputAssemblyStateCreateInfo}.topology = VK_PRIMITIVE_TOPOLOGY_TRIANGLE_LIST;

    VkPipelineRasterizationStateCreateInfo ${config.rasterisationStateCreateInfo};
    ${config.rasterisationStateCreateInfo}.sType = VK_STRUCTURE_TYPE_PIPELINE_RASTERIZATION_STATE_CREATE_INFO;
    ${config.rasterisationStateCreateInfo}.pNext = NULL;
    ${config.rasterisationStateCreateInfo}.flags = 0;
    ${config.rasterisationStateCreateInfo}.polygonMode = VK_POLYGON_MODE_FILL;
    ${config.rasterisationStateCreateInfo}.cullMode = VK_CULL_MODE_BACK_BIT;
    ${config.rasterisationStateCreateInfo}.frontFace = VK_FRONT_FACE_CLOCKWISE;
    ${config.rasterisationStateCreateInfo}.depthClampEnable = VK_FALSE;
    ${config.rasterisationStateCreateInfo}.rasterizerDiscardEnable = VK_FALSE;
    ${config.rasterisationStateCreateInfo}.depthBiasEnable = VK_FALSE;
    ${config.rasterisationStateCreateInfo}.depthBiasConstantFactor = 0;
    ${config.rasterisationStateCreateInfo}.depthBiasClamp = 0;
    ${config.rasterisationStateCreateInfo}.depthBiasSlopeFactor = 0;
    ${config.rasterisationStateCreateInfo}.lineWidth = 0;

    VkPipelineColorBlendAttachmentState ${config.attachmentState}[1];
    ${config.attachmentState}[0].colorWriteMask = 0xf;
    ${config.attachmentState}[0].blendEnable = VK_FALSE;
    ${config.attachmentState}[0].alphaBlendOp = VK_BLEND_OP_ADD;
    ${config.attachmentState}[0].colorBlendOp = VK_BLEND_OP_ADD;
    ${config.attachmentState}[0].srcColorBlendFactor = VK_BLEND_FACTOR_ZERO;
    ${config.attachmentState}[0].dstColorBlendFactor = VK_BLEND_FACTOR_ZERO;
    ${config.attachmentState}[0].srcAlphaBlendFactor = VK_BLEND_FACTOR_ZERO;
    ${config.attachmentState}[0].dstAlphaBlendFactor = VK_BLEND_FACTOR_ZERO;

    VkPipelineColorBlendStateCreateInfo ${config.colourBlendStateCreateInfo};
    ${config.colourBlendStateCreateInfo}.sType = VK_STRUCTURE_TYPE_PIPELINE_COLOR_BLEND_STATE_CREATE_INFO;
    ${config.colourBlendStateCreateInfo}.flags = 0;
    ${config.colourBlendStateCreateInfo}.pNext = NULL;
    ${config.colourBlendStateCreateInfo}.attachmentCount = 1;
    ${config.colourBlendStateCreateInfo}.pAttachments = ${config.attachmentState};
    ${config.colourBlendStateCreateInfo}.logicOpEnable = VK_FALSE;
    ${config.colourBlendStateCreateInfo}.logicOp = VK_LOGIC_OP_NO_OP;
    ${config.colourBlendStateCreateInfo}.blendConstants[0] = 1.0f;
    ${config.colourBlendStateCreateInfo}.blendConstants[1] = 1.0f;
    ${config.colourBlendStateCreateInfo}.blendConstants[2] = 1.0f;
    ${config.colourBlendStateCreateInfo}.blendConstants[3] = 1.0f;

    VkPipelineViewportStateCreateInfo ${config.viewportStateCreateInfo} = {};
    ${config.viewportStateCreateInfo}.sType = VK_STRUCTURE_TYPE_PIPELINE_VIEWPORT_STATE_CREATE_INFO;
    ${config.viewportStateCreateInfo}.pNext = NULL;
    ${config.viewportStateCreateInfo}.flags = 0;
    ${config.viewportStateCreateInfo}.viewportCount = 1;
    ${config.viewportStateCreateInfo}.scissorCount = 1;
    ${config.viewportStateCreateInfo}.pScissors = NULL;
    ${config.viewportStateCreateInfo}.pViewports = NULL;

    ${config.dynamicStatesEnables}[${config.dynamicStateCreateInfo}.dynamicStateCount++] = VK_DYNAMIC_STATE_VIEWPORT;
    ${config.dynamicStatesEnables}[${config.dynamicStateCreateInfo}.dynamicStateCount++] = VK_DYNAMIC_STATE_SCISSOR;

    VkPipelineDepthStencilStateCreateInfo ${config.stencilStateCreateInfo};
    ${config.stencilStateCreateInfo}.sType = VK_STRUCTURE_TYPE_PIPELINE_DEPTH_STENCIL_STATE_CREATE_INFO;
    ${config.stencilStateCreateInfo}.pNext = NULL;
    ${config.stencilStateCreateInfo}.flags = 0;
    ${config.stencilStateCreateInfo}.depthTestEnable = VK_FALSE;
    ${config.stencilStateCreateInfo}.depthWriteEnable = VK_FALSE;
    ${config.stencilStateCreateInfo}.depthCompareOp = VK_COMPARE_OP_LESS_OR_EQUAL;
    ${config.stencilStateCreateInfo}.depthBoundsTestEnable = VK_FALSE;
    ${config.stencilStateCreateInfo}.stencilTestEnable = VK_FALSE;
    ${config.stencilStateCreateInfo}.back.failOp = VK_STENCIL_OP_KEEP;
    ${config.stencilStateCreateInfo}.back.passOp = VK_STENCIL_OP_KEEP;
    ${config.stencilStateCreateInfo}.back.compareOp = VK_COMPARE_OP_ALWAYS;
    ${config.stencilStateCreateInfo}.back.compareMask = 0;
    ${config.stencilStateCreateInfo}.back.reference = 0;
    ${config.stencilStateCreateInfo}.back.depthFailOp = VK_STENCIL_OP_KEEP;
    ${config.stencilStateCreateInfo}.back.writeMask = 0;
    ${config.stencilStateCreateInfo}.minDepthBounds = 0;
    ${config.stencilStateCreateInfo}.maxDepthBounds = 0;
    ${config.stencilStateCreateInfo}.stencilTestEnable = VK_FALSE;
    ${config.stencilStateCreateInfo}.front = ${config.stencilStateCreateInfo}.back;

    VkPipelineMultisampleStateCreateInfo ${config.multisampleStateCreateInfo};
    ${config.multisampleStateCreateInfo}.sType = VK_STRUCTURE_TYPE_PIPELINE_MULTISAMPLE_STATE_CREATE_INFO;
    ${config.multisampleStateCreateInfo}.pNext = NULL;
    ${config.multisampleStateCreateInfo}.flags = 0;
    ${config.multisampleStateCreateInfo}.pSampleMask = NULL;
    ${config.multisampleStateCreateInfo}.rasterizationSamples = VK_SAMPLE_COUNT_1_BIT;
    ${config.multisampleStateCreateInfo}.sampleShadingEnable = VK_FALSE;
    ${config.multisampleStateCreateInfo}.alphaToCoverageEnable = VK_FALSE;
    ${config.multisampleStateCreateInfo}.alphaToOneEnable = VK_FALSE;
    ${config.multisampleStateCreateInfo}.minSampleShading = 0.0;

    std::vector<VkPipelineShaderStageCreateInfo> ${config.shaderStages};
    ${config.shaderStages}.push_back(${config.vertexShader});
    ${config.shaderStages}.push_back(${config.fragmentShader});

    VkGraphicsPipelineCreateInfo ${config.pipelineCreateInfo};
    ${config.pipelineCreateInfo}.sType = VK_STRUCTURE_TYPE_GRAPHICS_PIPELINE_CREATE_INFO;
    ${config.pipelineCreateInfo}.pNext = NULL;
    ${config.pipelineCreateInfo}.layout = ${config.pipelineLayout};
    ${config.pipelineCreateInfo}.basePipelineHandle = 0;
    ${config.pipelineCreateInfo}.basePipelineIndex = 0;
    ${config.pipelineCreateInfo}.flags = 0;
    ${config.pipelineCreateInfo}.pVertexInputState = &${config.vertexInputStateCreateInfo};
    ${config.pipelineCreateInfo}.pInputAssemblyState = &${config.inputAssemblyStateCreateInfo};
    ${config.pipelineCreateInfo}.pRasterizationState = &${config.rasterisationStateCreateInfo};
    ${config.pipelineCreateInfo}.pColorBlendState = &${config.colourBlendStateCreateInfo};
    ${config.pipelineCreateInfo}.pTessellationState = NULL;
    ${config.pipelineCreateInfo}.pMultisampleState = &${config.multisampleStateCreateInfo};
    ${config.pipelineCreateInfo}.pDynamicState = &${config.dynamicStateCreateInfo};
    ${config.pipelineCreateInfo}.pViewportState = &${config.viewportStateCreateInfo};
    ${config.pipelineCreateInfo}.pDepthStencilState = &${config.stencilStateCreateInfo};
    ${config.pipelineCreateInfo}.pStages = ${config.shaderStages}.data();
    ${config.pipelineCreateInfo}.stageCount = ${config.shaderStages}.size();
    ${config.pipelineCreateInfo}.renderPass = ${config.renderpass};
    ${config.pipelineCreateInfo}.subpass = 0;

    VkPipeline ${config.graphicsPipeline};
    VkResult ${config.result} = VK_SUCCESS;
        //= vkCreateGraphicsPipelines(${config.device}.device, ${config.pipelineCache},
        //    1, &${config.pipelineCreateInfo}, NULL, &${config.graphicsPipeline});

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);
    </#if>
