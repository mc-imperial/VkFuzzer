package tool.fuzzer;

import tool.fsm.FuzzerFSM;
import tool.fsm.vulkan.VulkanFSM;
import tool.fuzzer.vulkan.VulkanCMakeGenerator;
import tool.fuzzer.vulkan.VulkanTestRunnerGenerator;

/**
 * Created by constantinos on 28/03/2016.
 */
public class ProgramGenerator {
    private final Library library;
    private FuzzerFSM fsm;
    private CMakeGenerator cMakeGenerator;
    private TestRunnerGenerator testRunnerGenerator;

    public ProgramGenerator(final Library library) {
        this.library = library;
        init();
    }

    public void generatePrograms(final int size, final String outputFolder) {
        for (int i = 0; i < size; ++i) {
//            fsm.generate();
//            fsm.reset();
        }

        cMakeGenerator.generateCMakeFile(size, outputFolder);
        testRunnerGenerator.generateTestRunner(outputFolder);
    }

    private void init() {
        if (library == Library.VULKAN) {
            fsm = new VulkanFSM();
            cMakeGenerator = new VulkanCMakeGenerator();
            testRunnerGenerator = new VulkanTestRunnerGenerator();
        }
    }
}
