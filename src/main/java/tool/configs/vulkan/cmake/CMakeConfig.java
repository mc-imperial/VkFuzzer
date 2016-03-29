package tool.configs.vulkan.cmake;

import tool.configs.Config;

/**
 * Created by constantinos on 29/03/2016.
 */
public class CMakeConfig extends Config {
    private Config[] executables;
    private String sdkRoot = "${VULKAN_SDK_ROOT}";
    private String cFlags = "${CMAKE_C_FLAGS}";
    private String cppFlags = "${CMAKE_CXX_FLAGS}";
    private String binaryFolder = "${CMAKE_BINARY_DIR}";

    public Config[] getExecutables() {
        return executables;
    }

    public void setExecutables(Config[] executables) {
        this.executables = executables;
    }

    public String getSdkRoot() {
        return sdkRoot;
    }

    public void setSdkRoot(String sdkRoot) {
        this.sdkRoot = sdkRoot;
    }

    public String getcFlags() {
        return cFlags;
    }

    public void setcFlags(String cFlags) {
        this.cFlags = cFlags;
    }

    public String getCppFlags() {
        return cppFlags;
    }

    public void setCppFlags(String cppFlags) {
        this.cppFlags = cppFlags;
    }

    public String getBinaryFolder() {
        return binaryFolder;
    }

    public void setBinaryFolder(String binaryFolder) {
        this.binaryFolder = binaryFolder;
    }
}
