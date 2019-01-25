package com.fx.window;

import com.fx.window.util.WindowProperties;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Evgeniy on 21.01.2019.
 */
public class NotDraggableWindowTest extends WindowTest {

	public NotDraggableWindowTest() {
		super(createProperties());
	}

	private static WindowProperties createProperties() {
		final WindowProperties windowProperties = new WindowProperties();
		windowProperties.setMoveEnabled(false);
		return windowProperties;
	}

	@Test
	@Override
	public void testMove() {
		drag(MOVE_ID, SHIFT, 0);
		assertResult(withoutShift());
		drag(MOVE_ID, -SHIFT, 0);
		assertResult(withoutShift());
	}

	@Test
	@Override
	public void testDragMaximize() throws InterruptedException {
		moveTo(MOVE_ID).drag(MouseButton.PRIMARY).moveTo(100, 0).release(MouseButton.PRIMARY);
		Assert.assertFalse(((Stage) targetWindow()).isMaximized());
	}
}
