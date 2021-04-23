package knox.drawshapes;



import java.awt.Color;
import java.awt.Point;

public class Square extends Rectangle
{
    public Square(int centerX, int centerY, int length, Color color) {
        super(new Point(centerX, centerY), length, length, color);
    }
    
    public Square(Point center, int length, Color color) {
    	super(center, length, length, color);
    }
}
