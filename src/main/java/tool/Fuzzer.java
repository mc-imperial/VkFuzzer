package tool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(Fuzzer.class.getClass(), "/templates/vulkan");
        cfg.setDefaultEncoding("UTF-8");

        try {
            Template temp1 = cfg.getTemplate("enumeration/VkEnumerateInstanceLayerProperties.ftl");
            Template temp2 = cfg.getTemplate("Main.ftl");

            Map<String, Object> root = new HashMap<>();
            StringWriter writer = new StringWriter();
            temp1.process(root, writer);

            root.put("body", writer.toString());

            StringWriter writer2 = new StringWriter();
            temp2.process(root, writer2);
            System.out.println(writer2.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }


//        CmdLineArgsParser cmdLineArgsParser = new CmdLineArgsParser();
//        CmdLineOptions options = null;
//
//        try {
//            options = cmdLineArgsParser.parseArguments(args);
//        } catch (RuntimeException exception) {
//            cmdLineArgsParser.printHelp();
//            System.exit(1);
//        }
    }
}
