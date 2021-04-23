package knox.drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circle extends AbstractShape
{
    private int diameter;
    
    public Circle(Color color, Point center, int diameter) {
    	super(center, color);
        boundingBox = new BoundingBox(center.x - diameter/2, center.x + diameter/2, center.y - diameter/2, center.y + diameter/2);
        this.diameter = diameter;
    }

    @Override
    public void draw(Graphics g) {
        if (isSelected()){
            g.setColor(getColor().darker());
        } else {
            g.setColor(getColor());
        }
        g.fillOval((int)getAnchorPoint().getX() - diameter/2,
                (int)getAnchorPoint().getY() - diameter/2,
                diameter,
                diameter);
    }
    
    public String toString() {
        return String.format("CIRCLE %d %d %d %s %s", 
                this.getAnchorPoint().x, 
                this.getAnchorPoint().y,
                this.diameter,
                Util.colorToHex(this.getColor()),
                this.isSelected());
    }

	@Override
	public void scale(double factor) {
		this.diameter = (int)(factor * this.diameter);
	}

	@Override
	public boolean satcheck(IShape s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Range satcast(int xv, int yv) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IShape parsemake(String sarg) {
		String[] arg = sarg.split("\s+");
		int[] iarg = new int[3];
		for(int i=0;i<3;i++) {iarg[i]=Integer.valueOf(arg[i]);}
        Circle s = new Circle(Util.hexToColor(arg[3]),new Point(iarg[0],iarg[1]),iarg[2]);
        s.setSelected(Boolean.parseBoolean(arg[4]));
        return s;
	}

}
