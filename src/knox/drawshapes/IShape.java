package knox.drawshapes;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Interface for a shape to be drawn
 * 
 * You can add methods to this class if you'd like, such as
 * a method to move the anchor point by a given X/Y amount. 
 * 
 * @author jspacco
 *
 */
/**
 * @author jaimespacco
 *
 */
public interface IShape
{
    /**
     * Draw the shape using the given Graphics object
     * @param g
     */
    public void draw(Graphics g);
    /**
     * Returns whether this shape intersects shape s
     * 
     * @param s
     * @return
     */
    public boolean intersects(IShape s);
    /**
     * Calculate half of the collision check using sat
     * 
     * @param s
     * @return
     */
    public boolean satcheck(IShape s);
    /**
     * Casts this shape to the given vector
     * 
     * @param xv
     * @param yv
     * @return
     */
    public Range satcast(int xv,int yv);
    /**
     * Return closest vertex of shape to specified point
     * 
     * @param xv
     * @param yv
     * @return
     */
    public Point cpoint(int x,int y);
    /**
     * Returns whether this shape contains point p
     * 
     * @param p
     * @return
     */
    public boolean contains(Point p);
    /**
     * Return the color of this shape.
     * 
     * @return
     */
    public Color getColor();
    /**
     * Set the color of this shape to the given color.
     * 
     * @param color
     */
    public void setColor(Color color);
    /**
     * Returns whether this shape is selected
     * 
     * Some operations apply to all selected shapes.
     * 
     * @return
     */
    public boolean isSelected();
    /**
     * Set the selected status of this shape to the given value.
     * @param b
     */
    public void setSelected(boolean b);
    
    /**
     * Return the anchor point of this shape.
     * @return
     */
    public Point getAnchorPoint();
    /**
     * Set the anchor point of this shape to the given point.
     * @param p
     */
    public void setAnchorPoint(Point p);
	/**
     * Move the shape by the given dx and dy values
     * @param dx
     * @param dy
     */
    public void move(int dx, int dy);
    /**
     * Scale the size of the shape by the given factor
     * @param d
     */
    public void scale(double d);
}
