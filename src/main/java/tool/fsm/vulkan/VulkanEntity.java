package tool.fsm.vulkan;

import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.Config;
import tool.configs.GlobalState;
import tool.configs.vulkan.MainConfig;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.Entity;
import tool.fsm.vulkan.initializers.CodeGeneratorsInitializer;
import tool.fsm.vulkan.states.VulkanState;
import tool.serialization.vulkan.VulkanStateSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by constantinos on 28/03/2016.
 * A stateful entity that contains state about the Vulkan Program
 */
public class VulkanEntity extends Entity {
    private HashMap<String, VulkanCodeGenerator> codeGenerators;

    public VulkanEntity() {
        super(VulkanTemplates.TEMPLATE_FOLDER, new VulkanGlobalState());
        initCodeGenerators();
    }

    public VulkanEntity(GlobalState globalState,
                        ArrayList<String> visitedStates) {
        super(VulkanTemplates.TEMPLATE_FOLDER, globalState, visitedStates);
        initCodeGenerators();
    }

    // Generates code if it has not reached the stop state
    @Override
    public boolean generateStateCode() {
        visitedStates.add(state);

        if (!state.equals(VulkanState.STOP.toString())) {
            VulkanCodeGenerator generator = codeGenerators.get(state);
            templateEngine.generateCode(generator.getTemplateName(),
                    generator.generateConfig(), writer);

            return true;
        }

        return false;
    }

    // Generates code according to the given state and config
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

    // Initalizes code generators
    private void initCodeGenerators() {
        CodeGeneratorsInitializer initializer =
                new CodeGeneratorsInitializer((VulkanGlobalState)globalState);
        codeGenerators = initializer.initializeCodeGenerators();
    }
}
