package tool.fsm.vulkan.actions;

import org.statefulj.fsm.RetryException;
import org.statefulj.fsm.model.Action;
import tool.fsm.ExitCondition;
import tool.fsm.vulkan.VulkanEntity;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.TemplateEngine;

/**
 * Created by constantinos on 28/03/2016.
 */
public class GenerateCodeAction<T extends VulkanEntity> implements Action<T> {
    private final TemplateEngine templateEngine;
    private final ExitCondition exitCondition;

    public GenerateCodeAction(final TemplateEngine templateEngine,
                              final ExitCondition exitCondition) {
        this.templateEngine = templateEngine;
        this.exitCondition = exitCondition;
    }

    @Override
    public void execute(T stateful, String event, Object... args) throws RetryException {
        if (!stateful.getCurrentState().equals(VulkanState.STOP.toString())) {
            // Generate code
//            VulkanCodeGenerator generator =
//                    (VulkanCodeGenerator)stateful.getCurrentCodeGenerator();
//            templateEngine.generateCode(generator.getTemplate(),
//                    generator.generateConfig());
        } else {
            // Hit stop. Now exit
            exitCondition.signalExit();
        }
    }
}
