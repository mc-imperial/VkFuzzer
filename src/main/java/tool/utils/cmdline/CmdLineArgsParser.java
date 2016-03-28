package tool.utils.cmdline;

import org.apache.commons.cli.*;
import tool.fuzzer.Library;

/**
 * Created by constantinos on 25/03/2016.
 * A class that parses command line arguments.
 */
public class CmdLineArgsParser {
    private final String OUTPUT_OPTION = "output";
    private final String SAMPLE_OPTION = "samples";
    private final String LIBRARY_OPTION = "library";
    private final String NUMBER_FORMAT_EXCEPTION_MSG =
            "Samples size is not a number";
    private final String ILLEGAL_ARGUMENT_MSG =
            "Library specified is not supported";
    private final String USAGE_MSG = "java -jar Fuzzer-X.Y-SNAPSHOT.jar";
    private final String OUTPUT_ARG_NAME = "path";
    private final String OUTPUT_OPTION_DESC =
            "Specifies the output path folder for the generated programs";
    private final String SAMPLE_ARG_NAME = "size";
    private final String SAMPLE_OPTION_DESC =
            "Specifies the number of programs to generate";
    private final String LIBRARY_ARG_NAME = "library";
    private final String LIBRARY_OPTION_DESC =
            "Specifies the library used for fuzzing (vulkan)";
    private final String PARSE_EXCEPTION_MSG = "Invalid arguments.  Reason: ";
    private final Options options;

    public CmdLineArgsParser() {
        options = new Options();
        addCmdLineOptions();
    }

    // Parses the commandline arguments and returns a CmdLineOptions object
    // with the argument parameters
    public CmdLineOptions parseArguments(String[] args) throws RuntimeException {
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmdLine = parser.parse(options, args);

            Library library = parseLibrary(cmdLine);
            String outputFolder = cmdLine.getOptionValue(OUTPUT_OPTION);
            int samples = parseSamples(cmdLine);

            return new CmdLineOptions(library, outputFolder, samples);
        } catch (ParseException parseException) {
            System.err.println(PARSE_EXCEPTION_MSG + parseException.getMessage());
        }

        throw new RuntimeException();
    }

    // Prints the program usage
    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(USAGE_MSG, options);
    }

    // Constructs the command line options
    private void addCmdLineOptions() {
        Option output = Option.builder(OUTPUT_OPTION)
                .argName(OUTPUT_ARG_NAME)
                .hasArg()
                .required()
                .desc(OUTPUT_OPTION_DESC)
                .build();

        Option samples = Option.builder(SAMPLE_OPTION)
                .argName(SAMPLE_ARG_NAME)
                .hasArg()
                .required()
                .desc(SAMPLE_OPTION_DESC)
                .build();

        Option library = Option.builder(LIBRARY_OPTION)
                .argName(LIBRARY_ARG_NAME)
                .hasArg()
                .required()
                .desc(LIBRARY_OPTION_DESC)
                .build();

        options.addOption(output);
        options.addOption(samples);
        options.addOption(library);
    }

    // Attempts to parse the supplied library
    private Library parseLibrary(CommandLine cmdLine) throws ParseException {
        try {
            return Library.valueOf(
                    cmdLine.getOptionValue(LIBRARY_OPTION).toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new ParseException(ILLEGAL_ARGUMENT_MSG);
        }
    }

    // Attempts to parse the supplied number of samples
    private int parseSamples(CommandLine cmdLine) throws ParseException {
        try {
            return Integer.parseInt(cmdLine.getOptionValue(SAMPLE_OPTION));
        } catch (NumberFormatException exception) {
            throw new ParseException(NUMBER_FORMAT_EXCEPTION_MSG);
        }
    }
}
