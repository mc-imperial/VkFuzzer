package tool.codegen.vulkan.swapchain;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.commandbuffers.VkCreateCommandPoolConfig;
import tool.configs.vulkan.instance.VkCreateInstanceConfig;
import tool.configs.vulkan.swapchain.VkCreateSurfaceKHRConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by Constantinos on 04/05/2016.
 */
public class VkCreateSurfaceKHRGenerator extends VulkanCodeGenerator {
    private final String SURFACE = "surface";
    private final String CLASS_NAME = "className";
    private final String WINDOW_CLASS = "windowClass";
    private final String SURFACE_CREATE_INFO = "surfaceCreateInfo";
    private final String INSTANCE = "instance";

    public VkCreateSurfaceKHRGenerator(RandomStringGenerator randomStringGenerator,
                                       RandomNumberGanerator randomNumberGanerator,
                                       FreshMap freshMap,
                                       Coverage coverage,
                                       VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.VK_CREATE_SURFACE_KHR, globalState);
    }

    @Override
    public Config generateConfig() {
        VkCreateSurfaceKHRConfig config = new VkCreateSurfaceKHRConfig();

        config.setId(generateConfigId());
        config.setClassName(CLASS_NAME + freshMap.getFreshId(CLASS_NAME));
        config.setWindowClass(WINDOW_CLASS + freshMap.getFreshId(WINDOW_CLASS));
        config.setSurfaceCreateInfo(SURFACE_CREATE_INFO +
                freshMap.getFreshId(SURFACE_CREATE_INFO));
        config.setSurface(SURFACE + freshMap.getFreshId(SURFACE));
        config.setResult(RESULT + freshMap.getFreshId(RESULT));

        // random instance
        ArrayList<Config> configs =
                globalState.getConfig(VulkanState.VK_CREATE_INSTANCE);

        VkCreateInstanceConfig instance =
                (VkCreateInstanceConfig)
                configs.get(randomNumberGanerator.randomNumber(configs.size()));

        config.setInstance(instance.getInstanceName());
        config.setBad(instance.isBad());
        config.addDependency(instance.getId());

        globalState.addConfig(VulkanState.VK_CREATE_SURFACE_KHR, config);
        return config;
    }
}
