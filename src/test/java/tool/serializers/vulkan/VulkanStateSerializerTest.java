package tool.serializers.vulkan;

import org.junit.Before;
import org.junit.Test;
import tool.serialization.vulkan.VulkanStateSerializer;

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
    public void correctlySerializesState() {

    }
}
