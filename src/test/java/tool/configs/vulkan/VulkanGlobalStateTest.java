package tool.configs.vulkan;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tool.configs.Config;
import tool.fsm.vulkan.states.VulkanState;

import java.util.ArrayList;

/**
 * Created by constantinos on 01/04/2016.
 */
public class VulkanGlobalStateTest {
    private VulkanGlobalState globalState;

    @Before
    public void setup() {
        globalState = new VulkanGlobalState();
    }

    @Test
    public void returnsEmptyArrayIfNoElementsHaveBeenInserted() {
        Assert.assertTrue(globalState.getConfig(VulkanState.VK_APPLICATION_INFO).isEmpty());
    }

    @Test
    public void returnsArrayWithTheInsertedElements() {
        Config config = new Config();
        globalState.addConfig(VulkanState.VK_APPLICATION_INFO, config);
        ArrayList<Config> configs = globalState.getConfig(VulkanState.VK_APPLICATION_INFO);

        Assert.assertFalse(configs.isEmpty());
        Assert.assertEquals(configs.size(), 1);
        Assert.assertEquals(config, configs.get(0));
    }
}
