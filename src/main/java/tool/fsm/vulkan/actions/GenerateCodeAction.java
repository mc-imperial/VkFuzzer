package tool.fsm.vulkan.actions;

import org.statefulj.fsm.RetryException;
import org.statefulj.fsm.model.Action;
import tool.fsm.ExitCondition;
import tool.fsm.vulkan.VulkanEntity;

/**
 * Created by constantinos on 28/03/2016.
 * Performs the code generation action
 */
public class GenerateCodeAction<T extends VulkanEntity> implements Action<T> {
    private final ExitCondition exitCondition;

    public GenerateCodeAction(final ExitCondition exitCondition) {
        this.exitCondition = exitCondition;
    }

    @Override
    public void execute(T stateful, String event, Object... args) throws RetryException {
        // Generate code and exit if we have reached the STOP state
        if (!stateful.generateStateCode()) {
            exitCondition.signalExit();
        }
    }
}
