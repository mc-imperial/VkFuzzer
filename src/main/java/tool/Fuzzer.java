package tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.configs.vulkan.instance.VkApplicationInfoConfig;
import tool.configs.vulkan.instance.VkInstanceCreateInfoConfig;
import tool.fuzzer.ProgramGenerator;
import tool.serialization.vulkan.PolymorphicVulkanConfigMixIn;
import tool.utils.cmdline.CmdLineArgsParser;
import tool.utils.cmdline.CmdLineOptions;

import java.io.*;

/**
 * Created by constantinos on 25/03/2016.
 */
public class Fuzzer {
    public static void main(String[] args) {

//        VkInstanceCreateInfoConfig instanceCreateInfoConfig = new VkInstanceCreateInfoConfig();
//        VkApplicationInfoConfig applicationInfoConfig = new VkApplicationInfoConfig();
//        instanceCreateInfoConfig.setApplicationInfo("hello");
//        applicationInfoConfig.setApiVersion("hi");
//
//        VulkanGlobalState configs = new VulkanGlobalState();
//        configs.addConfig(instanceCreateInfoConfig);
//        configs.addConfig(applicationInfoConfig);
//
//        Config[] collection = new Config[2];
//        collection[0] = instanceCreateInfoConfig;
//        collection[1] = applicationInfoConfig;
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.addMixIn(Config.class, PolymorphicVulkanConfigMixIn.class);
//
//        StringWriter writer = new StringWriter();
//
//        try {
//            mapper.writeValue(writer, configs);
//            System.out.println(writer.toString());
//            VulkanGlobalState deserialized = mapper.readValue(writer.toString(), VulkanGlobalState.class);
//            for (Config c : deserialized.getConfigs()) {
//                System.out.println(c);
//            }
//            Config[] deserialized =
//            for (Config c : deserialized) {
//                if (c instanceof  VkApplicationInfoConfig) {
//                    System.out.println(((VkApplicationInfoConfig) c).getApiVersion());
//                } else if (c instanceof  VkInstanceCreateInfoConfig) {
//                    System.out.println(((VkInstanceCreateInfoConfig) c).getApplicationInfo());
//                }
//
//
//
//            }

//            VkApplicationInfoConfig[] stuff =
//                    Arrays.stream(deserialized.getConfigs().toArray()).filter(x -> x instanceof VkApplicationInfoConfig).toArray(VkApplicationInfoConfig[]::new);
//
//            System.out.println(stuff.length);
//
//            for (VkApplicationInfoConfig c : stuff) {
//                System.out.println(c.getApiVersion());
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
//        cfg.setClassForTemplateLoading(Fuzzer.class.getClass(), "/templates/vulkan");
//        cfg.setDefaultEncoding("UTF-8");
//
//        try {
//            Template temp1 = cfg.getTemplate("enumeration/VkEnumerateInstanceLayerProperties.ftl");
//            Template temp2 = cfg.getTemplate("Main.ftl");
//
//            Map<String, Object> root = new HashMap<>();
//            StringWriter writer = new StringWriter();
//            temp1.process(root, writer);
//
//            root.put("body", writer.toString());
//
//            StringWriter writer2 = new StringWriter();
//            temp2.process(root, writer2);
//            System.out.println(writer2.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }


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
