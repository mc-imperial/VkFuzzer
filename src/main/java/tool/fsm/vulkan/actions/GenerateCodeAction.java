package tool.fsm.vulkan.actions;

import org.statefulj.fsm.RetryException;
import org.statefulj.fsm.model.Action;
import tool.Main;
import tool.codegen.vulkan.VulkanCodeGenerator;
import tool.codegen.vulkan.VulkanTemplates;
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

    public GenerateCodeAction(final ExitCondition exitCondition) {
        this.templateEngine = new TemplateEngine(VulkanTemplates.TEMPLATE_FOLDER,
                Main.class);
        this.exitCondition = exitCondition;
    }

    @Override
    public void execute(T stateful, String event, Object... args) throws RetryException {
        if (!stateful.didReachStop()) {
            // Generate code
            VulkanCodeGenerator generator =
                    (VulkanCodeGenerator)stateful.getCurrentCodeGenerator();
            templateEngine.generateCode(generator.getTemplateName(),
                    generator.generateConfig(), stateful.getWriter());
        } else {
            // Hit stop. Now exit
            exitCondition.signalExit();
        }
    }
}
