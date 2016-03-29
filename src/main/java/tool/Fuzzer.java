package tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.instance.VkApplicationInfoConfig;
import tool.configs.vulkan.instance.VkCreateInstanceConfig;
import tool.configs.vulkan.instance.VkInstanceCreateInfoConfig;
import tool.fuzzer.ProgramGenerator;
import tool.serialization.vulkan.PolymorphicVulkanConfigMixIn;
import tool.utils.cmdline.CmdLineArgsParser;
import tool.utils.cmdline.CmdLineOptions;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by constantinos on 25/03/2016.
 */
public class Fuzzer {
    public static void main(String[] args) {
        CmdLineArgsParser cmdLineArgsParser = new CmdLineArgsParser();
        CmdLineOptions options = null;

        try {
            options = cmdLineArgsParser.parseArguments(args);
        } catch (RuntimeException exception) {
            cmdLineArgsParser.printHelp();
            System.exit(1);
        }

        ProgramGenerator generator = new ProgramGenerator(options.getLibrary());
        generator.generatePrograms(options.getSamples());
    }
}
