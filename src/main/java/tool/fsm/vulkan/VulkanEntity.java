package tool.fsm.vulkan;

import org.statefulj.persistence.annotations.State;
import tool.codegen.CodeGenerator;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.Entity;
import tool.fsm.vulkan.states.VulkanState;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

/**
 * Created by constantinos on 28/03/2016.
 * A stateful entity that contains state about the Vulkan Program
 */
public class VulkanEntity implements Entity {
    @State
    private String state;
    private Writer writer;
    private VulkanGlobalState globalState;
    private HashMap<String, VulkanCodeGenerator> codeGenerators;

    public VulkanEntity() {
        reset();
    }

    // Returns the Codegenerator used by the FSM action to generate configs
    public CodeGenerator getCurrentCodeGenerator() {
        return codeGenerators.get(state);
    }

    // Returns the writer used by the FSM action to append the generated program
    public Writer getWriter() {
        return  writer;
    }

    // Signals end of FSM
    public boolean didReachStop() {
        return state.equals(VulkanState.STOP.toString());
    }

    // Retuns generated program
    @Override
    public String getGeneratedProgram() {
        return null;
    }

    @Override
    public void reset() {
        globalState = new VulkanGlobalState();

        CodeGeneratorsInitializer initializer =
                new CodeGeneratorsInitializer(globalState);
        codeGenerators = initializer.initializeCodeGenerators();

        writer = new StringWriter();
    }
}
