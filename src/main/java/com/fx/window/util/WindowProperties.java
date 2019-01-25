package com.fx.window.util;

import com.fx.window.event.ExitWindowEventHandler;
import com.fx.window.event.MaximizeWindowEventHandler;
import com.fx.window.event.MinimizeWindowEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import lombok.Data;

import java.util.function.Function;

/**
 * Created by Evgeniy on 21.01.2019.
 */
@Data
public class WindowProperties {

	private boolean iconEnabled = false;
	private boolean iconLabelEnabled = false;
	private boolean exitButtonEnabled = true;
	private boolean maximizeButtonEnabled = true;
	private boolean minimizeButtonEnabled = true;
	private Function<Stage, EventHandler<ActionEvent>> initExitFunction = ExitWindowEventHandler::new;
	private Function<Stage, EventHandler<ActionEvent>> initMaximizeFunction = MaximizeWindowEventHandler::new;
	private Function<Stage, EventHandler<ActionEvent>> initMinimizeFunction = MinimizeWindowEventHandler::new;

	private boolean moveEnabled = true;
	private boolean resizeEnabled = true;

	public void setButtonsBarEnabled(boolean enabled) {
		this.exitButtonEnabled = enabled;
		this.maximizeButtonEnabled = enabled;
		this.minimizeButtonEnabled = enabled;
	}

	public boolean isButtonsBarEnabled() {
		return this.exitButtonEnabled || this.maximizeButtonEnabled || this.minimizeButtonEnabled;
	}

}
