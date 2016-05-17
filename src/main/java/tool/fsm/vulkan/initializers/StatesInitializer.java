package tool.fsm.vulkan.initializers;

import org.statefulj.fsm.model.State;
import org.statefulj.fsm.model.impl.StateImpl;
import org.statefulj.persistence.memory.MemoryPersisterImpl;
import tool.fsm.ExitCondition;
import tool.fsm.vulkan.VulkanEntity;
import tool.fsm.vulkan.actions.GenerateCodeAction;
import tool.fsm.vulkan.events.VulkanEvent;
import tool.fsm.vulkan.states.VulkanState;
import tool.fsm.vulkan.transitions.BiasedTransition;
import tool.fsm.vulkan.transitions.SimpleTransition;
import tool.fsm.vulkan.transitions.TransitionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by constantinos on 16/05/2016.
 */
public abstract class StatesInitializer {
    protected final ExitCondition exitCondition;
    protected Map<VulkanState, State<VulkanEntity>> states;
    protected MemoryPersisterImpl<VulkanEntity> persister;
    protected GenerateCodeAction<VulkanEntity> generateCodeAction;

    public StatesInitializer(final ExitCondition exitCondition) {
        states = new HashMap<>();
        generateCodeAction = new GenerateCodeAction<>(exitCondition);
        this.exitCondition = exitCondition;
    }

    public StatesInitializer(final ExitCondition exitCondition,
                                  final Map<VulkanState, State<VulkanEntity>> states) {
        this.states = states;
        generateCodeAction = new GenerateCodeAction<>(exitCondition);
        this.exitCondition = exitCondition;
    }

    public MemoryPersisterImpl<VulkanEntity> getPersister() {
        return persister;
    }

    // Initializes states
    public abstract void initializeStates();

    // Creates and initializes the states
    protected void createStates() {
        VulkanState vulkanStates[] = VulkanState.values();

        // Initialize everything except last state
        for (int i = 0; i < vulkanStates.length - 1; ++i) {
            VulkanState state = vulkanStates[i];
            states.put(state, new StateImpl<>(state.toString()));
        }

        // Add STOP state
        VulkanState stopState = vulkanStates[vulkanStates.length - 1];
        states.put(stopState, new StateImpl<>(stopState.toString(), true));
    }

    // Defines a transition between two states
    protected void defineTransition(TransitionType transitionType,
                                  VulkanState state,
                                  VulkanState... nextState) {
        String event = VulkanEvent.GENERATE_PROGRAM.toString();
        State<VulkanEntity> fsmState = states.get(state);

        ArrayList<State<VulkanEntity>> nextStates = new ArrayList<>();
        for (VulkanState tmpVulkanState : nextState) {
            nextStates.add(states.get(tmpVulkanState));
        }

        switch (transitionType) {
            case REPEATING:
                fsmState.addTransition(event,
                        new SimpleTransition<>(
                                true,
                                generateCodeAction,
                                fsmState,
                                nextStates));
                break;
            case SEQUENTIAL:
                fsmState.addTransition(event, nextStates.get(0), generateCodeAction);
                break;
            case BIASED:
                fsmState.addTransition(event,
                        new BiasedTransition<>(
                                generateCodeAction,
                                fsmState,
                                nextStates));
                break;
            case SEQUENTIAL_MULTI:
                fsmState.addTransition(event,
                        new SimpleTransition<>(
                                false,
                                generateCodeAction,
                                fsmState,
                                nextStates));
                break;
        }
    }
}
