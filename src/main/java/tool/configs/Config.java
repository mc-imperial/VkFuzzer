package tool.configs;

import java.util.ArrayList;

/**
 * Created by constantinos on 26/03/2016.
 */
public class Config {
    protected int id;
    protected ArrayList<Integer> dependencies;

    public Config() {
        dependencies = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getDependencies() {
        return dependencies;
    }

    public void setDependencies(int id) {
        this.id = id;
    }
}
