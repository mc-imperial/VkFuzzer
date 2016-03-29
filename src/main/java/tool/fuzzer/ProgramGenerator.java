package tool.fuzzer;

import tool.fsm.FuzzerFSM;
import tool.fsm.vulkan.VulkanFSM;

/**
 * Created by constantinos on 28/03/2016.
 */
public class ProgramGenerator {
    private final Library library;
    private FuzzerFSM fsm;

    public ProgramGenerator(final Library library) {
        this.library = library;
        initLibraryGenerator();
    }

    public void generatePrograms(final int size) {
        for (int i = 0; i < size; ++i) {
            fsm.generate();
            fsm.reset();
        }
    }

    private void initLibraryGenerator() {
        if (library == Library.VULKAN) {
            fsm = new VulkanFSM();
        }
    }
}
