package tool.fuzzer.vulkan;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/**
 * Created by cvryo on 16/04/2016.
 */
public class VulkanTestRunnerGeneratorTest {
    private VulkanTestRunnerGenerator testRunnerGenerator;

    @Before
    public void setup() {
        testRunnerGenerator = new VulkanTestRunnerGenerator();
    }

    @Test
    public void successfullyGeneratesTheTestRunner() {
        try {
            TemporaryFolder temporaryFolder = new TemporaryFolder();
            temporaryFolder.create();
            File tmp = temporaryFolder.newFolder("test");

            testRunnerGenerator.generateTestRunner(tmp.getPath());

            temporaryFolder.newFile("test/TestRunner.py");

            Assert.fail();
        } catch (IOException exception) {
            // Expected exception
        }
    }
}
