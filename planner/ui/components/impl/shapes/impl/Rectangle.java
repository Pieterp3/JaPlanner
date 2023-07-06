package ui.components.impl.shapes.impl;

import structures.List;
import ui.Frame;
import ui.components.impl.shapes.Polygon;
import ui.components.style.Style;
import ui.graphics.Graphics;
import util.math.Dimension;
import util.math.Point;

public class Rectangle extends Polygon {

    private static List<Point> toList(int x, int y, int width, int height) {
        Point p = new Point(x, y);
        Point p2 = new Point(x + width, y);
        Point p3 = new Point(x + width, y + height);
        Point p4 = new Point(x, y + height);
        return new List<Point>(new Point[] {p, p2, p3, p4});
    }

    public Rectangle(Frame frame, int x, int y, int width, int height) {
        super(frame, toList(x, y, width, height));
    }

    public Rectangle(Frame frame, Point p) {
        this(frame, p.getX(), p.getY(), 0, 0);
    }

    public Rectangle(Frame frame, Dimension d) {
        this(frame, 0, 0, d.getWidth(), d.getHeight());
    }

    public Rectangle(Frame frame, Point p, Dimension d) {
        this(frame, p.getX(), p.getY(), d.getWidth(), d.getHeight());
    }

    public Rectangle(Frame frame, int width, int height) {
        this(frame, 0, 0, width, height);
    }

    public Rectangle(Frame frame) {
        this(frame, 0,0,0,0); 
    }

    @Override
    public void draw(Graphics g, Style style) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public void click(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'click'");
    }

    @Override
    public void setHoveredCursor(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHoveredCursor'");
    }

    
    
}