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
public class VulkanCMakeGeneratorTest {
    private VulkanCMakeGenerator cMakeGenerator;

    @Before
    public void setup() {
        cMakeGenerator = new VulkanCMakeGenerator();
    }

    @Test
    public void successfullyGeneratesCMakeFile() {
        try {
            TemporaryFolder temporaryFolder = new TemporaryFolder();
            temporaryFolder.create();
            File tmp = temporaryFolder.newFolder("test");

            cMakeGenerator.generateCMakeFile(1, tmp.getPath());

            temporaryFolder.newFile("test/CMakeLists.txt");

            Assert.fail();
        } catch (IOException exception) {
            // Expected exception
        }
    }
}
