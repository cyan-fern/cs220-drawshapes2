package knox.drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends AbstractShape
{
	protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    
    public Rectangle(int left,int top,int right,int bottom,Color color){
    	super(color);
    	this.x1=left;
    	this.y1=top;
        this.x2=right;
        this.y2=bottom;
        this.center=this.calccenter();
    }
    
    public Rectangle(Point center,int left,int top,int right,int bottom,Color color){
    	super(color);
    	this.x1=left;
    	this.y1=top;
        this.x2=right;
        this.y2=bottom;
        this.center=center;
    }

	@Override
	public boolean satcheck(IShape s) {
		Range r=this.satcast(1, 0);
		Range sr=s.satcast(1, 0);
		if(!r.intersect(sr)) {return false;}
		
		r=this.satcast(0, 1);
		sr=s.satcast(0, 1);
		return r.intersect(sr);
	}

	@Override
	public Range satcast(int xv, int yv) {
		Range r = new Range(dotproduct(x1,y1,xv,yv));
		r.add(dotproduct(x2,y1,xv,yv));
		r.add(dotproduct(x1,y2,xv,yv));
		r.add(dotproduct(x2,y2,xv,yv));
		return r;
	}
	@Override
	public Point cpoint(int x, int y) {
		int px,py;
		if(x<(x1+x2)/2) {px=x1;}else {px=x2;}
		if(y<(y1+y2)/2) {py=y1;}else {py=y2;}
		return new Point(px,py);
	}

	@Override
	public boolean contains(Point p) {
		return x1<p.x&&p.x<x2&&y1<p.y&&p.y<y2;
	}
    /* (non-Javadoc)
     * @see drawshapes.sol.Shape#draw(java.awt.Graphics)
     */
    @Override
    public void draw(Graphics g) {
        if (isSelected()){
            g.setColor(color.darker());
        } else {
            g.setColor(getColor());
        }
        g.fillRect(x1,y1,x2-x1,y2-y1);
    }
    
    public String toString() {
    	return String.format("RECTANGLE %d %d %d %d %s %s",
                x1,y1,x2,y2,
                Util.colorToHex(getColor()),
                selected);
    }
    
    private Point calccenter() {
		return new Point((x1+x2)/2,(y1+y2)/2);
	}
    
	@Override
	public void scale(double d) {
		x1=(int)(center.x+(x1-center.x)*d);
		y1=(int)(center.y+(y1-center.y)*d);
		x2=(int)(center.x+(x2-center.x)*d);
		y2=(int)(center.y+(y2-center.y)*d);
	}

	public static IShape parsemake(String sarg) {
		String[] arg = sarg.split("\s+");
		int[] iarg = new int[4];
		for(int i=0;i<4;i++) {iarg[i]=Integer.valueOf(arg[i]);}
        Rectangle s = new Rectangle(iarg[0],iarg[1],iarg[2],iarg[3],Util.hexToColor(arg[4]));
        s.setSelected(Boolean.parseBoolean(arg[5]));
        return s;
	}
}
