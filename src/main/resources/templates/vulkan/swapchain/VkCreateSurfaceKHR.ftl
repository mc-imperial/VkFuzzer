
    <#if config.isBad()>
    <#else>
    //ID: ${config.id}
    VkResult ${config.result};
    VkSurfaceKHR ${config.surface};
#ifdef _WIN32
    VkWin32SurfaceCreateInfoKHR ${config.surfaceCreateInfo} = {};
    ${config.surfaceCreateInfo}.sType = VK_STRUCTURE_TYPE_WIN32_SURFACE_CREATE_INFO_KHR;
    ${config.surfaceCreateInfo}.pNext = NULL;
    ${config.surfaceCreateInfo}.hinstance = config->instance;
    ${config.surfaceCreateInfo}.hwnd = 	config->window;
    ${config.result} = vkCreateWin32SurfaceKHR(${config.instance}, &${config.surfaceCreateInfo},
            NULL, &${config.surface});
#elif defined(__ANDROID__)
    GET_INSTANCE_PROC_ADDR(info.inst, CreateAndroidSurfaceKHR);
    VkAndroidSurfaceCreateInfoKHR ${config.surfaceCreateInfo};
    ${config.surfaceCreateInfo}.sType = VK_STRUCTURE_TYPE_ANDROID_SURFACE_CREATE_INFO_KHR;
    ${config.surfaceCreateInfo}.pNext = nullptr;
    ${config.surfaceCreateInfo}.flags = 0;
    ${config.surfaceCreateInfo}.window = AndroidGetApplicationWindow();
    ${config.result} = info.fpCreateAndroidSurfaceKHR(${config.instance}, &${config.surfaceCreateInfo},
            nullptr, &${config.surface});
#else  // !__ANDROID__ && !_WIN32
    VkXcbSurfaceCreateInfoKHR ${config.surfaceCreateInfo} = {};
    ${config.surfaceCreateInfo}.sType = VK_STRUCTURE_TYPE_XCB_SURFACE_CREATE_INFO_KHR;
    ${config.surfaceCreateInfo}.pNext = NULL;
    ${config.surfaceCreateInfo}.connection = config->connection;
    ${config.surfaceCreateInfo}.window = config->window;
    ${config.result} = vkCreateXcbSurfaceKHR(${config.instance}, &${config.surfaceCreateInfo},
            NULL, &${config.surface});
#endif // _WIN32

    assert(${config.result} == VK_SUCCESS);

    </#if>