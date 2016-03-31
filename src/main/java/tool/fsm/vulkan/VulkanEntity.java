package tool.fsm.vulkan;

import org.statefulj.persistence.annotations.State;
import tool.Main;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.vulkan.MainConfig;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.Entity;
import tool.fsm.vulkan.initializers.CodeGeneratorsInitializer;
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
    private TemplateEngine templateEngine;
    private HashMap<String, VulkanCodeGenerator> codeGenerators;

    public VulkanEntity() {
        reset();
    }

    // Generates code if it has not reached the stop state
    @Override
    public boolean generateStateCode() {
        if (!state.equals(VulkanState.STOP.toString())) {
            VulkanCodeGenerator generator = codeGenerators.get(state);
            templateEngine.generateCode(generator.getTemplateName(),
                    generator.generateConfig(), writer);

            return true;
        }

        return false;
    }

    // Saves the generated program to the specified ppath
    @Override
    public void saveGeneratedProgram(final String output) {
        try {
            FileWriter fileWriter = new FileWriter(new File(output));
            MainConfig config = new MainConfig();
            config.setBody(writer.toString());
            templateEngine.generateCode(VulkanTemplates.MAIN, config, fileWriter);

            VulkanStateSerializer serializer = new VulkanStateSerializer();
            serializer.serializeState(globalState, fileWriter);

            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // Initializes the entity
    private void reset() {
        globalState = new VulkanGlobalState();

        CodeGeneratorsInitializer initializer =
                new CodeGeneratorsInitializer(globalState);
        codeGenerators = initializer.initializeCodeGenerators();

        writer = new StringWriter();

        this.templateEngine = new TemplateEngine(VulkanTemplates.TEMPLATE_FOLDER,
                Main.class);
    }
}
