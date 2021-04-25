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
    
    public Rectangle(Point topleft, int width, int height, Color color){
    	super(new Point(topleft.x, topleft.y), color);
    	this.x1=topleft.x;
    	this.y1=topleft.y;
        this.x2=x1+width;
        this.y2=y1+height;
    }
    
    public Rectangle(int left, int right, int top, int bottom) {
    	this(new Point(left, top), right - left, bottom - top, Color.BLUE);
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
                x1,y1,x2-x1,y2-y1,
                Util.colorToHex(getColor()),
                selected);
    }
    
	@Override
	public void scale(double factor) {
		//this.width = (int)(this.width * factor);
		//this.height = (int)(this.height * factor);
	}

	public static IShape parsemake(String sarg) {
		String[] arg = sarg.split("\s+");
		int[] iarg = new int[4];
		for(int i=0;i<4;i++) {iarg[i]=Integer.valueOf(arg[i]);}
        Rectangle s = new Rectangle(new Point(iarg[0],iarg[1]),iarg[2],iarg[3],Util.hexToColor(arg[4]));
        s.setSelected(Boolean.parseBoolean(arg[5]));
        return s;
	}
}
