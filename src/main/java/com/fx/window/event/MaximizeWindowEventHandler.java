package com.fx.window.event;

import com.fx.window.util.ViewUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;

/**
 * @author Evgeniy
 */
public class MaximizeWindowEventHandler implements EventHandler<ActionEvent> {

	private final Stage window;

	public MaximizeWindowEventHandler(final Stage window) {
		this.window = window;
	}

	@Override
	public void handle(final ActionEvent event) {
		if (this.window.isMaximized()) {
			dropMaximized(-1, -1);
		} else {
			this.window.setMaximized(true);
		}
	}

	public void dropMaximized(final double x, final double y) {
		this.window.setMaximized(false);
		final Rectangle2D dimension = ViewUtils.getScreenDimension();
		setUpXLine(x, dimension);
		setUpYLine(y, dimension);
	}

	private void setUpXLine(final double x, final Rectangle2D dimension) {
		final double resX;
		if (x > -1) {
			resX = x - this.window.getWidth() / 2;
		} else if (dimension.getWidth() <= this.window.getWidth()) {
			resX = 0;
		} else  {
			resX = (dimension.getWidth() - this.window.getWidth()) / 2;
		}
		this.window.setX(resX);
	}

	private void setUpYLine(final double y, final Rectangle2D dimension) {
		final double resY;
		if (y > -1) {
			resY = y - 7;
		} else if (dimension.getHeight() <= this.window.getHeight()) {
			resY = 0;
		} else  {
			resY = (dimension.getHeight() - this.window.getHeight()) / 2;
		}
		this.window.setY(resY);
	}
}
