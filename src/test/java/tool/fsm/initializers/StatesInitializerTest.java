package tool.fsm.initializers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tool.fsm.ExitCondition;
import tool.fsm.vulkan.initializers.StatesInitializer;

/**
 * Created by constantinos on 31/03/2016.
 */
public class StatesInitializerTest {
    private StatesInitializer initializer;

    @Before
    public void setup() {
        initializer = new StatesInitializer(new ExitCondition());
    }

    @Test
    public void returnsANullPersisterIfStatesHaveNotBeenInitialized() {
        Assert.assertNull(initializer.getPersister());
    }

    @Test
    public void doesNotReturnANullPersisterIfStatesHaveBeenInitialized() {
        initializer.initializeStates();
        Assert.assertNotNull(initializer.getPersister());
    }
}
