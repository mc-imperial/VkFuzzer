#ifndef _FUZZER_DISPLAY_LINUX_WINDOWCONFIG_HPP_
#define _FUZZER_DISPLAY_LINUX_WINDOWCONFIG_HPP_

#ifndef _FUZZER_COMPUTE_ONLY_
#include <xcb/xcb.h>
#endif

#include <memory>

namespace fuzzer
{
namespace display
{

struct WindowConfig
{
#ifndef _FUZZER_COMPUTE_ONLY_
    xcb_connection_t *connection;
	xcb_screen_t *screen;
	xcb_window_t window;
	xcb_intern_atom_reply_t *atomWmDeleteWindow;
#endif
	const uint32_t width;
	const uint32_t height;

	WindowConfig(const uint32_t width, const uint32_t height):
	        width(width), height(height)
	{
    }
};

typedef std::shared_ptr<WindowConfig> WindowConfigPtr;

} // namespace display
} // namespace fuzzer

#endif /* _FUZZER_DISPLAY_LINUX_WINDOWCONFIG_HPP_ */
