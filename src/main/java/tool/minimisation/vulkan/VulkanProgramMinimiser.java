package tool.minimisation.vulkan;

import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.instance.VkApplicationInfoConfig;
import tool.fsm.vulkan.VulkanEntity;
import tool.fsm.vulkan.states.VulkanState;
import tool.minimisation.Minimiser;
import tool.serialization.vulkan.VulkanStateDeserializer;

import java.io.File;
import java.util.*;

/**
 * Created by cvryo on 14/04/2016.
 */
public class VulkanProgramMinimiser implements Minimiser {
    private final String MINIMIZED_PROGRAM_SUFFIX = "-min.cpp";
    private final String META_FILE_EXTENSION = ".meta";
    private final VulkanStateDeserializer deserializer;

    public VulkanProgramMinimiser() {
        deserializer = new VulkanStateDeserializer();
    }

    @Override
    public void minimizeProgram(String inputMetaFile, int id) {
        // Deserialize metadata
        VulkanGlobalState globalState =
                deserializer.deserializeConfigs(inputMetaFile);
        ArrayList<VulkanState> visitedStates =
                deserializer.deserializeVisitedStates(inputMetaFile);

        // Find Config
        Config config = null;
        try {
            config = findConfig(globalState, id);
        } catch (NoSuchElementException e) {
            System.err.println("Id could not be found. Exiting...");
            System.exit(1);
        }

        // Generate minimized program
        Stack<Config> dependencies = generateDependencies(globalState, config);
        VulkanEntity entity = new VulkanEntity(globalState, visitedStates);

        while (!dependencies.isEmpty()) {
            entity.generateStateCode(visitedStates.get(dependencies.peek().getId()).toString(),
                    dependencies.pop());
        }

        // Save file
        File file = new File(inputMetaFile);
        String basePath = file.getParent();
        String fileName = file.getName();
        String minimizedFileName =
                fileName.substring(0, fileName.indexOf(META_FILE_EXTENSION))
                + MINIMIZED_PROGRAM_SUFFIX;
        String output = basePath + "/" + minimizedFileName;

        entity.saveGeneratedProgram(output);
    }

    // Finds the required config
    private Config findConfig(final VulkanGlobalState globalState,
                              final int id) throws NoSuchElementException {
        for (ArrayList<Config> list : globalState.getConfigs().values()) {
            for (Config config : list) {
                if (config.getId() == id) {
                    return config;
                }
            }
        }

        throw new NoSuchElementException();
    }

    // Generates a stack of dependencies
    private Stack<Config> generateDependencies(final VulkanGlobalState globalState,
                                               final Config config) {
        Stack<Config> dependencies = new Stack<>();
        Queue<Config> queue = new PriorityQueue<>();

        // Add config to queues
        queue.add(config);
        dependencies.push(config);

        do {
            Config current = queue.remove();

            for (int id : current.getDependencies()) {
                Config dependency = findConfig(globalState, id);
                dependencies.push(dependency);
                queue.add(dependency);
            }
        } while (!queue.isEmpty());

        return dependencies;
    }
}
