
    // ${config.id}
    // Get number of available GPUs
    std::vector<VkPhysicalDevice> ${config.gpus};
    uint32_t ${config.gpuCount} = 0;
    VkResult ${config.result} = vkEnumeratePhysicalDevices(${config.instance}, &${config.gpuCount}, NULL);

    assert(${config.result} == VK_SUCCESS);
    assert(${config.gpuCount} > 0);

    gpus.resize(${config.gpuCount});
    gpuProperties.resize(${config.gpuCount});

    // Get information of available GPUs
    ${config.result} = vkEnumeratePhysicalDevices(${config.instance}, &${config.gpuCount}, ${config.gpus}.data());
    assert(${config.result} == VK_SUCCESS);