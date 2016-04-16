package tool.serializeration.vulkan;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tool.configs.GlobalState;
import tool.configs.vulkan.VulkanGlobalState;
import tool.serialization.vulkan.VulkanStateDeserializer;
import tool.utils.TemplateEngine;
import tool.utils.TemplateEngineTest;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by constantinos on 01/04/2016.
 */
public class VulkanStateDeserializerTest {
    private String correctDummyMetaFile = "tests/DummyMetaFile.meta";
    private VulkanStateDeserializer deserializer;

    @Before
    public void setup() {
        deserializer = new VulkanStateDeserializer();
    }

    @Test
    public void correctlyDeserializesVulkanGlobalState() {
        URL path = TemplateEngineTest.class.getClassLoader()
                .getResource(correctDummyMetaFile);
        File file = new File(path.getPath());

        GlobalState globalState = deserializer.deserializeConfigs(file.getPath());
        Assert.assertFalse(globalState.getConfigs().isEmpty());
    }

    @Test
    public void correctlyDeserializesVisitedState() {
        URL path = TemplateEngineTest.class.getClassLoader()
                .getResource(correctDummyMetaFile);
        File file = new File(path.getPath());

        ArrayList<String> visitedStates =
                deserializer.deserializeVisitedStates(file.getPath());
        Assert.assertFalse(visitedStates.isEmpty());
    }

    @Test
    public void doesNotDeserializeConfigsWhenANullMetaFileIsSupplied() {
        GlobalState globalState = deserializer.deserializeConfigs(null);
        Assert.assertTrue(globalState.getConfigs().isEmpty());
    }

    @Test
    public void doesNotDeserializeVisitedStatesWhenANullMetaFileIsSupplied() {
        ArrayList<String> visitedStates = deserializer.deserializeVisitedStates(null);
        Assert.assertTrue(visitedStates.isEmpty());
    }
}
