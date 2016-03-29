package tool.fsm.vulkan;

import org.statefulj.persistence.annotations.State;
import tool.codegen.CodeGenerator;
import tool.codegen.vulkan.VulkanCodeGenerator;

import java.util.HashMap;

/**
 * Created by constantinos on 28/03/2016.
 */
public class VulkanEntity {
    private final HashMap<String, VulkanCodeGenerator> codeGenerators;
    @State
    private String state;

    public VulkanEntity() {
        codeGenerators = new HashMap<>();
        initialiseStates();
    }

    public CodeGenerator getCurrentCodeGenerator() {
        return codeGenerators.get(state);
    }

    public String getCurrentState() {
        return state;
    }

    private void initialiseStates() {
//        state = VulkanState.START.toString();
    }
}
