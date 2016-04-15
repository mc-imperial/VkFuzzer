package tool.minimization;

import tool.fuzzer.Library;
import tool.minimization.vulkan.VulkanProgramMinimizer;

/**
 * Created by cvryo on 14/04/2016.
 * Delegates minimization of a program to the correct class
 */
public class ProgramMinimizer {
    private Minimizer minimizer;

    public ProgramMinimizer(final Library library) {
        reset(library);
    }

    // Delegates minimization of a program
    public void minimizeProgram(final String inputMetaFile, final int id) {
        minimizer.minimizeProgram(inputMetaFile, id);
    }

    // Initializes the correct delegate
    private void reset(final Library library) {
        if (library == Library.VULKAN) {
            minimizer = new VulkanProgramMinimizer();
        }
    }
}
