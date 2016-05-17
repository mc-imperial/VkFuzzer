package tool.codegen.vulkan.swapchain;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.device.SelectDeviceConfig;
import tool.configs.vulkan.swapchain.InitSwapchainConfig;
import tool.configs.vulkan.swapchain.VkCreateSurfaceKHRConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by Constantinos on 04/05/2016.
 */
public class InitSwapchainGenerator extends VulkanCodeGenerator {
    private final String FORMAT_COUNT = "formatCount";
    private final String SURFACE = "surface";
    private final String SURFACE_FORMAT = "surfaceFormats";
    private final String FORMAT = "format";
    private final String SURF_CAPABILITIES = "surfCapabilities";
    private final String PRESENT_MODE_COUNT = "presentModeCount";
    private final String PRESENT_MODES = "presentModes";
    private final String SWAPCHAIN_EXTENT = "swapchainExtent";
    private final String SWAPCHAIN_PRESENT_MODE = "swapchainPresentMode";
    private final String DESIRED_NUMBER_OF_SWAPCHAIN_IMAGES = "desiredNumberOfSwapChainImages";
    private final String PRE_TRANSFORM = "preTransform";
    private final String SWAPCHAIN_CREATE_INFO = "swapchainCreateInfo";
    private final String SWAPCHAIN = "swapchain";
    private final String SWAPCHAIN_IMAGE_COUNT = "swapchainImageCount";
    private final String SWAPCHAIN_IMAGES = "swapchainImages";
    private final String SWAPCHAIN_BUFFESR = "swapchainBuffers";
    private final String DEVICE = "device";

    public InitSwapchainGenerator(RandomStringGenerator randomStringGenerator,
                                  RandomNumberGanerator randomNumberGanerator,
                                  FreshMap freshMap,
                                  Coverage coverage,
                                  VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap,
                coverage, VulkanTemplates.INIT_SWAPCHAIN, globalState);
    }

    @Override
    public Config generateConfig() {
        InitSwapchainConfig config = new InitSwapchainConfig();

        config.setId(generateConfigId());
        config.setResult(RESULT + freshMap.getFreshId(RESULT));
        config.setFormat(FORMAT + freshMap.getFreshId(FORMAT));
        config.setFormatCount(FORMAT_COUNT + freshMap.getFreshId(FORMAT_COUNT));
        config.setSurface(SURFACE + freshMap.getFreshId(SURFACE));
        config.setSurfaceFormats(SURFACE_FORMAT +
                freshMap.getFreshId(SURFACE_FORMAT));
        config.setSurfCapabilities(SURF_CAPABILITIES +
                freshMap.getFreshId(SURF_CAPABILITIES));
        config.setPresentModeCount(PRESENT_MODE_COUNT +
                freshMap.getFreshId(PRESENT_MODE_COUNT));
        config.setPresentModes(PRESENT_MODES + freshMap.getFreshId(PRESENT_MODES));
        config.setSwapchain(SWAPCHAIN + freshMap.getFreshId(SWAPCHAIN));
        config.setSwapchainExtent(SWAPCHAIN_EXTENT +
                freshMap.getFreshId(SWAPCHAIN_EXTENT));
        config.setSwapchainPresentMode(SWAPCHAIN_PRESENT_MODE +
                freshMap.getFreshId(SWAPCHAIN_PRESENT_MODE));
        config.setDesiredNumberOfSwapChainImages(DESIRED_NUMBER_OF_SWAPCHAIN_IMAGES +
                freshMap.getFreshId(DESIRED_NUMBER_OF_SWAPCHAIN_IMAGES));
        config.setPreTransform(PRE_TRANSFORM + freshMap.getFreshId(PRE_TRANSFORM));
        config.setSwapchainCreateInfo(SWAPCHAIN_CREATE_INFO +
                freshMap.getFreshId(SWAPCHAIN_CREATE_INFO));
        config.setSwapchainImageCount(SWAPCHAIN_IMAGE_COUNT +
                freshMap.getFreshId(SWAPCHAIN_IMAGE_COUNT));
        config.setSwapchainImages(SWAPCHAIN_IMAGES +
                freshMap.getFreshId(SWAPCHAIN_IMAGES));
        config.setSwapchainBuffers(SWAPCHAIN_BUFFESR +
                freshMap.getFreshId(SWAPCHAIN_BUFFESR));
        config.setDevice(DEVICE + freshMap.getFreshId(DEVICE));

        // Get surface
        ArrayList<Config> surfaces =
                globalState.getConfig(VulkanState.VK_CREATE_SURFACE_KHR);

        VkCreateSurfaceKHRConfig surface = (VkCreateSurfaceKHRConfig)surfaces.get(0);
        config.setSurface(surface.getSurface());
        config.addDependency(surface.getId());
        config.setBad(surface.isBad());

        // Get surface
        ArrayList<Config> selectedDevices =
                globalState.getConfig(VulkanState.SELECT_DEVICE);
        SelectDeviceConfig device = (SelectDeviceConfig)selectedDevices.get(
                randomNumberGanerator.randomNumber(selectedDevices.size()));

        config.setDevice(device.getDevice());
        config.setBad(device.isBad());

        globalState.addConfig(VulkanState.INIT_SWAPCHAIN, config);

        return config;
    }
}
