package tool.fsm;

import tool.configs.Config;

/**
 * Created by constantinos on 30/03/2016.
 */
public interface Entity {
    public boolean generateStateCode();
    public void generateStateCode(final String state, final Config config);
    public void saveGeneratedProgram(final String output);
}
