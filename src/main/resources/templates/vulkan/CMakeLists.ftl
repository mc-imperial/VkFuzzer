cmake_minimum_required (VERSION 2.6)
project (VulkanPrograms)

# The MAJOR number of the version we're building, used in naming
# vulkan-(major).dll (and other files).
set(MAJOR "1")

# Add compile flags and defines
if(WIN32)
    set (CMAKE_C_FLAGS "${config.cFlags} -D_CRT_SECURE_NO_WARNINGS -D_USE_MATH_DEFINES /D_UNICODE /DUNICODE /DWIN32 /D_WINDOWS")
    set (CMAKE_CXX_FLAGS "${config.cppFlags} -D_CRT_SECURE_NO_WARNINGS -D_USE_MATH_DEFINES /D_UNICODE /DUNICODE /DWIN32 /D_WINDOWS")

    # If MSVC, disable some mismatch warnings.
    if (MSVC)
        set(CMAKE_C_FLAGS "${config.cFlags} /wd4267 /wd4244 /wd4804")
        set(CMAKE_CXX_FLAGS "${config.cppFlags} /wd4267 /wd4244 /wd4804")
    endif()
else()
    set (CMAKE_C_FLAGS "${config.cFlags}")
    set (CMAKE_CXX_FLAGS "${config.cppFlags} -Wno-sign-compare -std=c++11")
endif()

include_directories(include/)

set(PLATFORM_INDEPENDENT_HEADERS
    include/Fuzzer.hpp
    include/FuzzerData.hpp)

if(WIN32)
    include_directories("$ENV{VULKAN_SDK}/Include" platform/windows)
    SET(PLATFORM_SOURCES
        platform/windows/AppWindow.cpp
        platform/windows/AppWindow.hpp
        platform/windows/Main.cpp
        platform/windows/stdafx.h
        platform/windows/WindowConfig.hpp
        platform/windows/ExitCondition.hpp
        platform/windows/ExitCondition.cpp)
else()
    include_directories("/usr/include/vulkan")
endif()

# Find vulkan library
if(WIN32)
    set (VULKAN_LOADER_NAME "vulkan-${config.major}")
else()
    set (VULKAN_LOADER_NAME "vulkan")
endif()

find_library(VULKAN_LOADER NAMES ${config.vulkanLoaderName})

# Copy test runner
file(COPY "TestRunner.py" DESTINATION "${config.binaryFolder}")
file(COPY "simple-vert.spv" DESTINATION "${config.binaryFolder}")
file(COPY "simple-frag.spv" DESTINATION "${config.binaryFolder}")

<#list config.executables as executable>

# Create executable
# Link executable
if(WIN32)
    add_executable(${executable.name} WIN32 ${executable.source} ${config.platformSources} ${config.platformIndependentSources})
    target_link_libraries(${executable.name} ${config.vulkanLoader})
else()
    # TODO:: Add other deps for linux
    add_executable(${executable.name} ${executable.source} ${config.platformSources} ${config.platformIndependentSources})
    target_link_libraries(${executable.name} ${config.vulkanLoader})
endif()
</#list>
