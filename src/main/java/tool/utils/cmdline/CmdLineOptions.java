package tool.utils.cmdline;

import tool.fuzzer.Library;

/**
 * Created by constantinos on 25/03/2016.
 * A class that stores the command line options
 */
public class CmdLineOptions {
    private Library library;
    private String outputFolder;
    private String inputFolder;
    private int id;
    private int samples;
    private boolean minimize;

    public CmdLineOptions(final Library library,
                          final String outputFolder,
                          final int samples,
                          final String inputFolder,
                          final boolean minimize,
                          final int id) {
        this.library = library;
        this.outputFolder = outputFolder;
        this.samples = samples;
        this.id = id;
        this.minimize = minimize;
        this.inputFolder = inputFolder;
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

    public String getInputFolder() {
        return inputFolder;
    }

    public int getId() {
        return id;
    }

    public boolean isMinimize() {
        return minimize;
    }
}
