package tool.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by constantinos on 25/03/2016.
 */
public class FreshMapTest {
    private FreshMap freshMap;

    @Before
    public void setup() {
        freshMap = new FreshMap();
    }

    @Test
    public void returnsZeroAsInitialId() {
        Assert.assertEquals(freshMap.getFreshId("test"), 0);
    }

    @Test
    public void doesNotReturnTheSameIdOnSuccessiveCallsWithTheSameVariableName() {
        String variableName = "test";
        int a = freshMap.getFreshId(variableName);
        int b = freshMap.getFreshId(variableName);
        Assert.assertNotEquals(a, b);
    }
}
