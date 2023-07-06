package ui.components.impl.shapes;

import util.math.Point;

public interface Shape {

    public double getArea();
    public double getPerimeter();
    public boolean contains(int x, int y);
    public boolean contains(Point p);
    public boolean contains(Polygon s);
    public boolean intersects(Polygon s);
    public void translate(int x, int y);
    public void translate(Point p);
    public void scale(double factor);
    public void skew(double xFactor, double yFactor);
    public void rotate(double angle);
    public Point getCenter();
    

}
