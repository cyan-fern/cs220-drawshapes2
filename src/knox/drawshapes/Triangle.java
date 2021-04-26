package knox.drawshapes;

import java.awt.Color;

public class Triangle extends Polygon {

	public Triangle(int x1,int y1,int x2,int y2,int x3,int y3,Color color) {
		super(new int[]{x1,x2,x3},
				new int[]{y1,y2,y3},
				3,color);
	}

}
