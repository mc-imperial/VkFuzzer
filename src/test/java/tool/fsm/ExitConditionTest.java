package tool.fsm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tool.fsm.ExitCondition;

/**
 * Created by constantinos on 29/03/2016.
 */
public class ExitConditionTest {
    private ExitCondition exitCondition;

    @Before
    public void setup() {
        exitCondition = new ExitCondition();
    }

    @Test
    public void shouldBeInitializedToFalse() {
        Assert.assertFalse(exitCondition.shouldExit());
    }

    @Test
    public void shouldReportAnExitAfterSignalExitIsCalled() {
        exitCondition.signalExit();
        Assert.assertTrue(exitCondition.shouldExit());
    }
}
