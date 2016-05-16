package tool.fsm.vulkan.initializers;

import org.statefulj.fsm.model.State;
import tool.fsm.ExitCondition;
import tool.fsm.vulkan.VulkanEntity;
import tool.fsm.vulkan.states.VulkanState;

import java.util.Map;

/**
 * Created by constantinos on 16/05/2016.
 */
public class ComputeStatesInitializer extends StatesInitializer {
    public ComputeStatesInitializer(ExitCondition exitCondition) {
        super(exitCondition);
    }

    public ComputeStatesInitializer(ExitCondition exitCondition, Map<VulkanState, State<VulkanEntity>> states) {
        super(exitCondition, states);
    }

    @Override
    public void initializeStates() {

    }
}
