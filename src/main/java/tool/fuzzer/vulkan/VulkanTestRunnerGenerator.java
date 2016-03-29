package tool.fuzzer.vulkan;

import tool.Main;
import tool.configs.Config;
import tool.fuzzer.TestRunnerGenerator;
import tool.utils.TemplateEngine;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by constantinos on 29/03/2016.
 * Copies the test runner.
 */
public class VulkanTestRunnerGenerator implements TestRunnerGenerator {
    private final String SCRIPT_FOLDER = "/scripts";
    private final String TEMPLATE_NAME = "TestRunner.py";
    private final String RUNNER_NAME = "TestRunner.py";
    private final TemplateEngine templateEngine;

    public VulkanTestRunnerGenerator() {
        templateEngine = new TemplateEngine(SCRIPT_FOLDER, Main.class);
    }

    @Override
    public void generateTestRunner(final String outputFolder) {
        try {
            FileWriter writer = new FileWriter(new File(outputFolder +
                    "/" + RUNNER_NAME));
            templateEngine.generateCode(TEMPLATE_NAME, new Config(), writer);
            writer.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
