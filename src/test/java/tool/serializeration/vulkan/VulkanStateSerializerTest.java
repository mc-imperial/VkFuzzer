package tool.serializeration.vulkan;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.vulkan.states.VulkanState;
import tool.serialization.vulkan.VulkanStateSerializer;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by constantinos on 01/04/2016.
 */
public class VulkanStateSerializerTest {
    private VulkanStateSerializer serializer;

    @Before
    public void setup() {
        serializer = new VulkanStateSerializer();
    }

    @Test
    public void correctlySerializesVulkanGlobalState() {
        VulkanGlobalState globalState = new VulkanGlobalState();
        globalState.addConfig(VulkanState.START, new Config());

        Writer writer = new StringWriter();
        serializer.serializeState(globalState, writer);

        Assert.assertNotEquals(-1, writer.toString().indexOf(VulkanState.START.toString()));
    }

    @Test
    public void correctlySerializesVisitedStates() {
        String dummyState = "DUMMY_STATE";
        ArrayList<String> states = new ArrayList<>();
        states.add(dummyState);

        Writer writer = new StringWriter();
        serializer.serializeVisitedStates(states, writer);

        Assert.assertNotEquals(-1, writer.toString().indexOf(dummyState));
    }

    @Test
    public void doesNotSerializeANullVisitedStatesArray() {
        Writer writer = new StringWriter();
        serializer.serializeVisitedStates(null, writer);
        Assert.assertEquals(-1, writer.toString().indexOf("null"));
    }

    @Test
    public void doesNotSerializeANullVulkanGlobalState() {
        Writer writer = new StringWriter();
        serializer.serializeState(null, writer);
        Assert.assertEquals(-1, writer.toString().indexOf("null"));
    }
}
