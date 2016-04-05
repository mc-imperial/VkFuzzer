package tool.fsm.vulkan.transitions;

import org.statefulj.fsm.model.Action;
import org.statefulj.fsm.model.State;
import org.statefulj.fsm.model.StateActionPair;
import org.statefulj.fsm.model.Transition;
import org.statefulj.fsm.model.impl.StateActionPairImpl;

import java.util.Random;

/**
 * Created by constantinos on 31/03/2016.
 * Defines a transition which can recur on its self
 */
public class SimpleTransition<T> implements Transition<T> {
    private final double RECOUR_CHANCE = 0.1;
    private final Random random;
    private final State<T> current;
    private final State<T> next;
    private final Action<T> action;

    public SimpleTransition(final Action<T> action,
                            final State<T> current,
                            final State<T> next) {
        random = new Random();
        this.current = current;
        this.next = next;
        this.action = action;
    }

    @Override
    public StateActionPair<T> getStateActionPair(T stateful) {
        State<T> nextState;

        if (random.nextDouble() <= RECOUR_CHANCE) {
            nextState = current;
        } else {
            nextState = next;
        }

        return new StateActionPairImpl<>(nextState, action);
    }
}
