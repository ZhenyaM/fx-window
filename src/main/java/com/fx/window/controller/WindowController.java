package com.fx.window.controller;

import com.fx.window.event.DragWindowEventHandler;
import com.fx.window.event.MaximizeWindowEventHandler;
import com.fx.window.event.ResizeEventHandler;
import com.fx.window.util.WindowProperties;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import lombok.Data;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Evgeniy on 01.02.2018.
 */
@Data
public class WindowController implements Initializable {

	@FXML
	private AnchorPane rootPane;
	@FXML
	private Label windowTitle;
	@FXML
	private Region winResizeNW;
	@FXML
	private Region winResizeN;
	@FXML
	private Region winResizeNE;
	@FXML
	private Region winResizeE;
	@FXML
	private Region winResizeSE;
	@FXML
	private Region winResizeS;
	@FXML
	private Region winResizeSW;
	@FXML
	private Region winResizeW;

	@FXML
	private MenuBarController menuBarController;

	private final WindowProperties properties;

	public void setContentPane(final Parent content) {
		this.rootPane.getChildren().add(content);
		AnchorPane.setRightAnchor(content, winResizeE.getWidth());
		AnchorPane.setLeftAnchor(content, winResizeW.getWidth());
		AnchorPane.setBottomAnchor(content, winResizeN.getHeight());
		AnchorPane.setTopAnchor(content, winResizeS.getHeight() +
				this.menuBarController.getMenuBarContainer().getHeight());
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		this.rootPane.sceneProperty().addListener((sceneObservable, oldScene, newScene) -> {
			if (newScene != null) {
				newScene.windowProperty().addListener((windowObservable, oldWindow, newWindow) -> {
					final Stage window = (Stage) newWindow;
					this.initWindowManipulations(window);
					this.initTitle(window);
				});
			}
		});
	}

	private void initTitle(final Stage window) {
		if (this.properties.isIconLabelEnabled()) {
			window.titleProperty().bind(this.menuBarController.getMenuIconLabel().textProperty());
		}
		double leftWidth = 0;
		double rightWidth = 0;
		if (this.properties.isIconEnabled()) {
			final ImageView menuIcon = this.menuBarController.getMenuIcon();
			leftWidth += menuIcon.getFitWidth();
		}
		if (this.properties.isIconLabelEnabled()) {
			final Label menuLabel = this.menuBarController.getMenuIconLabel();
			leftWidth += menuLabel.getWidth();
		}
		if (this.properties.isButtonsBarEnabled()) {
			final ButtonBar buttonsBar = this.menuBarController.getButtonsBar();
			rightWidth += buttonsBar.getWidth();
		}
		final double anchor = Math.max(leftWidth, rightWidth);
		AnchorPane.setLeftAnchor(this.windowTitle, anchor);
		AnchorPane.setRightAnchor(this.windowTitle, anchor);
	}

	private void initWindowManipulations(final Stage window) {
		final MaximizeWindowEventHandler maximizeWindowEventHandler = getMaximizeWindowEventHandler(window);

		if (this.properties.isMoveEnabled()) {
			initMoveRegion(window, maximizeWindowEventHandler);
		} else {
			this.menuBarController.getWinMove().setCursor(Cursor.DEFAULT);
		}

		if (this.properties.isResizeEnabled()) {
			initResize(window, maximizeWindowEventHandler);
		} else {
			this.winResizeN.setCursor(Cursor.DEFAULT);
			this.winResizeNW.setCursor(Cursor.DEFAULT);
			this.winResizeW.setCursor(Cursor.DEFAULT);
			this.winResizeSW.setCursor(Cursor.DEFAULT);
			this.winResizeS.setCursor(Cursor.DEFAULT);
			this.winResizeSE.setCursor(Cursor.DEFAULT);
			this.winResizeE.setCursor(Cursor.DEFAULT);
			this.winResizeNE.setCursor(Cursor.DEFAULT);
		}
	}

	private MaximizeWindowEventHandler getMaximizeWindowEventHandler(final Stage window) {
		final ObjectProperty<EventHandler<ActionEvent>> property =
				this.menuBarController.getMaximizeButton().onActionProperty();
		if (property != null) {
			final EventHandler<ActionEvent> handler = property.get();
			if (handler instanceof MaximizeWindowEventHandler) {
				return (MaximizeWindowEventHandler) handler;
			}
		}
		return new MaximizeWindowEventHandler(window);
	}

	private void initMoveRegion(final Stage window, final MaximizeWindowEventHandler maximizeWindowEventHandler) {
		final EventHandler<MouseEvent> dragHandler = new DragWindowEventHandler(window, maximizeWindowEventHandler);
		final Region winMove = this.menuBarController.getWinMove();
		setHandler(dragHandler, winMove);
		winMove.setOnMouseReleased(dragHandler);
		winMove.setOnMouseClicked(dragHandler);
	}

	private void initResize(final Stage window, final MaximizeWindowEventHandler handler) {
		setHandler(new ResizeEventHandler(window, handler, 0, 1, 0, -1), this.winResizeN);
		setHandler(new ResizeEventHandler(window, handler, 1, 1, -1, -1), this.winResizeNW);
		setHandler(new ResizeEventHandler(window, handler, 1, 0, -1, 0), this.winResizeW);
		setHandler(new ResizeEventHandler(window, handler, 1, 0, -1, 1), this.winResizeSW);
		setHandler(new ResizeEventHandler(window, handler, 0, 0, 0, 1), this.winResizeS);
		setHandler(new ResizeEventHandler(window, handler, 0, 0, 1, 1), this.winResizeSE);
		setHandler(new ResizeEventHandler(window, handler, 0, 0, 1, 0), this.winResizeE);
		setHandler(new ResizeEventHandler(window, handler, 0, 1, 1, -1), this.winResizeNE);
	}

	private void setHandler(final EventHandler<MouseEvent> handler, final Parent region) {
		region.setOnMouseDragged(handler);
		region.setOnMousePressed(handler);
	}
}
