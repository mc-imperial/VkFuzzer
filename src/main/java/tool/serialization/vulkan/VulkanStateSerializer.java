package tool.serialization.vulkan;

import com.fasterxml.jackson.databind.ObjectMapper;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.serialization.StateSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Base64;

/**
 * Created by constantinos on 31/03/2016.
 * Serializes a collection of config primitives to a Base64 string
 */
public class VulkanStateSerializer implements StateSerializer {
    private final ObjectMapper objectMapper;

    public VulkanStateSerializer() {
        objectMapper = new ObjectMapper();
    }

    // Serializes the state into Base64
    public void serializeState(final VulkanGlobalState globalState,
                               final Writer writer) {
        StringWriter stringWriter = new StringWriter();

        try {
            objectMapper.addMixIn(Config.class, PolymorphicVulkanConfigMixIn.class);
            objectMapper.writeValue(stringWriter, globalState.getConfigs());

            writer.append("\n//States #");
            writer.append(Base64.getEncoder().encodeToString(
                    stringWriter.toString().getBytes()));
//            Pattern p = Pattern.compile("()States \\/\\/()");
//            Matcher m = p.matcher("blablaStates //helloooooo");
//            boolean b = m.matches();
//            System.out.println(writer.toString());
//            System.out.println(writer.toString());
//            HashMap<VulkanState, ArrayList<Config>> deserialized =
//                    objectMapper.readValue(writer.toString(),
//                            new HashMap<VulkanState, ArrayList<Config>>().getClass());
//            System.out.println(deserialized);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
