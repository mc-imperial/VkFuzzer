cmake_minimum_required (VERSION 2.6)
project (VulkanPrograms)

# Add compile flags and defines
set(CMAKE_C_FLAGS "${config.cFlags} -DVK_PROTOTYPES")
set(CMAKE_CXX_FLAGS "${config.cppFlags} -std=c++11 -DVK_PROTOTYPES")

set(VULKAN_SDK_ROOT "C:/VulkanSDK/1.0.3.1/")

# Set platform specific library includes and libraries
if(WIN32)
include_directories(${config.sdkRoot}/Include)
endif()

# Copy test runner
file(COPY "TestRunner.py" DESTINATION "${config.binaryFolder}")

<#list config.executables as executable>

# Create executable
add_executable(${executable.name} ${executable.source})
# Link executable
if(WIN32)
target_link_libraries(${executable.name} ${config.sdkRoot}/Source/lib/vulkan-1.lib)
else()
target_link_libraries(${executable.name} vulkan)
endif()
</#list>
