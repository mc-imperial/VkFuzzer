package tool.fsm.vulkan.initializers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.vulkan.states.VulkanState;

/**
 * Created by constantinos on 31/03/2016.
 */
public class CodeGeneratorsInitializerTest {
    private CodeGeneratorsInitializer initializer;

    @Before
    public void setup() {
        initializer = new CodeGeneratorsInitializer(new VulkanGlobalState());
    }

    @Test
    public void doesNotReturnNullMap() {
        Assert.assertNotNull(initializer.initializeCodeGenerators());
    }

    @Test
    public void doesNotReturnEmptyMap() {
        Assert.assertFalse(initializer.initializeCodeGenerators().isEmpty());
    }

    @Test
    public void initializesAllAvailableCodeGenerators() {
        VulkanState[] states = VulkanState.values();

        // Subtract 2 because the START and STOP states do not have code generators
        Assert.assertEquals(initializer.initializeCodeGenerators().size(),
                states.length - 2);
    }
}
