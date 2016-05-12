package tool.fuzzer;

import tool.fsm.Entity;
import tool.fsm.FuzzerFSM;
import tool.fsm.vulkan.VulkanEntity;
import tool.fsm.vulkan.VulkanFSM;
import tool.fuzzer.vulkan.VulkanCMakeGenerator;
import tool.fuzzer.vulkan.VulkanDependencyGenerator;
import tool.fuzzer.vulkan.VulkanShaderGenerator;
import tool.fuzzer.vulkan.VulkanTestRunnerGenerator;

/**
 * Created by constantinos on 28/03/2016.
 * Delegates program creation.
 */
public class ProgramGenerator {
    private final Library library;
    private final Component component;
    private FuzzerFSM fsm;
    private Entity entity;
    private CMakeGenerator cMakeGenerator;
    private TestRunnerGenerator testRunnerGenerator;
    private DependencyGenerator dependencyGenerator;
    private ShaderGenerator shaderGenerator;

    public ProgramGenerator(final Library library, final Component component) {
        this.library = library;
        this.component = component;
        reset();
    }

    // Generate programs
    public void generatePrograms(final int size, final String outputFolder) {
        String basePath = outputFolder + "/Program";
        String fileExtension = ".cpp";

        for (int i = 0; i < size; ++i) {
            String programSource = basePath + i + fileExtension;
            fsm.generate();
            entity.saveGeneratedProgram(programSource);
            reset();
        }

        cMakeGenerator.generateCMakeFile(size, outputFolder);
        testRunnerGenerator.generateTestRunner(outputFolder);
        dependencyGenerator.generateDependencies(outputFolder);
        shaderGenerator.generateShaders(outputFolder);
    }

    // Initialise platform specific classes
    private void reset() {
        if (library == Library.VULKAN) {
            entity = new VulkanEntity();
            fsm = new VulkanFSM((VulkanEntity)entity);
            cMakeGenerator = new VulkanCMakeGenerator(component);
            testRunnerGenerator = new VulkanTestRunnerGenerator();
            shaderGenerator = new VulkanShaderGenerator();
            dependencyGenerator = new VulkanDependencyGenerator();
        }
    }
}
