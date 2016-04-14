package tool.serialization.vulkan;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import tool.configs.Config;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.vulkan.states.VulkanState;
import tool.serialization.StateSerializer;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by constantinos on 31/03/2016.
 * Serializes a collection of config primitives to a Base64 string
 */
public class VulkanStateSerializer implements StateSerializer {
    private final ObjectMapper objectMapper;

    public VulkanStateSerializer() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
    }

    // Serializes the state into Base64
    public void serializeState(final VulkanGlobalState globalState,
                               final Writer writer) {
        try {
            objectMapper.addMixIn(Config.class, PolymorphicVulkanConfigMixIn.class);
            writer.append("\n//States #");
            objectMapper.writeValue(writer, globalState.getConfigs());
//            writer.append(Base64.getEncoder().encodeToString(
//                    stringWriter.toString().getBytes()));
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

    // Serializes visited states so that the program can be generated in order in the future
    public void serializeVisitedStates(final ArrayList<VulkanState> states, final Writer writer) {
        try {
            writer.append("\n//VisitedStates #");
            objectMapper.writeValue(writer, states);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
