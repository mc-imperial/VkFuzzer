package tool.fuzzer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/**
 * Created by cvryo on 16/04/2016.
 */
public class LibraryDependencyGeneratorTest {
    private LibraryDependencyGenerator dependencyGenerator;

    @Before
    public void setup() {
        dependencyGenerator = new LibraryDependencyGenerator();
    }

    @Test
    public void successfullyExtractsTheLibraryDependency() {
        try {
            TemporaryFolder temporaryFolder = new TemporaryFolder();
            temporaryFolder.create();
            File tmp = temporaryFolder.newFolder("test");

            dependencyGenerator.generateLibraryDependencies(tmp.getPath());

            temporaryFolder.newFile("test/SDL2-2.0.4");

            Assert.fail();
        } catch (IOException exception) {
            // Expected exception
        }
    }
}
