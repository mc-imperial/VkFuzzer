#include "AppWindow.hpp"

namespace fuzzer
{
namespace display
{

namespace
{
// Window event callback
LRESULT CALLBACK WindowProc(HWND hwnd, UINT uMsg, WPARAM wParam, LPARAM lParam)
{
	switch (uMsg)
	{
	case WM_DESTROY:
        exit(EXIT_SUCCESS);
		return 0;
	case WM_PAINT:
		// To do
		return 0;
	}

	return DefWindowProc(hwnd, uMsg, wParam, lParam);
}

const wchar_t NAME[] = L"VulkanApp";
}

AppWindow::AppWindow(const WindowConfigPtr &config): _config(config)
{
	_createWindow();
}

AppWindow::~AppWindow()
{
}

void AppWindow::showAndloopForEver(const ExitConditionPtr &exitCondition)
{
	ShowWindow(_config->window, _config->nCmdShow);
	MSG msg = {};
	while (GetMessage(&msg, NULL, 0, 0))
	{
		if (exitCondition->isFinished())
		{
			break;
		}

		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}
}

// Creates a window
void AppWindow::_createWindow()
{
	// Register window class
	WNDCLASS windowClass;
	memset(&windowClass, 0, sizeof(windowClass));

	windowClass.lpfnWndProc = WindowProc;
	windowClass.hInstance = _config->instance;
	windowClass.lpszClassName = NAME;
	RegisterClass(&windowClass);

	// Create window
	_config->window = CreateWindowEx(
		0, NAME, NAME,
		WS_OVERLAPPEDWINDOW,
		CW_USEDEFAULT, CW_USEDEFAULT, _config->width, _config->height,
		NULL, NULL, _config->instance, NULL);

	// Exit if window could not be created
	if (_config->window == NULL)
	{
		exit(1);
	}
}
		
} // namespace display
} // namespace fuzzer