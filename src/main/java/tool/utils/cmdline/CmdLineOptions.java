package tool.utils.cmdline;

import tool.fuzzer.Library;

/**
 * Created by constantinos on 25/03/2016.
 * A class that stores the command line options
 */
public class CmdLineOptions {
    private Library library;
    private String outputFolder;
    private String inputMetaFile;
    private int id;
    private int samples;
    private boolean minimize;
    private boolean graphics;
    private boolean compute;

    private CmdLineOptions(final Library library,
                          final String outputFolder,
                          final int samples,
                          final String inputMetaFile,
                          final boolean minimize,
                          final int id,
                          final boolean graphics,
                          final boolean compute) {
        this.library = library;
        this.outputFolder = outputFolder;
        this.samples = samples;
        this.id = id;
        this.minimize = minimize;
        this.inputMetaFile = inputMetaFile;
        this.graphics = graphics;
        this.compute = compute;
    }

    public CmdLineOptions(final Library library,
                          final String outputFolder,
                          final int samples,
                          final boolean graphics,
                          final boolean compute) {
        this(library,outputFolder, samples, null,
                false, 0, graphics, compute);
    }

    public CmdLineOptions(final Library library,
                          final String inputMetaFile,
                          final int id) {
        this(library, null, 0, inputMetaFile,
                true, id, false, false);
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

    public String getInputMetaFile() {
        return inputMetaFile;
    }

    public int getId() {
        return id;
    }

    public boolean isMinimize() {
        return minimize;
    }

    public boolean isGraphics() {
        return graphics;
    }

    public void setGraphics(boolean graphics) {
        this.graphics = graphics;
    }

    public boolean isCompute() {
        return compute;
    }

    public void setCompute(boolean compute) {
        this.compute = compute;
    }
}
