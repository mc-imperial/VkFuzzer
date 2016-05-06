
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    std::ifstream ${config.vert}("simple-vert.spv", std::ios::binary);
    std::ifstream ${config.frag}("simple-frag.spv", std::ios::binary);
    std::vector<uint32_t> ${config.vertContents};
    std::vector<uint32_t> ${config.fragContents};

    ${config.vert}.seekg (0, std::ios::end);
    int ${config.length} = ${config.vert}.tellg();
    ${config.vert}.seekg (0, std::ios::beg);
    ${config.vertContents}.resize(${config.length}/sizeof(uint32_t));
    ${config.vert}.read((char*)${config.vertContents}.data(), ${config.length});

    VkPipelineShaderStageCreateInfo ${config.vertexPipelineShaderStageCreateInfo} = {};
    ${config.vertexPipelineShaderStageCreateInfo}.sType  = VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO;
    ${config.vertexPipelineShaderStageCreateInfo}.pNext  = NULL;
    ${config.vertexPipelineShaderStageCreateInfo}.pSpecializationInfo = NULL;
    ${config.vertexPipelineShaderStageCreateInfo}.flags = 0;
    ${config.vertexPipelineShaderStageCreateInfo}.stage = VK_SHADER_STAGE_VERTEX_BIT;
    ${config.vertexPipelineShaderStageCreateInfo}.pName = "main";

    VkShaderModuleCreateInfo ${config.vertexModuleCreateInfo};
    ${config.vertexModuleCreateInfo}.sType = VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO;
    ${config.vertexModuleCreateInfo}.pNext = NULL;
    ${config.vertexModuleCreateInfo}.flags = 0;
    ${config.vertexModuleCreateInfo}.codeSize = ${config.vertContents}.size() * sizeof(uint32_t);
    ${config.vertexModuleCreateInfo}.pCode = ${config.vertContents}.data();

    VkResult ${config.result} = vkCreateShaderModule(${config.device}.device,
            &${config.vertexModuleCreateInfo}, NULL, &${config.vertexPipelineShaderStageCreateInfo}.module);

    assert(${config.result} == VK_SUCCESS);

    ${config.frag}.seekg (0, std::ios::end);
    ${config.length} = ${config.frag}.tellg();
    ${config.frag}.seekg (0, std::ios::beg);
    ${config.fragContents}.resize(${config.length}/sizeof(uint32_t));
    ${config.frag}.read((char*)${config.fragContents}.data(), ${config.length});

    VkPipelineShaderStageCreateInfo ${config.fragmentPipelineShaderStageCreateInfo} = {};
    ${config.fragmentPipelineShaderStageCreateInfo}.sType  = VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO;
    ${config.fragmentPipelineShaderStageCreateInfo}.pNext  = NULL;
    ${config.fragmentPipelineShaderStageCreateInfo}.pSpecializationInfo = NULL;
    ${config.fragmentPipelineShaderStageCreateInfo}.flags = 0;
    ${config.fragmentPipelineShaderStageCreateInfo}.stage = VK_SHADER_STAGE_FRAGMENT_BIT;
    ${config.fragmentPipelineShaderStageCreateInfo}.pName = "main";

    VkShaderModuleCreateInfo ${config.fragmentModuleCreateInfo};
    ${config.fragmentModuleCreateInfo}.sType = VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO;
    ${config.fragmentModuleCreateInfo}.pNext = NULL;
    ${config.fragmentModuleCreateInfo}.flags = 0;
    ${config.fragmentModuleCreateInfo}.codeSize = ${config.fragContents}.size() * sizeof(uint32_t);
    ${config.fragmentModuleCreateInfo}.pCode = ${config.fragContents}.data();

    ${config.result} = vkCreateShaderModule(${config.device}.device,
            &${config.fragmentModuleCreateInfo}, NULL, &${config.fragmentPipelineShaderStageCreateInfo}.module);

    assert((${config.result} == VK_SUCCESS)
            || (${config.result} == VK_ERROR_OUT_OF_HOST_MEMORY)
            || (${config.result} == VK_ERROR_OUT_OF_DEVICE_MEMORY));

    checkResultOutOfMemory(${config.result});

    assert(${config.result} == VK_SUCCESS);
    </#if>