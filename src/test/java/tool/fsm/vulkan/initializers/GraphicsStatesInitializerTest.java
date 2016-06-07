package tool.fsm.vulkan.initializers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tool.fsm.ExitCondition;

/**
 * Created by constantinos on 31/03/2016.
 */
public class GraphicsStatesInitializerTest {
    private GraphicsStatesInitializer initializer;

    @Before
    public void setup() {
        initializer = new GraphicsStatesInitializer(new ExitCondition());
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
