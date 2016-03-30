package tool.fsm.vulkan;

import org.statefulj.fsm.FSM;
import org.statefulj.fsm.TooBusyException;
import tool.fsm.ExitCondition;
import tool.fsm.FuzzerFSM;
import tool.fsm.vulkan.events.VulkanEvent;
import tool.fsm.vulkan.states.StatesInitializer;

/**
 * Created by constantinos on 29/03/2016.
 * An FSM that constructs vulkan programs
 */
public class VulkanFSM implements FuzzerFSM {
    private final VulkanEntity entity;
    private ExitCondition exitCondition;
    private StatesInitializer statesInitializer;
    private FSM<VulkanEntity> fsm;

    public VulkanFSM(final VulkanEntity entity) {
        this.entity = entity;
        reset();
    }

    @Override
    public void reset() {
        exitCondition = new ExitCondition();
        statesInitializer = new StatesInitializer(exitCondition);
        statesInitializer.initializeStates();
        fsm = new FSM<>(statesInitializer.getPersister());
    }

    @Override
    public void generate()  {
        try {
            String event = VulkanEvent.GENERATE_PROGRAM.toString();
            while (!exitCondition.shouldExit()) {
                fsm.onEvent(entity, event);
            }
        } catch (TooBusyException e) {
            e.printStackTrace();
        }
    }
}
