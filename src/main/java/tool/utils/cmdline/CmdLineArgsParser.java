package tool.utils.cmdline;

import org.apache.commons.cli.*;

/**
 * Created by constantinos on 25/03/2016.
 * A class that parses command line arguments
 */
public class CmdLineArgsParser {
    private final Options options;
    private final String OUTPUT_OPTION = "output";
    private final String SAMPLE_OPTION = "samples";

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

            // Get
            try {
                String outputFolder = line.getOptionValue(OUTPUT_OPTION);
                int samples = Integer.parseInt(line.getOptionValue(SAMPLE_OPTION));
                return new CmdLineOptions(outputFolder, samples);
            } catch (NumberFormatException e) {
                throw new ParseException("Samples size is not a number");
            }

        } catch (ParseException parseException) {
            System.err.println("Invalid arguments.  Reason: " +
                    parseException.getMessage());
        }

        throw new RuntimeException();
    }

    // Prints the program usage
    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar VulkanFuzzer-X.Y-SNAPSHOT.jar", options);
    }

    private void addCmdLineOptions() {
        Option output = Option.builder(OUTPUT_OPTION).argName("folder")
                .hasArg()
                .required()
                .desc("Specifies the output folder for the generated programs")
                .build();

        Option samples = Option.builder(SAMPLE_OPTION).argName("size")
                .hasArg()
                .required()
                .desc("Specifies the number of programs to generate")
                .build();

        options.addOption(output);
        options.addOption(samples);
    }
}
