package knox.drawshapes;

import java.awt.Color;
import java.awt.Point;

public class Triangle extends Polygon {

	public Triangle(Point anchorpoint, int x2, int y2, int x3, int y3, Color color) {
		super(anchorpoint,new int[]{anchorpoint.x,anchorpoint.x+x2,anchorpoint.x+x2+x3},
				new int[]{anchorpoint.y,anchorpoint.y+y2,anchorpoint.y+y2+y3},
				3, true, color);
	}

}
