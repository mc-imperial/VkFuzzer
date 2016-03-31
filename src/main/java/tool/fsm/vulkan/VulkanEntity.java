package tool.fsm.vulkan;

import org.statefulj.persistence.annotations.State;
import tool.Main;
import tool.codegen.CodeGenerator;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.MainConfig;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.Entity;
import tool.fsm.vulkan.states.VulkanState;
import tool.serialization.vulkan.VulkanStateSerializer;
import tool.utils.TemplateEngine;

import java.io.*;
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

    // Saves the generated program to the specified ppath
    @Override
    public void saveGeneratedProgram(final String output) {
        try {
            TemplateEngine engine = new TemplateEngine(VulkanTemplates.TEMPLATE_FOLDER,
                    Main.class);
            FileWriter fileWriter = new FileWriter(new File(output));
            MainConfig config = new MainConfig();
            config.setBody(writer.toString());
            engine.generateCode(VulkanTemplates.MAIN, config, fileWriter);

            VulkanStateSerializer serializer = new VulkanStateSerializer();
            serializer.serializeState(globalState, fileWriter);

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
