package ui.components.shapes;


import util.math.Point;

public interface Shape {

    public double getArea();

    public double getPerimeter();

    public boolean contains(double x, double y);

    public boolean contains(Point p);

    public boolean contains(Polygon s);

    public boolean intersects(Polygon s);

    public void translate(double x, double y);

    public void translate(Point p);

    public void scale(double factor);

    public void skew(double xFactor, double yFactor);

    public void rotate(double angle);

    public Point getCenter();

    public void moveInArc(Point p, double angle, double distance);

    public void moveInArc(double x, double y, double angle, double distance);

    public void moveInArc(Polygon p, double angle, double distance);

}
