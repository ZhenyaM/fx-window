package com.fx.window.event;

import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Evgeniy
 */
public class DragWindowEventHandler extends ResizeEventHandler {

	public DragWindowEventHandler(final Stage window, final MaximizeWindowEventHandler maximizeWindowEventHandler) {
		super(window, maximizeWindowEventHandler, 1, 1, 0, 0);
	}

	@Override
	public void handle(final MouseEvent e) {
		final EventType<? extends MouseEvent> type = e.getEventType();
		if (type == MouseEvent.MOUSE_CLICKED && e.getClickCount() == 2) {
			this.maximizeWindowEventHandler.handle(null);
		} else {
			final double y = ((Node) e.getTarget()).getScene().getWindow().getY();
			if (type == MouseEvent.MOUSE_RELEASED && y <= 2) {
				this.maximizeWindowEventHandler.handle(null);
			} else {
				super.handle(e);
			}

		}
	}

}
