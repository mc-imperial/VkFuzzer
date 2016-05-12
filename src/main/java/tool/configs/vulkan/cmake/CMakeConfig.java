package tool.configs.vulkan.cmake;

import tool.configs.Config;

/**
 * Created by constantinos on 29/03/2016.
 */
public class CMakeConfig extends Config {
    private Config[] executables;
    private boolean everything;

    public boolean isEverything() {
        return everything;
    }

    public void setEverything(boolean everything) {
        this.everything = everything;
    }

    public Config[] getExecutables() {
        return executables;
    }

    public void setExecutables(Config[] executables) {
        this.executables = executables;
    }
}
