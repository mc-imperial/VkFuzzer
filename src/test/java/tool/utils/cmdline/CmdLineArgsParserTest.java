package tool.utils.cmdline;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by constantinos on 25/03/2016.
 */
public class CmdLineArgsParserTest {
    private String ERROR_MSG = "Expected exception to be thrown";
    private CmdLineArgsParser cmdLineArgsParser;

    @Before
    public void setup() {
        cmdLineArgsParser = new CmdLineArgsParser();
    }

    @Test
    public void correctlyParsesArguments() throws RuntimeException {
        String[] args =
        {
                "-samples", "3",
                "-output" , "/path" ,
                "-library", "vulkan"
        };

        CmdLineOptions option = cmdLineArgsParser.parseArguments(args);
    }

    @Test
    public void failsToParseArgumentsWhenRequiredArgumentIsMissing() {
        String[] args =
        {
                "-samples", "3",
                "-output"
        };

        try {
            CmdLineOptions option = cmdLineArgsParser.parseArguments(args);
            Assert.fail(ERROR_MSG);
        } catch (RuntimeException e) {
            // Exception was expected
        }
    }

    @Test
    public void failsToParseArgumentsWhenSamplesSizeIsNotANumber() {
        String[] args =
        {
                "-samples", "xxxxx",
                "-output" , "/path",
                "-library", "vulkan"
        };

        try {
            CmdLineOptions option = cmdLineArgsParser.parseArguments(args);
            Assert.fail(ERROR_MSG);
        } catch (RuntimeException e) {
            // Exception was expected
        }
    }

    @Test
    public void failsToParseArgumentsWhenLibrarySpecifiedIsNotSupported() {
        String[] args =
        {
                "-samples", "123",
                "-output" , "/path",
                "-library", "dx12"
        };

        try {
            CmdLineOptions option = cmdLineArgsParser.parseArguments(args);
            Assert.fail(ERROR_MSG);
        } catch (RuntimeException e) {
            // Exception was expected
        }
    }

    @Test
    public void failsToParseArgumentsIncompatibleArgumentsAreGiven() {
        String[] args =
        {
                "-in", "/path",
                "-output" , "/path",
                "-library", "dx12"
        };

        try {
            CmdLineOptions option = cmdLineArgsParser.parseArguments(args);
            Assert.fail(ERROR_MSG);
        } catch (RuntimeException e) {
            // Exception was expected
        }
    }
}
