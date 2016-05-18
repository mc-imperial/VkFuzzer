package tool.fsm.vulkan.initializers;

import org.statefulj.fsm.model.State;
import org.statefulj.persistence.memory.MemoryPersisterImpl;
import tool.fsm.ExitCondition;
import tool.fsm.vulkan.VulkanEntity;
import tool.fsm.vulkan.states.VulkanState;
import tool.fsm.vulkan.transitions.TransitionType;

import java.util.LinkedList;
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
        createStates();

        // Start of sequential  part of the fsm
        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.START,
                VulkanState.RANDOM_SEED);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.RANDOM_SEED,
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
                VulkanState.SELECT_DEVICE);

        defineTransition(TransitionType.REPEATING,
                VulkanState.SELECT_DEVICE,
                VulkanState.VK_CREATE_COMMAND_POOL);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_COMMAND_POOL,
                VulkanState.VK_ALLOCATE_COMMAND_BUFFERS);

        // Define random transitions from the VK_ALLOCATE_COMMAND_BUFFERS state
        VulkanState[] randomStates0 =
                {
                        VulkanState.VK_CREATE_SEMAPHORE,
                        VulkanState.VK_CREATE_EVENT,
                        VulkanState.VK_CREATE_FENCE,
                        VulkanState.VK_CREATE_IMAGE,
                        VulkanState.VK_CREATE_BUFFER,
                        VulkanState.VK_CREATE_SHADER_MODULE,
                };

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_ALLOCATE_COMMAND_BUFFERS,
                VulkanState.VK_CREATE_IMAGE_VIEW);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.VK_CREATE_IMAGE_VIEW,
                VulkanState.VK_CREATE_RENDERPASS);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.VK_CREATE_RENDERPASS,
                VulkanState.VK_CREATE_FRAMEBUFFER);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.VK_CREATE_FRAMEBUFFER,
                randomStates0);

        // End of sequential part

        // Entering random path
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
                randomStates0);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_FENCE,
                VulkanState.VK_GET_FENCE_STATUS);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_GET_FENCE_STATUS,
                VulkanState.VK_RESET_FENCES);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_RESET_FENCES,
                randomStates0);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_SEMAPHORE,
                randomStates0);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_IMAGE,
                VulkanState.VK_GET_IMAGE_MEMORY_REQUIREMENTS);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_GET_IMAGE_MEMORY_REQUIREMENTS,
                VulkanState.VK_GET_IMAGE_SUBRESOURCE_LAYOUT);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_GET_IMAGE_SUBRESOURCE_LAYOUT,
                randomStates0);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_SHADER_MODULE,
                VulkanState.VK_CREATE_PIPELINE_CACHE);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_PIPELINE_CACHE,
                VulkanState.VK_CREATE_PIPELINE_LAYOUT);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_PIPELINE_LAYOUT,
                VulkanState.VK_CREATE_GRAPHICS_PIPELINES);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_GRAPHICS_PIPELINES,
                randomStates0);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_BUFFER,
                VulkanState.VK_CREATE_BUFFER_VIEW);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CREATE_BUFFER_VIEW,
                VulkanState.VK_GET_BUFFER_MEMORY_REQUIREMENTS);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_GET_BUFFER_MEMORY_REQUIREMENTS,
                VulkanState.POPULATE_VERTEX_BUFFER);

        defineTransition(TransitionType.REPEATING,
                VulkanState.POPULATE_VERTEX_BUFFER,
                VulkanState.VK_ACQUIRE_NEXT_IMAGE_KHR);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.VK_ACQUIRE_NEXT_IMAGE_KHR,
                VulkanState.VK_BEGIN_COMMAND_BUFFER);

        VulkanState[] nextStates2 =
                {
                        VulkanState.VK_CMD_CLEAR_COLOR,
                        VulkanState.VK_CMD_BEGIN_RENDERPASS
                };

        defineTransition(TransitionType.SEQUENTIAL_MULTI,
                VulkanState.VK_BEGIN_COMMAND_BUFFER,
                nextStates2);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_CMD_CLEAR_COLOR,
                VulkanState.VK_END_COMMAND_BUFFER);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.VK_CMD_BEGIN_RENDERPASS,
                VulkanState.DRAW_BUFFERS);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.DRAW_BUFFERS,
                VulkanState.VK_CMD_END_RENDERPASS);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.VK_CMD_END_RENDERPASS,
                VulkanState.VK_END_COMMAND_BUFFER);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.VK_END_COMMAND_BUFFER,
                VulkanState.VK_GET_DEVICE_QUEUE);

        defineTransition(TransitionType.REPEATING,
                VulkanState.VK_GET_DEVICE_QUEUE,
                VulkanState.VK_QUEUE_SUBMIT);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.VK_QUEUE_SUBMIT,
                VulkanState.VK_DEVICE_WAIT_IDLE);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.VK_DEVICE_WAIT_IDLE,
                VulkanState.VK_QUEUE_PRESENT_KHR);

        VulkanState[] randomStates1 =
                {
                        VulkanState.VK_ACQUIRE_NEXT_IMAGE_KHR,
                        VulkanState.STOP,
                };

        defineTransition(TransitionType.BIASED,
                VulkanState.VK_QUEUE_PRESENT_KHR,
                randomStates1);

        defineTransition(TransitionType.SEQUENTIAL,
                VulkanState.DEALLOCATION,
                VulkanState.STOP);

        LinkedList<State<VulkanEntity>> fsmStates =
                new LinkedList<>(states.values());

        persister = new MemoryPersisterImpl<>(fsmStates,
                states.get(VulkanState.START));

    }
}
