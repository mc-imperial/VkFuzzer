package tool.configs;

import java.util.ArrayList;

/**
 * Created by constantinos on 26/03/2016.
 * Base class for configs
 */
public class Config {
    protected ArrayList<Integer> dependencies;
    protected int id;
    protected boolean isBad;

    public Config() {
        dependencies = new ArrayList<>();
        isBad = false;
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

    public void addDependency(int id) { this.dependencies.add(id); }

    public void setDependencies(ArrayList<Integer> dependencies) {
        this.dependencies = dependencies;
    }

    public boolean isBad() {
        return isBad;
    }

    public void setBad(boolean bad) {
        isBad = bad;
    }
}
