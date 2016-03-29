package tool.fsm.vulkan;

import org.statefulj.fsm.FSM;
import org.statefulj.fsm.TooBusyException;
import org.statefulj.fsm.model.State;
import org.statefulj.fsm.model.impl.StateImpl;
import org.statefulj.persistence.memory.MemoryPersisterImpl;
import tool.Fuzzer;
import tool.fsm.ExitCondition;
import tool.fsm.FuzzerFSM;
import tool.fsm.vulkan.actions.GenerateCodeAction;
import tool.fsm.vulkan.events.VulkanEvent;
import tool.fsm.vulkan.states.VulkanState;
import tool.utils.TemplateEngine;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by constantinos on 29/03/2016.
 */
public class VulkanFSM implements FuzzerFSM {
    private final String TEMPLATE_FOLDER = "templates/vulkan";
    private final TemplateEngine templateEngine;
    private final ExitCondition exitCondition;
    private VulkanEntity entity;
    private GenerateCodeAction<VulkanEntity> generateCodeAction;
    private List<State<VulkanEntity>> states;
    private FSM<VulkanEntity> fsm;
    private MemoryPersisterImpl<VulkanEntity> persister;

    public VulkanFSM() {
        states = new LinkedList<>();
        entity = new VulkanEntity();
        templateEngine = new TemplateEngine(TEMPLATE_FOLDER, Fuzzer.class);
        exitCondition = new ExitCondition();
        generateCodeAction = new GenerateCodeAction<>(templateEngine,
                exitCondition);
        init();
        persister = new MemoryPersisterImpl<>(states, states.get(4));
        fsm = new FSM<>(persister);
    }

    @Override
    public void reset() {

    }

    @Override
    public String generate()  {
        try {
            while (!exitCondition.shouldExit()) {
                fsm.onEvent(entity, VulkanEvent.GENERATE_PROGRAM.toString());
            }
        } catch (TooBusyException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void init() {
        State<VulkanEntity> stateA = new StateImpl<>(VulkanState.VK_APPLICATION_INFO.toString());
        State<VulkanEntity> stateB = new StateImpl<>(VulkanState.VK_INSTANCE_CREATE_INFO.toString());
        State<VulkanEntity> stateC = new StateImpl<>(VulkanState.VK_CREATE_INSTANCE.toString());
        State<VulkanEntity> stateD = new StateImpl<>(VulkanState.START.toString());
        State<VulkanEntity> stateE = new StateImpl<>(VulkanState.STOP.toString(), true);

        stateE.addTransition(VulkanEvent.GENERATE_PROGRAM.toString(),
                stateA, generateCodeAction);
        stateA.addTransition(VulkanEvent.GENERATE_PROGRAM.toString(),
                stateB, generateCodeAction);
        stateB.addTransition(VulkanEvent.GENERATE_PROGRAM.toString(),
                stateC, generateCodeAction);
        stateC.addTransition(VulkanEvent.GENERATE_PROGRAM.toString(),
                stateE, generateCodeAction);

        states.add(stateA);
        states.add(stateB);
        states.add(stateC);
        states.add(stateD);
        states.add(stateE);
    }
}
