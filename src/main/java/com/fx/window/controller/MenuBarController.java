package com.fx.window.controller;

import com.fx.window.util.WindowProperties;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Evgeniy
 */
@Data
public class MenuBarController implements Initializable {

	@FXML
	private HBox menuBarContainer;
	@FXML
	private ImageView menuIcon;
	@FXML
	private Label menuIconLabel;
	@FXML
	private Region winMove;
	@FXML
	private ButtonBar buttonsBar;
	@FXML
	private Button minimizeButton;
	@FXML
	private Button exitButton;
	@FXML
	private Button maximizeButton;

	private final WindowProperties properties;

	public void setMinimizeImage(String path) throws IOException {
		setImage(this.minimizeButton, path);
	}

	public void setMinimizeImage(Image image) throws IOException {
		setImage(this.minimizeButton, image);
	}

	public void setMaximizeImage(String path) throws IOException {
		setImage(this.maximizeButton, path);
	}

	public void setMaximizeImage(Image image) throws IOException {
		setImage(this.maximizeButton, image);
	}

	public void setCloseImage(String path) throws IOException {
		setImage(this.exitButton, path);
	}

	public void setCloseImage(Image image) throws IOException {
		setImage(this.exitButton, image);
	}

	public void setIconImage(String path) throws IOException {
		this.menuIcon.setImage(loadIconImage(path));
	}

	public void setIconImage(Image image) throws IOException {
		this.menuIcon.setImage(image);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (this.properties.isButtonsBarEnabled()) {
			initButtonsHandlers();
		} else {
			this.buttonsBar.setManaged(false);
			hideButton(this.exitButton);
			hideButton(this.minimizeButton);
			hideButton(this.maximizeButton);
		}
	}

	protected void initButtonsHandlers() {
		this.winMove.sceneProperty().addListener((sceneObservable, oldScene, newScene) -> {
			if (newScene != null) {
				newScene.windowProperty().addListener((windowObservable, oldWindow, newWindow) -> {
					final Stage stage = (Stage) newWindow;
					if (this.properties.isExitButtonEnabled()) {
						this.exitButton.setOnAction(this.properties.getInitExitFunction().apply(stage));
					} else {
						hideButton(this.exitButton);
					}
					if (this.properties.isMinimizeButtonEnabled()) {
						this.minimizeButton.setOnAction(this.properties.getInitMinimizeFunction().apply(stage));
					} else {
						hideButton(this.minimizeButton);
					}
					if (this.properties.isMaximizeButtonEnabled()) {
						this.maximizeButton.setOnAction(this.properties.getInitMaximizeFunction().apply(stage));
					} else {
						hideButton(this.maximizeButton);
					}
				});
			}
		});
	}

	protected void hideButton(final Button element) {
		element.setManaged(false);
		if (element.getGraphic() != null) {
			element.getGraphic().setManaged(false);
		}
	}

	private void setImage(Button button, String path) throws IOException {
		final Image image = loadIconImage(path);
		setImage(button, image);
	}

	private Image loadIconImage(final String path) throws IOException {
		try (InputStream imageStream = this.getClass().getResourceAsStream(path)) {
			return new Image(imageStream, 10, 10, true, true);
		}
	}

	private void setImage(final Button button, final Image image) {
		final ImageView view = new ImageView(image);
		button.setGraphic(view);
	}
}
