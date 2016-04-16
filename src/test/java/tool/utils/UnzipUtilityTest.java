package tool.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cvryo on 16/04/2016.
 */
public class UnzipUtilityTest {
    private String dummyZipFile = "tests/TestFile.zip";
    private UnzipUtility unzipUtility;

    @Before
    public void setup() {
        unzipUtility = new UnzipUtility();
    }

    @Test
    public void successfullyUnzipsToTheGivenPath() {
        try {
            TemporaryFolder temporaryFolder = new TemporaryFolder();
            temporaryFolder.create();
            File tmp = temporaryFolder.newFolder("test");

            InputStream stream = TemplateEngineTest.class.getClassLoader()
                    .getResourceAsStream(dummyZipFile);

            unzipUtility.unzip(stream, tmp.getPath());

            temporaryFolder.newFile("test/TestFile.txt");

            Assert.fail();
        } catch (IOException exception) {
            // Expected exception
        }
    }
}
