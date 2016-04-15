package tool.serialization;

import tool.configs.GlobalState;

import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by constantinos on 01/04/2016.
 * State serializers interface
 */
public interface StateSerializer {
    public void serializeState(final GlobalState globalState,
                               final Writer writer);
    public void serializeVisitedStates(final ArrayList<String> states,
                                       final Writer writer);

}
