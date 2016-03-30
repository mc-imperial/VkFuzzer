package tool.fsm.vulkan;

import tool.codegen.coverage.CoverageRandomizer;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.instance.VkApplicationInfoGenerator;
import tool.codegen.vulkan.instance.VkCreateInstanceGenerator;
import tool.codegen.vulkan.instance.VkInstanceCreateGenerator;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.HashMap;

/**
 * Created by constantinos on 30/03/2016.
 * Initializes code generators
 */
public class CodeGeneratorsInitializer {
    private final RandomNumberGanerator randomNumberGanerator;
    private final RandomStringGenerator randomStringGenerator;
    private final FreshMap freshMap;
    private final VulkanGlobalState globalState;
    private final HashMap<String, VulkanCodeGenerator> generators;

    public CodeGeneratorsInitializer(final VulkanGlobalState globalState) {
        randomNumberGanerator = new RandomNumberGanerator();
        randomStringGenerator = new RandomStringGenerator();
        freshMap = new FreshMap();
        generators = new HashMap<>();
        this.globalState = globalState;
    }

    // Initializes the generator hashmap
    public HashMap<String, VulkanCodeGenerator> initializeCodeGenerators() {
        CoverageRandomizer randomizer = new CoverageRandomizer();

        // Now populate the hashmap
        generators.put(
                VulkanState.VK_APPLICATION_INFO.toString(),
                new VkApplicationInfoGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_INSTANCE_CREATE_INFO.toString(),
                new VkInstanceCreateGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        generators.put(
                VulkanState.VK_CREATE_INSTANCE.toString(),
                new VkCreateInstanceGenerator(
                        randomStringGenerator,
                        randomNumberGanerator,
                        freshMap,
                        randomizer.randomCoverage(),
                        globalState));

        return generators;
    }
}
