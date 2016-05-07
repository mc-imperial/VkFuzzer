package tool.fsm.vulkan.transitions;

import org.statefulj.fsm.model.Action;
import org.statefulj.fsm.model.State;
import org.statefulj.fsm.model.StateActionPair;
import org.statefulj.fsm.model.Transition;
import org.statefulj.fsm.model.impl.StateActionPairImpl;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Constantinos on 07/05/2016.
 */
public class BiasedTransition<T>implements Transition<T> {
    private final double BIAS = 0.995;
    private final Random random;
    private final State<T> current;
    private final ArrayList<State<T>> next;
    private final Action<T> action;

    public BiasedTransition(final Action<T> action,
                            final State<T> current,
                            final ArrayList<State<T>> next) {
        random = new Random();
        this.current = current;
        this.next = next;
        this.action = action;
    }

    @Override
    public StateActionPair<T> getStateActionPair(T stateful) {
        State<T> nextState = random.nextDouble() <= BIAS ? next.get(0) : next.get(1);
        return new StateActionPairImpl<>(nextState, action);
    }
}
