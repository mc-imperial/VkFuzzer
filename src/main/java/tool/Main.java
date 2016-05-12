package tool;

import tool.fuzzer.ProgramGenerator;
import tool.minimization.ProgramMinimizer;
import tool.utils.cmdline.CmdLineArgsParser;
import tool.utils.cmdline.CmdLineOptions;

/**
 * Created by constantinos on 25/03/2016.
 */
public class Main {
    public static void main(String[] args) {
        CmdLineArgsParser cmdLineArgsParser = new CmdLineArgsParser();
        CmdLineOptions options = null;

        // Parse arguments
        try {
            options = cmdLineArgsParser.parseArguments(args);
        } catch (RuntimeException exception) {
            cmdLineArgsParser.printHelp();
            System.exit(1);
        }

        if (options.doMinimize()) {
            // Minimize program
            ProgramMinimizer minimiser = new ProgramMinimizer(options.getLibrary());
            minimiser.minimizeProgram(options.getInputMetaFile(),
                    options.getId());
        } else {
            // Generate program
            ProgramGenerator generator = new ProgramGenerator(options.getLibrary(),
                    options.getComponent());
            generator.generatePrograms(options.getSamples(),
                    options.getOutputFolder());
        }
    }
}
