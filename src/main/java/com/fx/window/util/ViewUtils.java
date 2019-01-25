package com.fx.window.util;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;


/**
 * @author Evgeniy
 */
public class ViewUtils {

	private static final Rectangle2D SIZE = initScreenDimension();

	private ViewUtils() {
		throw new UnsupportedOperationException();
	}

	private static Rectangle2D initScreenDimension() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		return new Rectangle2D(0, 0, width, height);
	}

	public static void runOnFxThread(final Runnable runnable) {
		if (Platform.isFxApplicationThread()) {
			runnable.run();
		} else {
			Platform.runLater(runnable);
		}
	}

	public static void clear(final Canvas canvas) {
		final GraphicsContext context = canvas.getGraphicsContext2D();
		context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public static Rectangle2D getScreenDimension() {
		return SIZE;
	}
}
