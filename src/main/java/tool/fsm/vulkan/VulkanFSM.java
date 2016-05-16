package tool.fsm.vulkan;

import org.statefulj.fsm.FSM;
import org.statefulj.fsm.TooBusyException;
import tool.configs.vulkan.VulkanGlobalState;
import tool.fsm.ExitCondition;
import tool.fsm.FuzzerFSM;
import tool.fsm.vulkan.events.VulkanEvent;
import tool.fsm.vulkan.initializers.*;
import tool.fuzzer.Component;

/**
 * Created by constantinos on 29/03/2016.
 * An FSM that constructs vulkan programs
 */
public class VulkanFSM implements FuzzerFSM {
    private final VulkanEntity entity;
    private final Component component;
    private ExitCondition exitCondition;
    private FSM<VulkanEntity> fsm;

    public VulkanFSM(final VulkanEntity entity, final Component component) {
        this.entity = entity;
        this.component = component;
        reset();
    }

    // Loops to generate code
    @Override
    public void generate()  {
        try {
            String event = VulkanEvent.GENERATE_PROGRAM.toString();
            while (!exitCondition.shouldExit()) {
                fsm.onEvent(entity, event);
            }
        } catch (TooBusyException e) {
            System.err.println(e.getMessage());
        }
    }

    // Reset the FSM
    private void reset() {
        exitCondition = new ExitCondition();

        StatesInitializer initializer = null;

        if (component == Component.COMPUTE) {
            initializer = new ComputeStatesInitializer(exitCondition);
        } else if (component == Component.GRAPHICS) {
            throw new RuntimeException("Component " + component +
                    " is not available for fuzzing yet.");
        } else {
            initializer = new MixedStatesInitializer(exitCondition);
        }

        initializer.initializeStates();
        fsm = new FSM<>(initializer.getPersister());
    }
}
