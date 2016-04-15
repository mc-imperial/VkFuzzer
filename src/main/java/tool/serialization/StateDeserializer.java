package tool.serialization;

import tool.configs.GlobalState;

import java.util.ArrayList;

/**
 * Created by constantinos on 01/04/2016.
 * State deserializers interface
 */
public interface StateDeserializer {
    public GlobalState deserializeConfigs(final String metaFile);
    public ArrayList<String> deserializeVisitedStates(final String metaFile);
}
