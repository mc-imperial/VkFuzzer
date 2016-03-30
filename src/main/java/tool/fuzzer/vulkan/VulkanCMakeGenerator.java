package tool.fuzzer.vulkan;

import tool.Main;
import tool.codegen.vulkan.VulkanTemplates;
import tool.configs.vulkan.cmake.CMakeConfig;
import tool.configs.vulkan.cmake.ExecutableConfig;
import tool.fuzzer.CMakeGenerator;
import tool.utils.TemplateEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by constantinos on 29/03/2016.
 * Generates the CMake file
 */
public class VulkanCMakeGenerator implements CMakeGenerator {
    private final String TEMPLATE_NAME = "CMakeLists.ftl";
    private final String CMAKE_NAME = "CMakeLists.txt";
    private final TemplateEngine templateEngine;

    public VulkanCMakeGenerator() {
        templateEngine = new TemplateEngine(VulkanTemplates.TEMPLATE_FOLDER,
                Main.class);
    }

    @Override
    public void generateCMakeFile(final int size, final String path) {
        ExecutableConfig[] configs = new ExecutableConfig[size];
        String baseProgramName = "Program";
        String programExtension = ".cpp";

        // Create configs
        for (int i = 0; i < size; ++i) {
            String name = baseProgramName + i;
            String source = name + programExtension;
            ExecutableConfig config = new ExecutableConfig();
            config.setName(name);
            config.setSource(source);
            configs[i] = config;
        }

        CMakeConfig cMakeConfig = new CMakeConfig();
        cMakeConfig.setExecutables(configs);

        try {
            Writer writer = new FileWriter(new File(path + "/" + CMAKE_NAME));
            templateEngine.generateCode(TEMPLATE_NAME, cMakeConfig, writer);
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
