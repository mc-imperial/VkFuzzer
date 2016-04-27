
std::ifstream triangleVert("triangle-vert.spv", std::ios::binary);
std::ifstream triangleFrag("triangle-frag.spv", std::ios::binary);
std::vector<uint32_t> triangleVertContents;
std::vector<uint32_t> triangleFragContents;

triangleVert.seekg (0, std::ios::end);
int length = triangleVert.tellg();
triangleVert.seekg (0, std::ios::beg);
triangleVertContents.resize(length/sizeof(uint32_t));
triangleVert.read((char*)triangleVertContents.data(), length);

_engineComponents->_shaderStages.push_back({});
_engineComponents->_shaderStages.push_back({});

_engineComponents->_shaderStages[0].sType  = VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO;
_engineComponents->_shaderStages[0].pNext  = NULL;
_engineComponents->_shaderStages[0].pSpecializationInfo = NULL;
_engineComponents->_shaderStages[0].flags = 0;
_engineComponents->_shaderStages[0].stage = VK_SHADER_STAGE_VERTEX_BIT;
_engineComponents->_shaderStages[0].pName = "main";

VkShaderModuleCreateInfo moduleCreateInfo;
moduleCreateInfo.sType = VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO;
moduleCreateInfo.pNext = NULL;
moduleCreateInfo.flags = 0;
moduleCreateInfo.codeSize = triangleVertContents.size() * sizeof(uint32_t);
moduleCreateInfo.pCode = triangleVertContents.data();
VkResult res = vkCreateShaderModule(_engineComponents->_device, &moduleCreateInfo, NULL, &_engineComponents->_shaderStages[0].module);

assert(res == VK_SUCCESS);

_engineComponents->_shaderStages[1].sType  = VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO;
_engineComponents->_shaderStages[1].pNext  = NULL;
_engineComponents->_shaderStages[1].pSpecializationInfo = NULL;
_engineComponents->_shaderStages[1].flags = 0;
_engineComponents->_shaderStages[1].stage = VK_SHADER_STAGE_FRAGMENT_BIT;
_engineComponents->_shaderStages[1].pName = "main";

triangleFrag.seekg (0, std::ios::end);
length = triangleFrag.tellg();
triangleFrag.seekg (0, std::ios::beg);

triangleFragContents.resize(length/sizeof(uint32_t));
triangleFrag.read((char*)triangleFragContents.data(), length);

moduleCreateInfo.sType = VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO;
moduleCreateInfo.pNext = NULL;
moduleCreateInfo.flags = 0;
moduleCreateInfo.codeSize = triangleFragContents.size() * sizeof(uint32_t);
moduleCreateInfo.pCode = triangleFragContents.data();
res = vkCreateShaderModule(_engineComponents->_device, &moduleCreateInfo, NULL, &_engineComponents->_shaderStages[1].module);

assert(res == VK_SUCCESS);