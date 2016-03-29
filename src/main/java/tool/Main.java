package tool;

import tool.fuzzer.ProgramGenerator;
import tool.utils.cmdline.CmdLineArgsParser;
import tool.utils.cmdline.CmdLineOptions;

/**
 * Created by constantinos on 25/03/2016.
 */
public class Main {
    public static void main(String[] args) {
        CmdLineArgsParser cmdLineArgsParser = new CmdLineArgsParser();
        CmdLineOptions options = null;

        try {
            options = cmdLineArgsParser.parseArguments(args);
        } catch (RuntimeException exception) {
            cmdLineArgsParser.printHelp();
            System.exit(1);
        }

        System.out.println(options.getOutputFolder());
        ProgramGenerator generator = new ProgramGenerator(options.getLibrary());
        generator.generatePrograms(options.getSamples(),
                options.getOutputFolder());
    }
}
