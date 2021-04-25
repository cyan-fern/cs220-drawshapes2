package knox.drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Polygon extends AbstractShape {
	protected int[] x;
	protected int[] y;
	protected int n;

	public Polygon(Point anchorPoint,int[] x,int[] y,int n,Boolean unsafe,Color color) {
		super(anchorPoint, color);
		this.n=n;
		if(!unsafe) {this.x=x;this.y=y;}
		else{this.x=x.clone();this.y=y.clone();}
	}
	public Polygon(Point anchorPoint,int[] x,int[] y,int n,Color color) {
		this(anchorPoint,x,y,n,false,color);
	}
	public Polygon(int[] x,int[] y,int n,Color color) {
		this(new Point(x[0],y[0]),x,y,n,false,color);
	}

	@Override
	public boolean satcheck(IShape s) {
		int ix=y[0]-y[n-1];int iy=x[n-1]-x[0];
		Range r=this.satcast(ix, iy);Range sr=s.satcast(ix, iy);
		Boolean o=r.intersect(sr);
		for(int i=1;i<n;i++) {
			ix=y[i]-y[i-1];iy=x[i-1]-x[i];
			r=this.satcast(ix, iy);
			sr=s.satcast(ix, iy);
			o&=r.intersect(sr);
		}
		return o;
	}

	@Override
	public Range satcast(int xv,int yv) {
		Range r = new Range(dotproduct(x[0],y[0],xv,yv));
		for(int i=1;i<n;i++) {
			r.add(dotproduct(x[i],y[i],xv,yv));
		}
		return r;
	}

	@Override
	public Point cpoint(int xp, int yp) {
		int p=0;
		int id=Integer.MAX_VALUE;
		//Bad practice. Don't care. Fix later maybe.
		int min=id;
		for(int i=1;i<n;i++) {
			id=x[i]*x[i]+y[i]*y[i];
			if(id<min) {min=id;p=i;}
		}
		return new Point(x[p],y[p]);
	}

	@Override
	public boolean contains(Point s) {
		int ix=y[0]-y[n-1];int iy=x[n-1]-x[0];
		Range r=this.satcast(ix, iy);int sr=dotproduct(s.x,s.y,ix,iy);
		Boolean o=r.intersect(sr);
		for(int i=1;i<n;i++) {
			ix=y[i]-y[i-1];iy=x[i-1]-x[i];
			r=this.satcast(ix, iy);
			sr=dotproduct(s.x,s.y,ix,iy);
			o&=r.intersect(sr);
		}
		return o;
	}

	@Override
	public void draw(Graphics g) {
        if (isSelected()){g.setColor(color.darker());}
        else {g.setColor(getColor());}
		g.fillPolygon(x,y,n);
	}
    
    public String toString() {
    	String subs="";
    	for(int i=0;i<n;i++) {subs+=String.format("%d %d ",x[i],y[i]);}
    	return String.format("POLYGON %s%d %s %s",subs,n,Util.colorToHex(getColor()),selected);
    }

	@Override
	public void scale(double d) {
	}
	
	public static IShape parsemake(String sarg) {
		String[] arg = sarg.split("\s+");
		int n=(arg.length-2)/2;
		int[] xarg = new int[n];
		int[] yarg = new int[n];
		for(int i=0;i<n;i+=1) {xarg[i]=Integer.valueOf(arg[2*i]);yarg[i]=Integer.valueOf(arg[2*i+1]);}
        Polygon s = new Polygon(xarg,yarg,n,Util.hexToColor(arg[arg.length-2]));
        s.setSelected(Boolean.parseBoolean(arg[4]));
        return s;
	}
}
