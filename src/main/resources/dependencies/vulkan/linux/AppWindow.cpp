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

#include <iostream>

#include "AppWindow.hpp"

namespace fuzzer
{
namespace display
{

AppWindow::AppWindow(const WindowConfigPtr &config): _run(true), _config(config)
{
    _initConnection();
    _initWindow();
}

AppWindow::~AppWindow()
{
    xcb_destroy_window(_config->connection, _config->window);
    xcb_disconnect(_config->connection);
}

void AppWindow::showAndloopForEver(const ExitConditionPtr &exitCondition)
{
    xcb_flush(_config->connection);

    while(_run)
    {
        xcb_generic_event_t *event = xcb_poll_for_event(_config->connection);

        if(event)
        {
            _handleEvent(event);
            free(event);
        }

        if(exitCondition->isFinished())
        {
            _run = false;            
        }
    }
}

void AppWindow::_initConnection()
{
    // Create connection with a given preferred number
	int preferredScreenNumber = 0;
	_config->connection = xcb_connect(NULL, &preferredScreenNumber);
	if (_config->connection == NULL) {
		std::cout << "Cannot find a compatible Vulkan ICD.\n";
		exit(-1);
	}

	// Create screen
	const xcb_setup_t *setup = xcb_get_setup(_config->connection);
	xcb_screen_iterator_t it = xcb_setup_roots_iterator(setup);
	while (preferredScreenNumber-- > 0)
	{
		xcb_screen_next(&it);
	}

	_config->screen = it.data;
}

void AppWindow::_initWindow()
{
    uint32_t value_mask, value_list[32];

	_config->window = xcb_generate_id(_config->connection);

	value_mask = XCB_CW_BACK_PIXEL | XCB_CW_EVENT_MASK;
	value_list[0] = _config->screen->black_pixel;
	value_list[1] = XCB_EVENT_MASK_KEY_RELEASE |
					XCB_EVENT_MASK_EXPOSURE;

	xcb_create_window(_config->connection,
			XCB_COPY_FROM_PARENT,
			_config->window,_config->screen->root,
			0, 0, _config->width, _config->height, 0,
			XCB_WINDOW_CLASS_INPUT_OUTPUT,
			_config->screen->root_visual,
			value_mask, value_list);

	/* Magic code that will send notification when window is destroyed */
	xcb_intern_atom_cookie_t cookie = xcb_intern_atom(_config->connection, 1, 12,
													  "WM_PROTOCOLS");
	xcb_intern_atom_reply_t* reply = xcb_intern_atom_reply(_config->connection, cookie, 0);

	xcb_intern_atom_cookie_t cookie2 = xcb_intern_atom(_config->connection, 0, 16, "WM_DELETE_WINDOW");
	_config->atomWmDeleteWindow = xcb_intern_atom_reply(_config->connection, cookie2, 0);

	xcb_change_property(_config->connection, XCB_PROP_MODE_REPLACE,
			_config->window, (*reply).atom, 4, 32, 1,
						&(*_config->atomWmDeleteWindow).atom);
	free(reply);

	xcb_map_window(_config->connection, _config->window);

	// Force the x/y coordinates to 100,100 results are identical in consecutive runs
	const uint32_t coords[] = {100,  100};
	xcb_configure_window(_config->connection, _config->window,
						 XCB_CONFIG_WINDOW_X | XCB_CONFIG_WINDOW_Y, coords);
	xcb_flush(_config->connection);

	xcb_generic_event_t *event = NULL;
	while ((event = xcb_wait_for_event(_config->connection)))
	{
	   if((event->response_type & ~0x80) == XCB_EXPOSE)
	   {
		   break;
	   }
	}
}

void AppWindow::_handleEvent(const xcb_generic_event_t *event)
{
    uint8_t eventCode = event->response_type & 0x7f;
	switch (eventCode)
	{
		case XCB_EXPOSE:
			// TODO: Resize window
			break;
		case XCB_CLIENT_MESSAGE:
			if((*(xcb_client_message_event_t*)event).data.data32[0] ==
			   (*_config->atomWmDeleteWindow).atom)
			{
				_run = false;
			}
			break;
		case XCB_KEY_RELEASE:
			{
				const xcb_key_release_event_t *key =
					(const xcb_key_release_event_t *)event;

				switch (key->detail)
				{
					case 0x9:
						// Escape key
						_run = false;
						break;
					default:
						break;
				}
			}
			break;
		default:
			break;
	}
}

} // namespace display
} // namespace fuzzer
