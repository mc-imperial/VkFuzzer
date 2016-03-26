package tool.utils.cmdline;

/**
 * Created by constantinos on 25/03/2016.
 * A class that stores the command line options
 */
public class CmdLineOptions {
    private String outputFolder;
    private int samples;

    public CmdLineOptions(String outputFolder, int samples) {
        this.outputFolder = outputFolder;
        this.samples = samples;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public int getSamples() {
        return samples;
    }
}
