#ifndef _FUZZER_DISPLAY_LINUX_WINDOWCONFIG_HPP_
#define _FUZZER_DISPLAY_LINUX_WINDOWCONFIG_HPP_

#include <xcb/xcb.h>
#include <memory>

namespace fuzzer
{
namespace display
{

struct WindowConfig
{
    xcb_connection_t *connection;
	xcb_screen_t *screen;
	xcb_window_t window;
	xcb_intern_atom_reply_t *atomWmDeleteWindow;
	const uint32_t width;
	const uint32_t height;
};

typedef std::shared_ptr<WindowConfig> WindowConfigPtr;

} // namespace display
} // namespace fuzzer

#endif /* _FUZZER_DISPLAY_LINUX_WINDOWCONFIG_HPP_ */
