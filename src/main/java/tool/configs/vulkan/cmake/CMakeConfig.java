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
    private String major = "${MAJOR}";
    private String vulkanLoaderName = "${VULKAN_LOADER_NAME}";
    private String vulkanLoader = "${VULKAN_LOADER}";
    private String platformSources = "${PLATFORM_SOURCES}";
    private String platformIndependentSources = "${PLATFORM_INDEPENDENT_HEADERS}";
    private String xcbLibraries = "${XCB_LIBRARIES}";
    private String cmakeModulePath = "${CMAKE_MODULE_PATH}";
    private String cmakeSourceDir = "${CMAKE_SOURCE_DIR}";

    public String getCmakeSourceDir() {
        return cmakeSourceDir;
    }

    public void setCmakeSourceDir(String cmakeSourceDir) {
        this.cmakeSourceDir = cmakeSourceDir;
    }

    public String getCmakeModulePath() {
        return cmakeModulePath;
    }

    public void setCmakeModulePath(String cmakeModulePath) {
        this.cmakeModulePath = cmakeModulePath;
    }

    public String getXcbLibraries() {
        return xcbLibraries;
    }

    public void setXcbLibraries(String xcbLibraries) {
        this.xcbLibraries = xcbLibraries;
    }

    public String getPlatformIndependentSources() {
        return platformIndependentSources;
    }

    public void setPlatformIndependentSources(String platformIndependentSources) {
        this.platformIndependentSources = platformIndependentSources;
    }

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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getVulkanLoaderName() {
        return vulkanLoaderName;
    }

    public void setVulkanLoaderName(String vulkanLoaderName) {
        this.vulkanLoaderName = vulkanLoaderName;
    }

    public String getVulkanLoader() {
        return vulkanLoader;
    }

    public void setVulkanLoader(String vulkanLoader) {
        this.vulkanLoader = vulkanLoader;
    }

    public String getPlatformSources() {
        return platformSources;
    }

    public void setPlatformSources(String platformSources) {
        this.platformSources = platformSources;
    }
}
