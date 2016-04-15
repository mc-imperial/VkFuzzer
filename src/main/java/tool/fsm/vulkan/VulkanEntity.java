package tool.fsm.vulkan;

import org.statefulj.persistence.annotations.State;
import tool.Main;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.vulkan.MainConfig;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.Entity;
import tool.fsm.vulkan.initializers.CodeGeneratorsInitializer;
import tool.fsm.vulkan.states.VulkanState;
import tool.serialization.vulkan.VulkanStateSerializer;
import tool.utils.TemplateEngine;

import java.io.*;
import java.util.ArrayList;
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
    private ArrayList<VulkanState> visitedStates;

    public VulkanEntity() {
        reset();
    }

    public VulkanEntity(VulkanGlobalState globalState,
                        ArrayList<VulkanState> visitedStates) {
        reset();
        this.globalState = globalState;
        this.visitedStates = visitedStates;
    }

    // Generates code if it has not reached the stop state
    @Override
    public boolean generateStateCode() {
        visitedStates.add(VulkanState.valueOf(state));

        if (!state.equals(VulkanState.STOP.toString())) {
            VulkanCodeGenerator generator = codeGenerators.get(state);
            templateEngine.generateCode(generator.getTemplateName(),
                    generator.generateConfig(), writer);

            return true;
        }

        return false;
    }

    @Override
    public void generateStateCode(final String state, final Config config) {
        templateEngine.generateCode(codeGenerators.get(state).getTemplateName(),
                config, writer);
    }

    // Saves the generated program to the specified path
    @Override
    public void saveGeneratedProgram(final String output) {
        try {
            File file = new File(output);
            FileWriter fileWriter = new FileWriter(file);

            MainConfig config = new MainConfig();
            config.setBody(writer.toString());
            templateEngine.generateCode(VulkanTemplates.MAIN, config, fileWriter);

            fileWriter.close();

            // Get file name
            String filename = file.getName();
            if (filename.indexOf(".") > 0) {
                filename = filename.substring(0, filename.lastIndexOf("."));
            }

            saveMetaData(file.getParent(), filename);
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

        templateEngine = new TemplateEngine(VulkanTemplates.TEMPLATE_FOLDER,
                Main.class);

        visitedStates = new ArrayList<>();
    }

    // Saves metadata to file so that the program can be minimised
    private void saveMetaData(final String basePath, final String fileName) {
        try {
            FileWriter writer = new FileWriter(new File(basePath + "/" + fileName + ".meta"));
            VulkanStateSerializer serializer = new VulkanStateSerializer();

            // Serialize visited states order
            serializer.serializeVisitedStates(visitedStates, writer);

            writer.write("\n");

            // Serialize configs
            serializer.serializeState(globalState, writer);

            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
