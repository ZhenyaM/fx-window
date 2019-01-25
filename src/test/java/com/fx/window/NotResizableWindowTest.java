package com.fx.window;

import com.fx.window.util.WindowProperties;
import org.junit.Test;

/**
 * Created by Evgeniy on 21.01.2019.
 */
public class NotResizableWindowTest extends WindowTest {

	public NotResizableWindowTest() {
		super(createProperties());
	}

	private static WindowProperties createProperties() {
		final WindowProperties windowProperties = new WindowProperties();
		windowProperties.setResizeEnabled(false);
		return windowProperties;
	}

	private void testResize(final String name) {
		drag(name, SHIFT, -SHIFT);
		assertResult(withoutShift());
		drag(name, -SHIFT, SHIFT);
		assertResult(withoutShift());
	}

	@Test
	@Override
	public void testTopResize() {
		testResize(TOP_ID);
	}

	@Test
	@Override
	public void testTopRightResize() {
		testResize(TOP_RIGHT_ID);
	}

	@Test
	@Override
	public void testRightResize() {
		testResize(RIGHT_ID);
	}

	@Test
	@Override
	public void testRightBottomResize() {
		testResize(RIGHT_BOTTOM_ID);
	}

	@Test
	@Override
	public void testBottomResize() {
		testResize(BOTTOM_ID);
	}

	@Test
	@Override
	public void testLeftBottomResize() {
		testResize(LEFT_BOTTOM_ID);
	}

	@Test
	@Override
	public void testLeftResize() {
		testResize(LEFT_ID);
	}

	@Test
	@Override
	public void testLeftTopResize() {
		testResize(LEFT_TOP_ID);
	}
}
