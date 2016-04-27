/*
* Vulkan Samples
*
* Copyright (C) 2015-2016 Valve Corporation
* Copyright (C) 2015-2016 LunarG, Inc.
*
* Permission is hereby granted, free of charge, to any person obtaining a
* copy of this software and associated documentation files (the "Software"),
* to deal in the Software without restriction, including without limitation
* the rights to use, copy, modify, merge, publish, distribute, sublicense,
* and/or sell copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included
* in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL
* THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
* DEALINGS IN THE SOFTWARE.
*/

#include <vulkan/vulkan.h>
#include <cassert>
#include <cstring>
#include <vector>
#include <iostream>
#include <cstdlib>
#include <ctime>
<#--#include <SDL.h>-->
<#--#include <SDL_syswm.h>-->

<#--SDL_Window *window;-->
<#--SDL_SysWMinfo windowInfo;-->

<#--// Initializes SDL-->
<#--SDL_bool initSDL()-->
<#--{-->
    <#--SDL_Init(SDL_INIT_VIDEO);-->

    <#--window = SDL_CreateWindow("VulkanProgram", SDL_WINDOWPOS_UNDEFINED,-->
            <#--SDL_WINDOWPOS_UNDEFINED, 0, 0, SDL_WINDOW_HIDDEN);-->

    <#--if (window == NULL)-->
    <#--{-->
        <#--std::cerr << "Could not initialize window" << std::endl;-->
        <#--return SDL_FALSE;-->
    <#--}-->

    <#--SDL_SysWMinfo windowInfo;-->

    <#--return SDL_GetWindowWMInfo(window, &windowInfo);-->
<#--}-->

<#--// Shutsdown SDL-->
<#--void shutDownSDL()-->
<#--{-->
    <#--SDL_DestroyWindow(window);-->
    <#--SDL_Quit();-->
<#--}-->

struct FuzzerLogicalDevice
{
    VkDevice device;
    uint32_t queueFamilyIndex;
    uint32_t maxQueueCount;
    bool supportsGraphics;
    bool supportsCompute;
    bool supportsTransfer;
    bool supportsSparseBinding;
};

int EXIT_INVALID_CODE_GENERATION = -5;
int EXIT_RUN_OUT_OF_MEMORY = -6;

int main(int argc, char *argv[])
{
    // Set seed for random operations
    srand(static_cast<unsigned>(time(0)));

    <#--// Init SDL-->
    <#--if (!initSDL())-->
    <#--{-->
        <#--return 1;-->
    <#--}-->

${config.body}

    <#--shutDownSDL();-->

    return 0;
}
