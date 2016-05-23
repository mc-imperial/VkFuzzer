<#noparse>
cmake_minimum_required (VERSION 2.6)
project (VulkanPrograms)

set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${CMAKE_SOURCE_DIR}/cmake")

# The MAJOR number of the version we're building, used in naming
# vulkan-(major).dll (and other files).
set(MAJOR "1")

# Add compile flags and defines
if(WIN32)
    set (CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -D_CRT_SECURE_NO_WARNINGS -D_USE_MATH_DEFINES /D_UNICODE /DUNICODE /DWIN32 /D_WINDOWS")
    set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -D_CRT_SECURE_NO_WARNINGS -D_USE_MATH_DEFINES /D_UNICODE /DUNICODE /DWIN32 /D_WINDOWS")

    # If MSVC, disable some mismatch warnings.
    if (MSVC)
        set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} /wd4267 /wd4244 /wd4804")
        set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} /wd4267 /wd4244 /wd4804")
    endif()
else()
    set (CMAKE_C_FLAGS "${CMAKE_C_FLAGS}")
    set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wno-sign-compare -std=c++11")
endif()
</#noparse>

<#if !config.isEverything()>
    <#noparse>
    set (CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -D_FUZZER_COMPUTE_ONLY_")
    set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -D_FUZZER_COMPUTE_ONLY_")
    </#noparse>
</#if>

include_directories(include/)


<#if config.isEverything()>
if(UNIX)
find_package(XCB REQUIRED)
endif()

set(TYPE WIN32)
</#if>

<#noparse>
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
    include_directories("$ENV{VULKAN_SDK}/include" platform/linux)
    SET(PLATFORM_SOURCES
        platform/linux/Main.cpp
        platform/linux/WindowConfig.hpp
        platform/linux/ExitCondition.hpp
        platform/linux/ExitCondition.cpp)
</#noparse>
    <#if config.isEverything()>
        SET(PLATFORM_SOURCES
            <#noparse>
            ${PLATFORM_SOURCES}
            </#noparse>
            platform/linux/AppWindow.cpp
            platform/linux/AppWindow.hpp)
    </#if>
<#noparse>
endif()

# Find vulkan library
if(WIN32)
    set (VULKAN_LOADER_NAME "vulkan-${MAJOR}")
else()
    set (VULKAN_LOADER_NAME "vulkan")
endif()

find_library(VULKAN_LOADER NAMES ${VULKAN_LOADER_NAME} HINTS "$ENV{VULKAN_SDK}/lib")

# Copy test runner and shaders
file(COPY "TestRunner.py" DESTINATION "${CMAKE_BINARY_DIR}")
file(COPY "simple-vert.spv" DESTINATION "${CMAKE_BINARY_DIR}")
file(COPY "simple-frag.spv" DESTINATION "${CMAKE_BINARY_DIR}")

</#noparse>

<#list config.executables as executable>

# Create executable
# Link executable
if(WIN32)
    add_executable(${executable.name} <#noparse>${TYPE}</#noparse> ${executable.source} <#noparse>${PLATFORM_SOURCES} ${PLATFORM_INDEPENDENT_HEADERS}</#noparse>)
    target_link_libraries(${executable.name} <#noparse>${VULKAN_LOADER} </#noparse>)
else()
    add_executable(${executable.name} ${executable.source} <#noparse>${PLATFORM_SOURCES} ${PLATFORM_INDEPENDENT_HEADERS}</#noparse>)
    target_link_libraries(${executable.name} <#noparse>${VULKAN_LOADER} pthread</#noparse>)
    <#if config.everything>
    target_link_libraries(${executable.name} <#noparse>${XCB_LIBRARIES}</#noparse>)
    </#if>
endif()

<#--# Set target properties-->
<#--set_target_properties(${executable.name}-->
    <#--PROPERTIES-->
    <#--<#noparse>-->
    <#--ARCHIVE_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/lib"-->
    <#--LIBRARY_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/lib"-->
    <#--RUNTIME_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/bin")-->
    <#--</#noparse>-->

</#list>
