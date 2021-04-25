package knox.drawshapes;

import java.awt.Color;
import java.awt.Point;

public abstract class AbstractShape implements IShape {
	protected Point anchorPoint;
	protected Color color;
	protected boolean selected;
	
	public AbstractShape(Point anchorPoint, Color color) {
		this.anchorPoint = anchorPoint;
		this.color = color;
	}
	
	@Override
    public void setAnchorPoint(Point p) {
        // TODO: move bounding box
        this.anchorPoint = p;
    }
    @Override
    public Color getColor() {
        return color;
    }
    @Override
    public void setColor(Color color) {
        this.color = color;
    }
    @Override
    public boolean isSelected() {
        return selected;
    }
    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    @Override
    public Point getAnchorPoint() {
        return anchorPoint;
    }
    @Override
    public boolean intersects(IShape s) {
        return this.satcheck(s)&&s.satcheck(this);
    }
    public int dotproduct(int x1,int y1,int x2,int y2) {
    	return (x1*x2)+(y1*y2);
    }
	@Override
    public boolean contains(Point point) {
		//this likely won't be efficient, but it should work.
		//..is what I thought, but I had things reversed in my head. I need to implement this separately for each shape.
		//which is annoying because it's so similar to satcheck(), but points aren't a shape
		//I thought about creating a dummy class, but I decided against that.
		//also I'm still sad about the whole untracked file thing, I may have to fix the repository a bit later
		return false;
    }

	@Override
	public void move(int dx, int dy) {
		this.setAnchorPoint(new Point(this.anchorPoint.x + dx, this.anchorPoint.y + dy));
	}

}
