#ifndef _FUZZER_DISPLAY_WINDOWS_APP_WINDOW_HPP_
#define _FUZZER_DISPLAY_WINDOWS_APP_WINDOW_HPP_

#include <cstdint>

#include "stdafx.h"
#include "WindowConfig.hpp"
#include "ExitCondition.hpp"

namespace fuzzer
{
namespace display
{

class AppWindow
{
public:
	AppWindow(const WindowConfigPtr &config);
	~AppWindow();

	void showAndloopForEver(const ExitConditionPtr &exitCondition);
private:
	void _createWindow();

	const WindowConfigPtr _config;
};

typedef std::unique_ptr<AppWindow> AppWindowPtr;

} // namespace display
} // namespace fuzzer

#endif // _FUZZER_DISPLAY_WINDOWS_APP_WINDOW_HPP_