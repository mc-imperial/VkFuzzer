package tool.codegen.coverage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by constantinos on 31/03/2016.
 */
public class CoverageRandomizerTest {
    private CoverageRandomizer randomizer;

    @Before
    public void setup() {
        randomizer = new CoverageRandomizer();
    }

    @Test
    public void doesNotReturnANullCoverageObject() {
        Assert.assertNotNull(randomizer.randomCoverage());
    }
}
