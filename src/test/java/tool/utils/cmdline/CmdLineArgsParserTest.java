package tool.utils.cmdline;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by constantinos on 25/03/2016.
 */
public class CmdLineArgsParserTest {
    private CmdLineArgsParser cmdLineArgsParser;

    @Before
    public void setup() {
        cmdLineArgsParser = new CmdLineArgsParser();
    }

    @Test
    public void correctlyParsesArguments() {
        String[] args = {"-samples", "3", "-output" , "/path" };

        try {
            CmdLineOptions option = cmdLineArgsParser.parseArguments(args);
        } catch (RuntimeException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void failsToParseArgumentsWhenRequiredArgumentIsMissing() {
        String[] args = {"-samples", "3", "-output" };

        try {
            CmdLineOptions option = cmdLineArgsParser.parseArguments(args);
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void failsToParseArgumentsWhenSamplesSizeIsNotANumber() {
        String[] args = {"-samples", "xxxxx", "-output" , "/path" };

        try {
            CmdLineOptions option = cmdLineArgsParser.parseArguments(args);
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
    }
}
