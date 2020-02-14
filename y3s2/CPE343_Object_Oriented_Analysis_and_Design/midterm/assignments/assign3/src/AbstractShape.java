import java.util.ArrayList;
import java.awt.*;
/**
 *  AbstractShape class. Intended to serve as a superclass (generalization) for
 *  individual shapes like Triangle, Square, etc.
 *
 *  V2 - Created by Sally Goldin, 21 August 2017
 */
public abstract class AbstractShape
{
    /** Anchor point X,Y */
    protected Point anchor;   /* determines the "position" of a shape */
    /* Point is a class in package java.awt that has a public x and y member */

    /** list of points */
    protected ArrayList<Point> vertices = new ArrayList<Point>();

    /** how many points? */
    protected int pointCount;

    protected int figureNumber;

    /** color */
    protected Color color;

    /** so we can count and label figures */ 
    protected static int counter = 0;
    
    /** collection of all squares */
    protected static ArrayList<AbstractShape> allFigures = new ArrayList<AbstractShape>();

    /** used to cycle through display colors */    
    protected static Color colors[] = {Color.RED, Color.GREEN, Color.BLUE,
			      Color.MAGENTA, Color.ORANGE};

    public AbstractShape()
    {
        counter++;
        figureNumber = counter;
        color = colors[counter % 5];
        allFigures.add(this);
    }


    /**
     * Move the shape to a new location, specified by
     * the passed point.
     */
    public void move(Point newAnchor)
    {
        //Point newAnchor = new Point(x,y);
        int deltaX = newAnchor.x - anchor.x;
        int deltaY = newAnchor.y - anchor.y;
        anchor = newAnchor;
        int points = vertices.size();
        for (int i = 0; i < points; i++)
        {
            Point p = vertices.get(i);
            p.x += deltaX;
            p.y += deltaY;
        }
    }


    /**
     * Calculate and return the perimeter.
     * @return  Length of shape boundary
     */
    public abstract double calcPerimeter();

    /**
     * Calculate and return the area of the shape.
     * @return  area
     */
    public abstract double calcArea();

    /**
     * Draw the shape.
     * This will only work correctly for closed shapes with a finite
     * number of vertices (like triangles, squares and diamonds)
     * @param  graphics    Graphics context for drawing
     */
    public void draw(Graphics2D graphics)
    {
        graphics.setPaint(color);
        int points = vertices.size();
        for (int i = 0; i < points; i++)
        {
            Point p1 = vertices.get(i);
            Point p2 = vertices.get((i+1)%points);
            graphics.drawLine(p1.x*10,p1.y*10,p2.x*10,p2.y*10);
        }
        /* label it near the anchor point */
        int labelx = anchor.x*10 + 5;
        int labely = anchor.y*10 - 5;
        graphics.drawString(new String(" " + figureNumber),labelx,labely);
    }
    /** static method to draw all the shapes of this category
     * that have been created so far.
     * @param  graphics   Graphics context for drawing.
     */
    public static void drawAll(Graphics2D graphics)
    {
        for (int i = 0; i < allFigures.size(); i++) {
            AbstractShape shape = allFigures.get(i);
            shape.draw(graphics);
        }
    }


}