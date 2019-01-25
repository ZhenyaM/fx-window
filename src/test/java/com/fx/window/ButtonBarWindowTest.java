package com.fx.window;

import com.fx.window.util.WindowProperties;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.testfx.service.query.NodeQuery;

/**
 * Created by Evgeniy on 21.01.2019.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		ButtonBarWindowTest.CloseButtonWindowTest.class,
		ButtonBarWindowTest.MinimizeWindowTest.class,
		ButtonBarWindowTest.MaximizeWindowTest.class,
		ButtonBarWindowTest.NoButtonsWindowTest.class
})
public class ButtonBarWindowTest {

	public static class CloseButtonWindowTest extends WindowTest {

		public CloseButtonWindowTest() {
			super(createProperties());
		}

		private static WindowProperties createProperties() {
			final WindowProperties windowProperties = new WindowProperties();
			windowProperties.setExitButtonEnabled(false);
			return windowProperties;
		}

		@Test
		@Override
		public void testClose() {
			testButton(lookup(EXIT_ID));
		}
	}

	public static class MinimizeWindowTest extends WindowTest {

		public MinimizeWindowTest() {
			super(createProperties());
		}

		private static WindowProperties createProperties() {
			final WindowProperties windowProperties = new WindowProperties();
			windowProperties.setMinimizeButtonEnabled(false);
			return windowProperties;
		}

		@Test
		@Override
		public void testIconified() {
			testButton(lookup(MINIMIZE_ID));
		}
	}

	public static class MaximizeWindowTest extends WindowTest {

		public MaximizeWindowTest() {
			super(createProperties());
		}

		private static WindowProperties createProperties() {
			final WindowProperties windowProperties = new WindowProperties();
			windowProperties.setMaximizeButtonEnabled(false);
			return windowProperties;
		}

		@Test
		@Override
		public void testMaximize() {
			testButton(lookup(MAXIMIZE_ID));
		}
	}

	public static class NoButtonsWindowTest extends WindowTest {

		public NoButtonsWindowTest() {
			super(createProperties());
		}

		private static WindowProperties createProperties() {
			final WindowProperties windowProperties = new WindowProperties();
			windowProperties.setButtonsBarEnabled(false);
			return windowProperties;
		}

		@Test
		@Override
		public void testMaximize() {
			testButton(lookup(MAXIMIZE_ID));
			testButton(lookup(BUTTONS_BAR_ID));
		}

		@Test
		@Override
		public void testIconified() {
			testButton(lookup(MINIMIZE_ID));
			testButton(lookup(BUTTONS_BAR_ID));
		}

		@Test
		@Override
		public void testClose() {
			testButton(lookup(EXIT_ID));
			testButton(lookup(BUTTONS_BAR_ID));
		}
	}

	private static void testButton(NodeQuery q) {
		final Node node = q.query();
		Assert.assertFalse(node.isManaged());
		if (node instanceof Button) {
			Assert.assertFalse(((Button) node).getGraphic().isManaged());
		}
	}
}
