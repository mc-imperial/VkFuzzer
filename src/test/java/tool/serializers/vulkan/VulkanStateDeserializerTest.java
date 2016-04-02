package tool.serializers.vulkan;

import org.junit.Before;
import org.junit.Test;
import tool.serialization.vulkan.VulkanStateDeserializer;

/**
 * Created by constantinos on 01/04/2016.
 */
public class VulkanStateDeserializerTest {
    private VulkanStateDeserializer deserializer;

    @Before
    public void setup() {
        deserializer = new VulkanStateDeserializer();
    }

    @Test
    public void correctlyDeserializesState() {

    }
}
