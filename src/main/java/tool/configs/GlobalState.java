package tool.configs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by constantinos on 15/04/2016.
 * GlobalState base class used to store configs
 */
public class GlobalState {
    protected HashMap<String, ArrayList<Config>> configs;

    public GlobalState() {
        configs = new HashMap<>();
    }

    public HashMap<String, ArrayList<Config>> getConfigs() {
        return configs;
    }
}
