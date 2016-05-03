package tool.fuzzer.vulkan;

import tool.Main;
import tool.fuzzer.ShaderGenerator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by constantinos on 28/04/2016.
 */
public class VulkanShaderGenerator implements ShaderGenerator {
    private final String SHADER_FOLDER = "shaders/vulkan";
    private final String SIMPLE_VERTEX_SHADER = "simple-vert.spv";
    private final String SIMPLE_FRAGMENT_SHADER = "simple-frag.spv";

    @Override
    public void generateShaders(String outputFolder) {
        try {
            ClassLoader loader = Main.class.getClassLoader();

            // Copy vertex shader
            InputStream vertexShader = loader.getResourceAsStream(SHADER_FOLDER
                    + "/" + SIMPLE_VERTEX_SHADER);
            File vertexFile = new File(outputFolder + "/" +SIMPLE_VERTEX_SHADER);
            Files.copy(vertexShader, vertexFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            // Copy fragment shader
            InputStream fragmentShader = loader.getResourceAsStream(SHADER_FOLDER
                    + "/" + SIMPLE_FRAGMENT_SHADER);
            File fragmentFile = new File(outputFolder + "/" +SIMPLE_FRAGMENT_SHADER);
            Files.copy(fragmentShader, fragmentFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
