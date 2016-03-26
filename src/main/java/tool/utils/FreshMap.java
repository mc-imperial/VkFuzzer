package tool.utils;

import java.util.HashMap;

/**
 * Created by constantinos on 25/03/2016.
 * The class keeps provides unique identifiers to variables
 */
public class FreshMap {
    private final HashMap<String, Integer> map;
    private final int INITIAL_ID = -1;

    public FreshMap() {
        map = new HashMap<>();
    }

    // Returns a fresh id for a given variable
    public int getFreshId(String variable) {
        int freshId = map.getOrDefault(variable, INITIAL_ID) + 1;
        map.put(variable, freshId);
        return freshId;
    }
}
