package tool.utils.cmdline;

import tool.fuzzer.Library;

/**
 * Created by constantinos on 25/03/2016.
 * A class that stores the command line options
 */
public class CmdLineOptions {
    private Library library;
    private String outputFolder;
    private int samples;

    public CmdLineOptions(Library library, String outputFolder, int samples) {
        this.library = library;
        this.outputFolder = outputFolder;
        this.samples = samples;
    }

    public Library getLibrary() {
        return library;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public int getSamples() {
        return samples;
    }
}
