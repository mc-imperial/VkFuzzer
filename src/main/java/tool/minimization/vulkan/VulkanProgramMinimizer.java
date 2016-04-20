package tool.minimization.vulkan;

import tool.configs.Config;
import tool.configs.GlobalState;
import tool.fsm.vulkan.VulkanEntity;
import tool.minimization.Minimizer;
import tool.serialization.vulkan.VulkanStateDeserializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by cvryo on 14/04/2016.
 * The vulkan program minimizer
 */
public class VulkanProgramMinimizer implements Minimizer {
    private final String ERROR_MSG = "Id could not be found. Exiting...";
    private final String MINIMIZED_PROGRAM_SUFFIX = "-min";
    private final String MINIMIZED_PROGRAM_EXTENSION = ".cpp";
    private final String META_FILE_EXTENSION = ".meta";
    private final VulkanStateDeserializer deserializer;

    public VulkanProgramMinimizer() {
        deserializer = new VulkanStateDeserializer();
    }

    // Minimises a given program from its meta file
    @Override
    public void minimizeProgram(String inputMetaFile, int id) {
        // Deserialize metadata
        GlobalState globalState =
                deserializer.deserializeConfigs(inputMetaFile);
        ArrayList<String> visitedStates =
                deserializer.deserializeVisitedStates(inputMetaFile);

        // Find Config
        Config config = null;
        try {
            config = findConfig(globalState, id);
        } catch (NoSuchElementException e) {
            System.err.println(ERROR_MSG);
            System.exit(1);
        }

        // Generate minimized program
        Stack<Config> dependencies = generateDependencies(globalState, config);
        VulkanEntity entity = new VulkanEntity(globalState, visitedStates);

        while (!dependencies.isEmpty()) {
            entity.generateStateCode(visitedStates.get(dependencies.peek().getId()),
                    dependencies.pop());
        }

        // Save file
        File file = new File(inputMetaFile);
        String basePath = file.getParent();
        String fileName = file.getName();
        String minimizedFileName =
                fileName.substring(0, fileName.indexOf(META_FILE_EXTENSION))
                        + MINIMIZED_PROGRAM_SUFFIX;
        String sourceFile = minimizedFileName + MINIMIZED_PROGRAM_EXTENSION;
        String output = basePath + "/" + sourceFile;
        String cmakeFile = basePath + "/CMakeLists.txt";
        entity.saveGeneratedProgram(output);

        // Modify CMake
        modifyCMakeLists(sourceFile, minimizedFileName, cmakeFile);
    }

    // Finds the required config
    private Config findConfig(final GlobalState globalState,
                              final int id) throws NoSuchElementException {
        for (ArrayList<Config> list : globalState.getConfigs().values()) {
            for (Config config : list) {
                if (config.getId() == id) {
                    return config;
                }
            }
        }

        throw new NoSuchElementException("Could not find id " + id);
    }

    // Generates a stack of dependencies
    private Stack<Config> generateDependencies(final GlobalState globalState,
                                               final Config config) {
        Stack<Config> dependencies = new Stack<>();
        LinkedList<Config> queue = new LinkedList<>();

        // Add config to queues
        queue.add(config);
        dependencies.push(config);

        do {
            Config current = queue.remove();

            for (int id : current.getDependencies()) {
                try {
                    Config dependency = findConfig(globalState, id);
                    dependencies.push(dependency);
                    queue.add(dependency);
                } catch (NoSuchElementException e) {
                    System.err.println(e.getMessage());
                }
            }
        } while (!queue.isEmpty());

        return dependencies;
    }

    // Modifies the existing CMake file with the new program
    private void modifyCMakeLists(final String programSource,
                                  final String programName,
                                  final String cmakeFile) {
        String template = "# Create executable\n" +
                "add_executable(" + programName + " " + programSource + ")\n" +
                "# Link executable\n" +
                "if(WIN32)\n" +
                "    target_link_libraries(" + programName + " ${VULKAN_LOADER})\n" +
                "else()\n" +
                "    # TODO:: Add other deps for linux\n" +
                "    target_link_libraries(" + programName + " ${VULKAN_LOADER})\n" +
                "endif()\n\n";

        try {
            FileWriter writer = new FileWriter(new File(cmakeFile), true);
            writer.append("\n");
            writer.append(template);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
