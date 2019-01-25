package com.fx.window;

import com.fx.window.util.ViewUtils;
import com.fx.window.util.WindowProperties;
import entity.ResultData;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Evgeniy on 21.01.2019.
 */
public class WindowTest extends AbstractWindowTest {

	public WindowTest() {
		this(new WindowProperties());
	}

	protected WindowTest(final WindowProperties properties) {
		super(properties);
	}

	@Test
	public void testMove() {
		final ResultData result = new ResultData(this.data, SHIFT, 0, 0, 0);
		drag(MOVE_ID, SHIFT, 0);
		assertResult(result);
		drag(MOVE_ID, -SHIFT, 0);
		assertResult(withoutShift());
	}

	@Test
	public void testTopResize() {
		final ResultData result = new ResultData(this.data, 0, -SHIFT, 0, SHIFT);
		drag(TOP_ID, 0, -SHIFT);
		assertResult(result);
		drag(TOP_ID, 0, SHIFT);
		assertResult(withoutShift());
	}

	@Test
	public void testTopRightResize() {
		final ResultData result = new ResultData(this.data, 0, -SHIFT, SHIFT, SHIFT);
		drag(TOP_RIGHT_ID, SHIFT, -SHIFT);
		assertResult(result);
		drag(TOP_RIGHT_ID, -SHIFT, SHIFT);
		assertResult(withoutShift());
	}

	@Test
	public void testRightResize() {
		final ResultData result = new ResultData(this.data, 0, 0, SHIFT, 0);
		drag(RIGHT_ID, SHIFT, 0);
		assertResult(result);
		drag(RIGHT_ID, -SHIFT, 0);
		assertResult(withoutShift());
	}

	@Test
	public void testRightBottomResize() {
		final ResultData result = new ResultData(this.data, 0, 0, SHIFT, SHIFT);
		drag(RIGHT_BOTTOM_ID, SHIFT, SHIFT);
		assertResult(result);
		drag(RIGHT_BOTTOM_ID, -SHIFT, -SHIFT);
		assertResult(withoutShift());
	}

	@Test
	public void testBottomResize() {
		final ResultData result = new ResultData(this.data, 0, 0, 0, SHIFT);
		drag(BOTTOM_ID, 0, SHIFT);
		assertResult(result);
		drag(BOTTOM_ID, 0, -SHIFT);
		assertResult(withoutShift());
	}

	@Test
	public void testLeftBottomResize() {
		final ResultData result = new ResultData(this.data, -SHIFT, 0, SHIFT, SHIFT);
		drag(LEFT_BOTTOM_ID, -SHIFT, SHIFT);
		assertResult(result);
		drag(LEFT_BOTTOM_ID, SHIFT, -SHIFT);
		assertResult(withoutShift());
	}

	@Test
	public void testLeftResize() {
		final ResultData result = new ResultData(this.data, -SHIFT, 0, SHIFT, 0);
		drag(LEFT_ID, -SHIFT, 0);
		assertResult(result);
		drag(LEFT_ID, SHIFT, 0);
		assertResult(withoutShift());
	}

	@Test
	public void testLeftTopResize() {
		final ResultData result = new ResultData(this.data, -SHIFT, -SHIFT, SHIFT, SHIFT);
		drag(LEFT_TOP_ID, -SHIFT, -SHIFT);
		assertResult(result);
		drag(LEFT_TOP_ID, SHIFT, SHIFT);
		assertResult(withoutShift());
	}

	@Test
	public void testIconified() throws InterruptedException {
		try {
			moveTo(MINIMIZE_ID).clickOn(MouseButton.PRIMARY);
			Assert.assertTrue(((Stage) targetWindow()).isIconified());
		} finally {
			ViewUtils.runOnFxThread(() -> ((Stage) targetWindow()).setIconified(false));
			Thread.sleep(300);
		}
	}

	@Test
	public void testMaximize() {
		moveTo(MAXIMIZE_ID).clickOn(MouseButton.PRIMARY);
		Assert.assertTrue(((Stage) targetWindow()).isMaximized());
		moveTo(MAXIMIZE_ID).clickOn(MouseButton.PRIMARY);
		Assert.assertFalse(((Stage) targetWindow()).isMaximized());
	}

	@Test
	public void testDragMaximize() throws InterruptedException {
		final double x = this.data.getX() + this.data.getWidth() / 2;
		moveTo(MOVE_ID).drag(MouseButton.PRIMARY).moveTo(x, 0).release(MouseButton.PRIMARY);
		try {
			Assert.assertTrue(((Stage) targetWindow()).isMaximized());
		} finally {
			moveTo(MOVE_ID).drag(MouseButton.PRIMARY).moveTo(x, this.data.getY()).release(MouseButton.PRIMARY);
		}
		Assert.assertFalse(((Stage) targetWindow()).isMaximized());
	}

	@Test
	public void testClose() {
		moveTo(EXIT_ID).clickOn(MouseButton.PRIMARY);
		Assert.assertFalse(targetWindow().isShowing());
	}
}
