package tool.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by constantinos on 25/03/2016.
 */
public class RandomStringGeneratorTest {
    private RandomStringGenerator randomStringGenerator;

    @Before
    public void setup() {
        randomStringGenerator = new RandomStringGenerator();
    }

    @Test
    public void generatesAStringWithTheGivenLength() {
        int length = 456;
        String randomString = randomStringGenerator.generateRandomString(length);
        Assert.assertEquals(randomString.length(), length);
    }

    @Test
    public void generatesAStringWithTheDefaultLength() {
        String randomString = randomStringGenerator.generateRandomString();
        Assert.assertEquals(randomString.length(), 80);
    }
}
