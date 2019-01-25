package com.fx.window.event;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Evgeniy
 */
public class ResizeEventHandler implements EventHandler<MouseEvent> {

	protected final Stage window;
	protected final MaximizeWindowEventHandler maximizeWindowEventHandler;

	private final double xMult;
	private final double yMult;
	private final double widthMult;
	private final double heightMult;

	private double x;
	private double y;

	public ResizeEventHandler(final Stage window,
							  final MaximizeWindowEventHandler maximizeWindowEventHandler,
							  final double xMult,
							  final double yMult,
							  final double widthMult,
							  final double heightMult) {
		this.window = window;
		this.maximizeWindowEventHandler = maximizeWindowEventHandler;
		this.xMult = xMult;
		this.yMult = yMult;
		this.widthMult = widthMult;
		this.heightMult = heightMult;
	}

	@Override
	public void handle(final MouseEvent e) {
		final EventType<? extends MouseEvent> type = e.getEventType();
		if (type == MouseEvent.MOUSE_PRESSED) {
			this.x = e.getScreenX();
			this.y = e.getScreenY();
		} else if (type == MouseEvent.MOUSE_DRAGGED) {
			dropMaximized(e);
			final double newX = e.getScreenX();
			final double newY = e.getScreenY();
			final double xShift = newX - this.x;
			final double yShift = newY - this.y;
			this.x = newX;
			this.y = newY;

			final double wX = this.window.getX() + this.xMult * xShift;
			final double wY = this.window.getY() + this.yMult * yShift;
			final double wWidth = this.window.getWidth() + this.widthMult * xShift;
			final double wHeight = this.window.getHeight() + this.heightMult * yShift;
			final double fWidth = Math.min(this.window.getMaxWidth(), Math.max(wWidth, this.window.getMinWidth()));
			final double fHeight = Math.min(this.window.getMaxHeight(), Math.max(wHeight, this.window.getMinHeight()));

			this.window.setX(wX);
			this.window.setY(wY);
			this.window.setWidth(fWidth);
			this.window.setHeight(fHeight);
			/*if (fWidth == wWidth) {
				this.window.setX(wX);
			}
			if (fHeight == wHeight) {
				this.window.setY(wY);
			}
			if (this.window.getX() < newX && newX < this.window.getX() + this.window.getWidth()) {
				this.window.setWidth(fWidth);
			}
			if (this.window.getY() < newY && newY < this.window.getY() + this.window.getHeight()) {
				this.window.setHeight(fHeight);
			}
*/		}
	}

	private void dropMaximized(final MouseEvent e) {
		if (this.window.isMaximized()) {
			this.maximizeWindowEventHandler.dropMaximized(e.getScreenX(), e.getScreenY());
		}
	}
}
