package tool.fsm.vulkan;

import org.statefulj.persistence.annotations.State;
import tool.codegen.CodeGenerator;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.FreshMap;
import tool.utils.RandomNumberGanerator;
import tool.utils.RandomStringGenerator;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

/**
 * Created by constantinos on 28/03/2016.
 */
public class VulkanEntity {
    @State
    private String state;
    private final HashMap<String, VulkanCodeGenerator> codeGenerators;
    private final RandomNumberGanerator randomNumberGanerator;
    private final RandomStringGenerator randomStringGenerator;
    private final FreshMap freshMap;
    private final VulkanGlobalState globalState;
    private final Writer writer;

    public VulkanEntity() {
        codeGenerators = new HashMap<>();
        randomNumberGanerator = new RandomNumberGanerator();
        randomStringGenerator = new RandomStringGenerator();
        freshMap = new FreshMap();
        globalState = new VulkanGlobalState();
        writer = new StringWriter();
    }

    public CodeGenerator getCurrentCodeGenerator() {
        return codeGenerators.get(state);
    }

    public Writer getWriter() {
        return  writer;
    }

    public boolean didReachStop() {
        return state.equals(VulkanState.STOP.toString());
    }
}
