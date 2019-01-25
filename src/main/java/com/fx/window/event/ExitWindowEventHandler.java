package com.fx.window.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * @author Evgeniy
 */
// todo: add ask frame on this action
public class ExitWindowEventHandler implements EventHandler<ActionEvent> {

	private final Stage window;

	public ExitWindowEventHandler(final Stage window) {
		this.window = window;
	}

	@Override
	public void handle(final ActionEvent event) {
		this.window.close();
	}
}
