package entity;

import lombok.Data;

/**
 * Created by Evgeniy on 21.01.2019.
 */
@Data
public class ResultData {

	private double x;
	private double y;
	private double width;
	private double height;

	public ResultData(final WindowData data, final double x, final double y, final double width, final double height) {
		this.x = data.getX() + x;
		this.y = data.getY() + y;
		this.width = data.getWidth() + width;
		this.height = data.getHeight() + height;
	}
}
