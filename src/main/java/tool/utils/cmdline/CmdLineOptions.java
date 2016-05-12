package tool.utils.cmdline;

import tool.fuzzer.Component;
import tool.fuzzer.Library;

/**
 * Created by constantinos on 25/03/2016.
 * A class that stores the command line options
 */
public class CmdLineOptions {
    private final Library library;
    private final String outputFolder;
    private final String inputMetaFile;
    private final int id;
    private final int samples;
    private final boolean minimize;
    private final Component component;

    private CmdLineOptions(final Library library,
                          final String outputFolder,
                          final int samples,
                          final String inputMetaFile,
                          final boolean minimize,
                          final int id,
                          final Component component) {
        this.library = library;
        this.outputFolder = outputFolder;
        this.samples = samples;
        this.id = id;
        this.minimize = minimize;
        this.inputMetaFile = inputMetaFile;
        this.component = component;
    }

    public CmdLineOptions(final Library library,
                          final String outputFolder,
                          final int samples,
                          final Component component) {
        this(library,outputFolder, samples, null,
                false, 0, component);
    }

    public CmdLineOptions(final Library library,
                          final String inputMetaFile,
                          final int id) {
        this(library, null, 0, inputMetaFile,
                true, id, Component.EVERYTHING);
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

    public boolean doMinimize() {
        return minimize;
    }

    public Component getComponent() {return component; }
}
