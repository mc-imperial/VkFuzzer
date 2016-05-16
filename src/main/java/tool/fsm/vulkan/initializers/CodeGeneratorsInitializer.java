package tool.fsm.vulkan.initializers;

import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.configs.vulkan.VulkanGlobalState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.util.HashMap;

/**
 * Created by constantinos on 16/05/2016.
 */
public abstract class CodeGeneratorsInitializer {
    protected final RandomNumberGanerator randomNumberGanerator;
    protected final RandomStringGenerator randomStringGenerator;
    protected final FreshMap freshMap;
    protected final VulkanGlobalState globalState;
    protected final HashMap<String, VulkanCodeGenerator> generators;

    public CodeGeneratorsInitializer(final VulkanGlobalState globalState) {
        randomNumberGanerator = new RandomNumberGanerator();
        randomStringGenerator = new RandomStringGenerator();
        freshMap = new FreshMap();
        generators = new HashMap<>();
        this.globalState = globalState;
    }

    public abstract HashMap<String, VulkanCodeGenerator> initializeCodeGenerators();
}
