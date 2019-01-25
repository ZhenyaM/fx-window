package com.fx.window;

import com.fx.window.util.StageBuilder;
import com.fx.window.util.ViewUtils;
import com.fx.window.util.WindowProperties;
import entity.ResultData;
import entity.WindowData;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.testfx.framework.junit.ApplicationTest;

/**
 * Created by Evgeniy on 21.01.2019.
 */
public abstract class AbstractWindowTest extends ApplicationTest {

	private static final String CSS = "css/fx-window.css";

	protected static final String MOVE_ID = "#winMove";
	protected static final String TOP_ID = "#winResizeN";
	protected static final String TOP_RIGHT_ID = "#winResizeNE";
	protected static final String RIGHT_ID = "#winResizeE";
	protected static final String RIGHT_BOTTOM_ID = "#winResizeSE";
	protected static final String BOTTOM_ID = "#winResizeS";
	protected static final String LEFT_BOTTOM_ID = "#winResizeSW";
	protected static final String LEFT_ID = "#winResizeW";
	protected static final String LEFT_TOP_ID = "#winResizeNW";
	protected static final String MINIMIZE_ID = "#minimizeButton";
	protected static final String MAXIMIZE_ID = "#maximizeButton";
	protected static final String EXIT_ID = "#exitButton";
	protected static final String BUTTONS_BAR_ID = "#buttonsBar";
	protected static final int SHIFT = 20;

	private final WindowProperties properties;

	protected WindowData data;

	protected AbstractWindowTest(final WindowProperties properties) {
		this.properties = properties;
	}

	@Override
	public void start(final Stage stage) throws Exception {
		final StageBuilder.BuildResult result = new StageBuilder()
				.setStage(stage)
				.setWindowProperties(this.properties)
				.build();
		result.getStage().getScene().getStylesheets().add(CSS);
		result.getStage().show();
		targetWindow(result.getStage());
	}

	@Before
	public void setUp() {
		final Rectangle2D d = ViewUtils.getScreenDimension();
		targetWindow().setX(d.getWidth() / 4);
		targetWindow().setY(d.getHeight() / 4);
		targetWindow().setWidth(d.getWidth() / 2);
		targetWindow().setHeight(d.getHeight() / 2);
		this.data = new WindowData(this.targetWindow());
	}

	protected void assertResult(final ResultData result) {
		Assert.assertEquals(result.getX(), targetWindow().getX(), 1);
		Assert.assertEquals(result.getY(), targetWindow().getY(), 1);
		Assert.assertEquals(result.getWidth(), targetWindow().getWidth(), 1);
		Assert.assertEquals(result.getHeight(), targetWindow().getHeight(), 1);
	}

	protected ResultData withoutShift() {
		return new ResultData(this.data, 0, 0, 0, 0);
	}

	protected void drag(String name, double x, double y) {
		moveTo(name).drag(MouseButton.PRIMARY).moveBy(x, y).release(MouseButton.PRIMARY);
	}
}
