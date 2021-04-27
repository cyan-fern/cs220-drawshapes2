package knox.drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circle extends AbstractShape
{
    private int r;
    private int x,y;
    
    public Circle(int x,int y,int radius,Color color) {
    	super(color);
        this.r = radius;
        this.x=x;this.y=y;
		this.center=this.calccenter();
    }
    
    public Circle(Point center,int x,int y,int radius,Color color) {
    	super(color);
        this.r = radius;
        this.x=x;this.y=y;
		this.center=center;
		//for consistency, and also because I might add it to the interface later
    }

    @Override
    public void draw(Graphics g) {
        if (isSelected()){
            g.setColor(getColor().darker());}
        else {
            g.setColor(getColor());}
        g.fillOval(x-r,y-r, r*2,r*2);
    }
    
    public String toString() {
        return String.format("CIRCLE %d %d %d %s %s", 
                x,y,this.r,
                Util.colorToHex(this.getColor()),
                this.isSelected());
    }
    
    private Point calccenter() {
		return new Point(this.x,this.y);
	}

	@Override
	public void move(int dx,int dy) {
		this.x+=dx;
		this.y+=dy;
	}

	@Override
	public void scale(double d) {
		this.r*=d;
	}

	@Override
    public boolean intersects(IShape s) {
		//override to try the presumably less expensive calculations first, and letting short-circuiting do its thing
        return s.satcheck(this)&&this.satcheck(s);
    }

	@Override
	public boolean satcheck(IShape s) {
		Point cp=s.cpoint(x,y);
		int ix=x-cp.x;int iy=y-cp.y;
		Range r=this.satcast(ix, iy);Range sr=s.satcast(ix, iy);
		return r.intersect(sr);
	}

	@Override
	public Range satcast(int xv, int yv) {
		//forced to use sqrt here sadly, integer math not quite possible. bad circle.
		double m = Math.sqrt(xv*xv+yv*yv);
		int p=dotproduct(x,y,xv,yv);
		Range o = new Range(p-(int)(r*m));
		o.add(p+(int)(r*m));
		return o;
	}

	@Override
	public Point cpoint(int x, int y) {
		return this.getcenter();
	}

	@Override
	public boolean contains(Point p) {
		int ix=x-p.x;int iy=y-p.y;
		return Math.sqrt(ix*ix+iy*iy)<r;
	}

	public static IShape parsemake(String sarg) {
		String[] arg = sarg.split("\s+");
		int[] iarg = new int[3];
		for(int i=0;i<3;i++) {iarg[i]=Integer.valueOf(arg[i]);}
        Circle s = new Circle(iarg[0],iarg[1],iarg[2],Util.hexToColor(arg[3]));
        s.setSelected(Boolean.parseBoolean(arg[4]));
        return s;
	}

}
