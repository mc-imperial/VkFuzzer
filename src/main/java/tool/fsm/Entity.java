package tool.fsm;

/**
 * Created by constantinos on 30/03/2016.
 */
public interface Entity {
    public boolean generateStateCode();
    public void saveGeneratedProgram(final String output);
}
