package tool.fsm.vulkan.initializers;

import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.configs.vulkan.VulkanGlobalState;

import java.util.HashMap;

/**
 * Created by constantinos on 16/05/2016.
 */
public class ComputeCodeGeneratorsInitializer extends CodeGeneratorsInitializer {
    public ComputeCodeGeneratorsInitializer(VulkanGlobalState globalState) {
        super(globalState);
    }

    @Override
    public HashMap<String, VulkanCodeGenerator> initializeCodeGenerators() {
        return null;
    }
}
