package tool.codegen.vulkan.instance;

import tool.codegen.coverage.Coverage;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.instance.VkApplicationInfoConfig;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

/**
 * Created by constantinos on 27/03/2016.
 */
public class VkApplicationInfoGenerator extends VulkanCodeGenerator {
    private final String VAR_NAME = "applicationInfo";
    private final String NEXT = "NULL";
    private final String TYPE = "VK_STRUCTURE_TYPE_APPLICATION_INFO";
    private final String API_VERSION = "VK_API_VERSION";

    public VkApplicationInfoGenerator(final RandomStringGenerator randomStringGenerator,
                                      final RandomNumberGanerator randomNumberGanerator,
                                      final FreshMap freshMap,
                                      final Coverage coverage,
                                      final VulkanGlobalState globalState) {
        super(randomStringGenerator, randomNumberGanerator, freshMap, coverage,
                VulkanTemplates.VK_APPLICATION_INFO, globalState);
    }

    @Override
    public Config generateConfig() {
        VkApplicationInfoConfig config = new VkApplicationInfoConfig();
        boolean isBad = coverage.createBadConfig();

        config.setId(generateConfigId());
        config.setNext(NEXT);
        config.setType(TYPE);
        config.setVariableName(VAR_NAME + freshMap.getFreshId(VAR_NAME));
        config.setApplicationName(randomStringGenerator.generateRandomString());
        config.setApplicationVersion(randomNumberGanerator.randomNumber());
        config.setEngineName(randomStringGenerator.generateRandomString());
        config.setEngineVersion(randomNumberGanerator.randomNumber());

        if (isBad) {
            // Request a random vulkan version
            int major = randomNumberGanerator.randomNumber();
            int minor = randomNumberGanerator.randomNumber();
            int patch = randomNumberGanerator.randomNumber();

            config.setApiVersion("VK_MAKE_VERSION(" + major + ", " + minor
                    + ", " + patch + ")");
            config.setBad(true);
        } else {
            // Request latest version
            config.setApiVersion(API_VERSION);
        }

        globalState.addConfig(VulkanState.VK_APPLICATION_INFO, config);

        return config;
    }
}
