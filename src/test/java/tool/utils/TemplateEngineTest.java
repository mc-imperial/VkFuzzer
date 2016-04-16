package tool.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tool.configs.Config;

import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by constantinos on 29/03/2016.
 */
public class TemplateEngineTest {
    private final String TEMPLATE_FOLDER = "/tests/";
    private TemplateEngine templateEngine;

    @Before
    public void setup() {
        templateEngine = new TemplateEngine(TEMPLATE_FOLDER,
                TemplateEngineTest.class);
    }

    @Test
    public void shouldNotGenerateCodeIfTemplateIsNotFound() {
        String template = "doesNotExist.ftl";
        Writer writer = new StringWriter();
        Config config = new Config();

        templateEngine.generateCode(template, config, writer);
        Assert.assertEquals(writer.toString().length(), 0);
    }

    @Test
    public void shouldGenerateCodeIfTemplateIsFound() {
        String template = "Hello.ftl";
        Writer writer = new StringWriter();
        Config config = new Config();

        templateEngine.generateCode(template, config, writer);
        Assert.assertNotEquals(writer.toString().length(), 0);
    }
}
