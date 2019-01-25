package com.fx.window.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * @author Evgeniy
 */
public class MinimizeWindowEventHandler implements EventHandler<ActionEvent> {

	private final Stage window;

	public MinimizeWindowEventHandler(final Stage window) {
		this.window = window;
	}

	@Override
	public void handle(final ActionEvent event) {
		this.window.setIconified(true);
	}
}
