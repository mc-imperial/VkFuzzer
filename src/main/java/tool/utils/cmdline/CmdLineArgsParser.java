package tool.utils.cmdline;

import org.apache.commons.cli.*;

/**
 * Created by constantinos on 25/03/2016.
 * A class that parses command line arguments.
 */
public class CmdLineArgsParser {
    private final String OUTPUT_OPTION = "output";
    private final String SAMPLE_OPTION = "samples";
    private final String NUMBER_FORMAT_EXCEPTION_MSG = "Samples size is not a number";
    private final String USAGE_MSG = "java -jar Fuzzer-X.Y-SNAPSHOT.jar";
    private final String OUTPUT_ARG_NAME = "path";
    private final String OUTPUT_OPTION_DESC =
            "Specifies the output path folder for the generated programs";
    private final String SAMPLE_ARG_NAME = "size";
    private final String SAMPLE_OPTION_DESC =
            "Specifies the number of programs to generate";
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
            CommandLine line = parser.parse(options, args);

            try {
                String outputFolder = line.getOptionValue(OUTPUT_OPTION);
                int samples = Integer.parseInt(line.getOptionValue(SAMPLE_OPTION));
                return new CmdLineOptions(outputFolder, samples);
            } catch (NumberFormatException numberFormatException) {
                throw new ParseException(NUMBER_FORMAT_EXCEPTION_MSG);
            }
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

    private void addCmdLineOptions() {
        Option output = Option.builder(OUTPUT_OPTION).argName(OUTPUT_ARG_NAME)
                .hasArg()
                .required()
                .desc(OUTPUT_OPTION_DESC)
                .build();

        Option samples = Option.builder(SAMPLE_OPTION).argName(SAMPLE_ARG_NAME)
                .hasArg()
                .required()
                .desc(SAMPLE_OPTION_DESC)
                .build();

        options.addOption(output);
        options.addOption(samples);
    }
}
