#include <thread>

#include "stdafx.h"
#include "WindowConfig.hpp"
#include "AppWindow.hpp"

#define WIDTH 640
#define HEIGHT 480

using namespace fuzzer;

// Function declarations
void fuzz(const ExitConditionPtr &exitCondition,
		const display::WindowConfigPtr &config);

int WINAPI WinMain(HINSTANCE hInstance,
	HINSTANCE hPrevInstance,
	LPSTR lpCmdLine,
	int nCmdShow)
{
	// Initial setup
	ExitConditionPtr exitCondition = std::make_shared<ExitCondition>();
	display::WindowConfigPtr config =
			std::make_shared<display::WindowConfig>(nCmdShow, WIDTH, HEIGHT, hInstance);
	display::AppWindowPtr window =
			std::unique_ptr<display::AppWindow>(new display::AppWindow(config));
	
	// Start fuzzer in a thread of its own
	std::thread fuzzer(fuzz, std::ref(exitCondition), std::ref(config));

	// Loop until fuzzer is finished
	window->showAndloopForEver(exitCondition);

	// Make sure the fuzzer thread exits first before returning
	if (fuzzer.joinable())
	{
		fuzzer.join();
	}

	return 0;
}
