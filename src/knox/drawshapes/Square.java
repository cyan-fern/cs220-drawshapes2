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

	public static IShape parsemake(String sarg) {
		String[] arg = sarg.split("\s+");
		int[] iarg = new int[3];
		for(int i=0;i<3;i++) {iarg[i]=Integer.valueOf(arg[i]);}
        Square s = new Square(iarg[0],iarg[1],iarg[2],Util.hexToColor(arg[3]));
        s.setSelected(Boolean.parseBoolean(arg[4]));
        return s;
	}
}
