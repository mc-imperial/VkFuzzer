package tool.fsm.vulkan.initializers;

import org.statefulj.fsm.model.State;
import org.statefulj.fsm.model.impl.StateImpl;
import org.statefulj.persistence.memory.MemoryPersisterImpl;
import tool.fsm.ExitCondition;
import tool.fsm.vulkan.VulkanEntity;
import tool.fsm.vulkan.actions.GenerateCodeAction;
import tool.fsm.vulkan.events.VulkanEvent;
import tool.fsm.vulkan.states.VulkanState;
import tool.fsm.vulkan.transitions.SimpleTransition;
import tool.fsm.vulkan.transitions.TransitionType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by constantinos on 30/03/2016.
 * Initializes states
 */
public class StatesInitializer {
    private final ExitCondition exitCondition;
    private Map<VulkanState, State<VulkanEntity>> states;
    private MemoryPersisterImpl<VulkanEntity> persister;
    private GenerateCodeAction<VulkanEntity> generateCodeAction;

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
    public void initializeStates() {
        createStates();

        defineTransition(VulkanState.START,
                VulkanState.VK_ENUMERATE_INSTANCE_EXTENSION_PROPERTIES,
                TransitionType.SEQUENTIAL);

        defineTransition(VulkanState.VK_ENUMERATE_INSTANCE_EXTENSION_PROPERTIES,
                VulkanState.VK_ENUMERATE_INSTANCE_LAYER_PROPERTIES,
                TransitionType.REPEATING);

        defineTransition(VulkanState.VK_ENUMERATE_INSTANCE_LAYER_PROPERTIES,
                VulkanState.VK_APPLICATION_INFO,
                TransitionType.REPEATING);

        defineTransition(VulkanState.VK_APPLICATION_INFO,
                VulkanState.VK_INSTANCE_CREATE_INFO,
                TransitionType.REPEATING);

        defineTransition(VulkanState.VK_INSTANCE_CREATE_INFO,
                VulkanState.VK_CREATE_INSTANCE,
                TransitionType.REPEATING);

        defineTransition(VulkanState.VK_CREATE_INSTANCE,
                VulkanState.VK_ENUMERATE_PHYSICAL_DEVICES,
                TransitionType.REPEATING);

        defineTransition(VulkanState.VK_ENUMERATE_PHYSICAL_DEVICES,
                VulkanState.STOP,
                TransitionType.REPEATING);

        LinkedList<State<VulkanEntity>> fsmStates =
                new LinkedList<>(states.values());

        persister = new MemoryPersisterImpl<>(fsmStates,
                states.get(VulkanState.START));
    }

    private void createStates() {
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

    private void defineTransition(VulkanState state,
                                  VulkanState nextState,
                                  TransitionType transitionType) {
        String event = VulkanEvent.GENERATE_PROGRAM.toString();
        State<VulkanEntity> fsmState = states.get(state);
        State<VulkanEntity> nextFsmState = states.get(nextState);

        switch (transitionType) {
            case REPEATING:
                fsmState.addTransition(event,
                        new SimpleTransition<>(
                                generateCodeAction,
                                fsmState,
                                nextFsmState));
                break;
            case SEQUENTIAL:
                fsmState.addTransition(event, nextFsmState, generateCodeAction);
        }
    }
}
