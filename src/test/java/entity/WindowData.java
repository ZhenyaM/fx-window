package entity;

import javafx.stage.Window;
import lombok.Data;

/**
 * Created by Evgeniy on 21.01.2019.
 */
@Data
public class WindowData {

	private double x;
	private double y;
	private double width;
	private double height;

	public WindowData(final Window window) {
		this.x = window.getX();
		this.y = window.getY();
		this.width = window.getWidth();
		this.height = window.getHeight();
	}
}
