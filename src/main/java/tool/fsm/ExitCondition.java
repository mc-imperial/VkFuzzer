package tool.fsm;

/**
 * Created by constantinos on 29/03/2016.
 * A helper class that can signal
 */
public class ExitCondition {
    private boolean exit;

    public ExitCondition() {
        exit = false;
    }

    public boolean shouldExit() {
        return exit;
    }

    public void signalExit() {
        exit = true;
    }
}
