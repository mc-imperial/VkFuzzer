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

import java.util.LinkedList;
import java.util.List;

/**
 * Created by constantinos on 30/03/2016.
 * Initializes states
 */
public class StatesInitializer {
    private final ExitCondition exitCondition;
    private List<State<VulkanEntity>> states;
    private MemoryPersisterImpl<VulkanEntity> persister;
    private GenerateCodeAction<VulkanEntity> generateCodeAction;

    public StatesInitializer(final ExitCondition exitCondition) {
        states = new LinkedList<>();
        generateCodeAction = new GenerateCodeAction<>(exitCondition);
        this.exitCondition = exitCondition;
    }

    public StatesInitializer(final ExitCondition exitCondition,
                             final LinkedList<State<VulkanEntity>> states) {
        this.states = states;
        generateCodeAction = new GenerateCodeAction<>(exitCondition);
        this.exitCondition = exitCondition;
    }

    public MemoryPersisterImpl<VulkanEntity> getPersister() {
        return persister;
    }

    // Initializes states
    public void initializeStates() {
        String event = VulkanEvent.GENERATE_PROGRAM.toString();

        // Initial and stopping states
        State<VulkanEntity> start =
                new StateImpl<>(VulkanState.START.toString());
        State<VulkanEntity> stop =
                new StateImpl<>(VulkanState.STOP.toString(), true);

        //VkEnumerateInstanceProperties
        State<VulkanEntity> vkEnumerateInstanceExtensionProperties =
                new StateImpl<>(VulkanState.VK_ENUMERATE_INSTANCE_EXTENSION_PROPERTIES.toString());
        State<VulkanEntity> vkEnumerateInstanceLayerProperties =
                new StateImpl<>(VulkanState.VK_ENUMERATE_INSTANCE_LAYER_PROPERTIES.toString());

        // States for a call to vkCreateInstance() and its prerequisites
        State<VulkanEntity> vkApplicationInfo =
                new StateImpl<>(VulkanState.VK_APPLICATION_INFO.toString());
        State<VulkanEntity> vkInstanceCreateInfo =
                new StateImpl<>(VulkanState.VK_INSTANCE_CREATE_INFO.toString());
        State<VulkanEntity> vkCreateInstance =
                new StateImpl<>(VulkanState.VK_CREATE_INSTANCE.toString());

        // Define transitions
        start.addTransition(event, vkEnumerateInstanceExtensionProperties, generateCodeAction);
        vkEnumerateInstanceExtensionProperties.addTransition(event,
                new SimpleTransition<>(generateCodeAction,
                        vkEnumerateInstanceExtensionProperties,
                        vkEnumerateInstanceLayerProperties));
        vkEnumerateInstanceLayerProperties.addTransition(event,
                new SimpleTransition<>(generateCodeAction,
                        vkEnumerateInstanceLayerProperties,
                        vkApplicationInfo));
        vkApplicationInfo.addTransition(event,
                new SimpleTransition<>(generateCodeAction,
                        vkApplicationInfo,
                        vkInstanceCreateInfo));
        vkInstanceCreateInfo.addTransition(event,
                new SimpleTransition<>(generateCodeAction,
                        vkInstanceCreateInfo,
                        vkCreateInstance));
        vkCreateInstance.addTransition(event,
                new SimpleTransition<>(generateCodeAction,
                        vkCreateInstance,
                        stop));

        // Add to states
        states.add(start);
        states.add(stop);
        states.add(vkEnumerateInstanceExtensionProperties);
        states.add(vkEnumerateInstanceLayerProperties);
        states.add(vkApplicationInfo);
        states.add(vkInstanceCreateInfo);
        states.add(vkCreateInstance);

        persister = new MemoryPersisterImpl<>(states, start);
    }
}
