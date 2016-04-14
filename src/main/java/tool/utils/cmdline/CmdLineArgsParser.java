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
    private final String MINIMIZE_OPTION = "minimize";
    private final String INPUT_OPTION = "input";
    private final String ID_OPTION = "id";
    private final String NUMBER_FORMAT_EXCEPTION_MSG =
            "Samples size is not a number";
    private final String ILLEGAL_ARGUMENT_MSG =
            "Library specified is not supported";
    private final String USAGE_MSG = "java -jar Main-X.Y-SNAPSHOT.jar";
    private final String OUTPUT_ARG_NAME = "path";
    private final String OUTPUT_OPTION_DESC =
            "Specifies the output path folder for the generated programs";
    private final String SAMPLE_ARG_NAME = "size";
    private final String SAMPLE_OPTION_DESC =
            "Specifies the number of programs to generate";
    private final String INPUT_ARG_NAME = "metadata file";
    private final String INPUT_OPTION_DESC =
            "Specifies the metadata file used to minimize the program)";
    private final String ID_ARG_NAME = "id";
    private final String ID_OPTION_DESC =
            "Specifies the id that will be used to minimize the program";
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

            return new CmdLineOptions(library, outputFolder, samples, "", false, 0);
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

        Option inputFile = Option.builder(INPUT_OPTION)
                .argName(INPUT_ARG_NAME)
                .hasArg()
                .required()
                .desc(INPUT_OPTION_DESC)
                .build();

        Option id = Option.builder(ID_OPTION)
                .argName(ID_ARG_NAME)
                .hasArg()
                .required()
                .desc(ID_OPTION_DESC)
                .build();

        options.addOptionGroup(new OptionGroup().addOption(output).addOption(inputFile));
        options.addOptionGroup(new OptionGroup().addOption(samples).addOption(id));
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
