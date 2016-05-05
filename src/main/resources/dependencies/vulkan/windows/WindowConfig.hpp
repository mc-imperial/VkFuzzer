#ifndef _FUZZER_DISPLAY_WINDOWS_WINDOWCONFIG_HPP_
#define _FUZZER_DISPLAY_WINDOWS_WINDOWCONFIG_HPP_

#include <memory>

#include "stdafx.h"

namespace fuzzer
{
namespace display
{

struct WindowConfig
{
	HWND window;
	const int  nCmdShow;
	const uint32_t width;
	const uint32_t height;
	const HINSTANCE instance;

	WindowConfig(const int nCmdShow, const uint32_t width,
			const uint32_t height, const HINSTANCE instance):
				nCmdShow(nCmdShow), width(width),
				height(height), instance(instance)
	{
	}
};

typedef std::shared_ptr<WindowConfig> WindowConfigPtr;

} // namespace display
} // namespace fuzzer

#endif /* _FUZZER_DISPLAY_WINDOWS_WINDOWCONFIG_HPP_ */