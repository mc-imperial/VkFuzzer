package tool.fuzzer.vulkan;

import tool.Main;
import tool.fuzzer.DependencyGenerator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by cvryo on 05/05/2016.
 */
public class VulkanDependencyGenerator implements DependencyGenerator {
    private final String DEPENDENCIES_FOLDER = "dependencies/vulkan/windows";
    private final String[] SOURCES =
    {
            "AppWindow.cpp",
            "AppWindow.hpp",
            "ExitCondition.cpp",
            "ExitCondition.hpp",
            "Main.cpp",
            "stdafx.cpp",
            "WindowConfig.cpp"
    };

    @Override
    public void generateDependencies(String outputFolder) {
        try {
            ClassLoader loader = Main.class.getClassLoader();

            for (String file : SOURCES) {
                String resourcePath = DEPENDENCIES_FOLDER + "/" + file;
                InputStream fileStream = loader.getResourceAsStream(resourcePath);
                File sourceFile = new File(outputFolder + "/platform/windows/" + file);
                sourceFile.createNewFile();
                Files.copy(fileStream, sourceFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
