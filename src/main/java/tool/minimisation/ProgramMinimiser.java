package tool.minimisation;

import tool.fuzzer.Library;
import tool.minimisation.vulkan.VulkanProgramMinimiser;

/**
 * Created by cvryo on 14/04/2016.
 */
public class ProgramMinimiser {
    private Minimiser minimiser;

    public ProgramMinimiser(final Library library) {
        reset(library);
    }

    public void minimizeProgram(final String inputMetaFile, final int id) {
        minimiser.minimizeProgram(inputMetaFile, id);
    }

    private void reset(final Library library) {
        if (library == Library.VULKAN) {
            minimiser = new VulkanProgramMinimiser();
        }
    }
}
