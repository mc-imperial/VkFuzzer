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

import java.util.ArrayList;
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

        // Start of sequential  part of the fsm
        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.START,
                VulkanState.VK_ENUMERATE_INSTANCE_EXTENSION_PROPERTIES);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_ENUMERATE_INSTANCE_EXTENSION_PROPERTIES,
                VulkanState.VK_ENUMERATE_INSTANCE_LAYER_PROPERTIES);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_ENUMERATE_INSTANCE_LAYER_PROPERTIES,
                VulkanState.VK_APPLICATION_INFO);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_APPLICATION_INFO,
                VulkanState.VK_INSTANCE_CREATE_INFO);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_INSTANCE_CREATE_INFO,
                VulkanState.VK_CREATE_INSTANCE);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_INSTANCE,
                VulkanState.VK_CREATE_SURFACE_KHR);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.VK_CREATE_SURFACE_KHR,
                VulkanState.VK_ENUMERATE_PHYSICAL_DEVICES);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_ENUMERATE_PHYSICAL_DEVICES,
                VulkanState.GET_DEVICE_PROPERTIES);

        defineTransition(TransitionType.REPEATING,
                VulkanState.GET_DEVICE_PROPERTIES,
                VulkanState.VK_CREATE_DEVICE);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_DEVICE,
                VulkanState.INIT_SWAPCHAIN);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.INIT_SWAPCHAIN,
                VulkanState.VK_CREATE_COMMAND_POOL);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_COMMAND_POOL,
                VulkanState.VK_ALLOCATE_COMMAND_BUFFERS);

        // Define random transitions from the VK_ALLOCATE_COMMAND_BUFFERS state
        VulkanState[] randomStates =
        {
                VulkanState.VK_CREATE_SEMAPHORE,
                VulkanState.VK_CREATE_EVENT,
                VulkanState.VK_CREATE_FENCE,
                VulkanState.VK_GET_DEVICE_QUEUE,
                VulkanState.VK_CREATE_IMAGE,
                VulkanState.VK_CREATE_BUFFER,
                VulkanState.VK_CREATE_SHADER_MODULE,
                VulkanState.DEALLOCATION
        };

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_ALLOCATE_COMMAND_BUFFERS,
                VulkanState.VK_CREATE_IMAGE_VIEW);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.VK_CREATE_IMAGE_VIEW,
                randomStates);

        // End of sequential part

        // Entering random path
        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_GET_DEVICE_QUEUE,
                randomStates);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_EVENT,
                VulkanState.VK_GET_EVENT_STATUS);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_GET_EVENT_STATUS,
                VulkanState.VK_RESET_EVENT);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_RESET_EVENT,
                VulkanState.VK_SET_EVENT);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_SET_EVENT,
                randomStates);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_FENCE,
                VulkanState.VK_GET_FENCE_STATUS);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_GET_FENCE_STATUS,
                VulkanState.VK_RESET_FENCES);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_RESET_FENCES,
                randomStates);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_SEMAPHORE,
                randomStates);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_BUFFER,
                VulkanState.VK_CREATE_BUFFER_VIEW);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_BUFFER_VIEW,
                VulkanState.VK_GET_BUFFER_MEMORY_REQUIREMENTS);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_GET_BUFFER_MEMORY_REQUIREMENTS,
                randomStates);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_IMAGE,
                VulkanState.VK_GET_IMAGE_MEMORY_REQUIREMENTS);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_GET_IMAGE_MEMORY_REQUIREMENTS,
                VulkanState.VK_GET_IMAGE_SUBRESOURCE_LAYOUT);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_GET_IMAGE_SUBRESOURCE_LAYOUT,
                randomStates);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_SHADER_MODULE,
                VulkanState.VK_CREATE_RENDERPASS);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_RENDERPASS,
                VulkanState.VK_CREATE_FRAMEBUFFER);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_FRAMEBUFFER,
                VulkanState.VK_CREATE_PIPELINE_CACHE);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_PIPELINE_CACHE,
                VulkanState.VK_CREATE_PIPELINE_LAYOUT);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_PIPELINE_LAYOUT,
                VulkanState.VK_CREATE_GRAPHICS_PIPELINES);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_GRAPHICS_PIPELINES,
                randomStates);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.DEALLOCATION,
                VulkanState.STOP);

        LinkedList<State<VulkanEntity>> fsmStates =
                new LinkedList<>(states.values());

        persister = new MemoryPersisterImpl<>(fsmStates,
                states.get(VulkanState.START));
    }

    // Creates and initializes the states
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

    // Defines a transition between two states
    private void defineTransition(TransitionType transitionType,
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
                                generateCodeAction,
                                fsmState,
                                nextStates));
                break;
            case SEQUENTIAL:
                fsmState.addTransition(event, nextStates.get(0), generateCodeAction);
        }
    }
}
