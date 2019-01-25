package com.fx.window.util;

import com.fx.window.controller.MenuBarController;
import com.fx.window.controller.WindowController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import lombok.Data;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;

/**
 * Builder for simple configuration of custom window
 *
 * Created by Evgeniy on 25.01.2019.
 */
@Getter
public class StageBuilder {

	private static final String PATH = "/fxml/window-frame.fxml";

	/**
	 * Stage which will be decorated. If not specified it will be created
	 */
	protected Stage stage;
	/**
	 * Path to template. Need for customizations of view
	 */
	protected String templatePath;
	/**
	 * Customized loader. If not specified it will be created
	 */
	protected FXMLLoader loader;
	/**
	 * Content pane
	 */
	protected Parent content;
	/**
	 * Properties for window customization. This properties used at once when
	 * panes get stage
	 */
	protected WindowProperties windowProperties;

	public StageBuilder setStage(final Stage stage) {
		this.stage = stage;
		return this;
	}

	public StageBuilder setTemplatePath(final String templatePath) {
		this.templatePath = templatePath;
		return this;
	}

	public StageBuilder setLoader(final FXMLLoader loader) {
		this.loader = loader;
		return this;
	}

	public StageBuilder setContent(final Parent content) {
		this.content = content;
		return this;
	}

	public StageBuilder setWindowProperties(final WindowProperties windowProperties) {
		this.windowProperties = windowProperties;
		return this;
	}

	public BuildResult build() throws IOException {
		if (this.stage == null) {
			this.stage = new Stage();
		}
		if (this.stage.getStyle() != StageStyle.UNDECORATED) {
			this.stage.initStyle(StageStyle.UNDECORATED);
		}
		if (this.templatePath == null) {
			this.templatePath = PATH;
		}
		if (this.windowProperties == null) {
			this.windowProperties = new WindowProperties();
		}
		if (this.loader == null) {
			this.loader = new FXMLLoader();
			this.loader.setControllerFactory(getControllerCallback());
		}
		try (final InputStream stream = getClass().getResourceAsStream(this.templatePath)) {
			final Parent load = loader.load(stream);
			this.stage.setScene(new Scene(load, 800, 600));
		}
		final WindowController controller = this.loader.getController();
		if (this.content != null) {
			controller.setContentPane(this.content);
		}
		return new BuildResult(this.stage, controller);
	}

	private Callback<Class<?>, Object> getControllerCallback() {
		return param -> {
			try {
				if (param.equals(WindowController.class)) {
					return new WindowController(this.windowProperties);
				} else if (param.equals(MenuBarController.class)) {
					return new MenuBarController(this.windowProperties);
				} else {
					return param.newInstance();
				}
			} catch (InstantiationException | IllegalAccessException e) {
				throw new IllegalStateException(new LoadException("Can not construct controller " + param.getName(), e));
			}
		};
	}

	@Data
	public static class BuildResult {
		private final Stage stage;
		private final WindowController controller;
	}
}
