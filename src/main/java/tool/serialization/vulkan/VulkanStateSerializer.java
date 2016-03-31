package tool.serialization.vulkan;

import com.fasterxml.jackson.databind.ObjectMapper;
import tool.configs.vulkan.VulkanGlobalState;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Base64;

/**
 * Created by constantinos on 31/03/2016.
 */
public class VulkanStateSerializer {
    private final ObjectMapper objectMapper;

    public VulkanStateSerializer() {
        objectMapper = new ObjectMapper();
    }

    public void serializeState(final VulkanGlobalState globalState,
                               final Writer writer) {
        StringWriter stringWriter = new StringWriter();

        try {
            objectMapper.writeValue(stringWriter, globalState.getConfigs());

            writer.append("\nStates //");
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

//    public HashMap<VulkanState, ArrayList<Config>> deserialize(String content) throws Exception {
//        try {
//
//            return deserialized;
//        } catch (IOException exception) {
//            System.err.println(exception.getMessage());
//        }
//
//        throw new Exception();
//    }
}
