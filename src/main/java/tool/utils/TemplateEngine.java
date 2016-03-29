package tool.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import tool.configs.Config;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by constantinos on 28/03/2016.
 */
public class TemplateEngine {
    private final Version ENGINE_VERSION = Configuration.VERSION_2_3_23;
    private final String TEMPLATE_ENCODING = "UTF-8";
    private final String CONFIG_NAME = "config";
    private final Configuration engineConfig;
    private final Map<String, Config> root;

    public TemplateEngine(final String templateFolder, final Class classForLoading) {
        engineConfig = new Configuration(ENGINE_VERSION);
        engineConfig.setClassForTemplateLoading(classForLoading, templateFolder);
        engineConfig.setDefaultEncoding(TEMPLATE_ENCODING);
        root = new HashMap<>();
    }

    public void generateCode(final String template, final Config config,
                             final Writer writer) {
        root.put(CONFIG_NAME, config);

        try {
            Template temp = engineConfig.getTemplate(template);
            temp.process(root, writer);
        } catch (IOException e) {
            System.err.println("Could not find template");
            System.err.println(e.getMessage());
        } catch (TemplateException e) {
            System.err.println("Template exception.");
            System.err.println(e.getMessage());
        }
    }
}
