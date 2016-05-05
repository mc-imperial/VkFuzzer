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
    private final String PLATFORM_INDEPENDENT_DEPENDENCIES_FOLDER = "dependencies/vulkan";
    private final String[] WINDOWS_PLATFORM_SOURCES =
    {
            "windows/AppWindow.cpp",
            "windows/AppWindow.hpp",
            "windows/ExitCondition.cpp",
            "windows/ExitCondition.hpp",
            "windows/Main.cpp",
            "windows/stdafx.h",
            "windows/WindowConfig.hpp"
    };
    private final String[] PLATFORM_INDEPENDENT_HEADERS =
    {
            "Fuzzer.hpp",
            "FuzzerData.hpp"
    };

    @Override
    public void generateDependencies(String outputFolder) {
        copyFiles(outputFolder + "/platform/windows", WINDOWS_PLATFORM_SOURCES);
        copyFiles(outputFolder + "/include", PLATFORM_INDEPENDENT_HEADERS);
    }

    private void copyFiles(String outputFolder, String[] sources) {
        try {
            ClassLoader loader = Main.class.getClassLoader();

            for (String file : sources) {
                //Read file from resources
                String resourcePath = PLATFORM_INDEPENDENT_DEPENDENCIES_FOLDER + "/" + file;
                InputStream fileStream = loader.getResourceAsStream(resourcePath);

                // Make the file
                File sourceFile = new File(outputFolder + "/" + file);
                sourceFile.getParentFile().mkdirs();

                // Copy
                Files.copy(fileStream, sourceFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
