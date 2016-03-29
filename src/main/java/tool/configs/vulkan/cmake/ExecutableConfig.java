package tool.configs.vulkan.cmake;

import tool.configs.Config;

/**
 * Created by constantinos on 29/03/2016.
 */
public class ExecutableConfig extends Config {
    private String source;
    private String name;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
