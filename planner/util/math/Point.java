package util.math;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;    
    }

    public Point() {
        this(0, 0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getIntX() {
        return (int) x;
    }

    public int getIntY() {
        return (int) y;
    }

    public double distanceTo(double x, double y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    public double distanceTo(Point point) {
        return distanceTo(point.getX(), point.getY());
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void translate(double x, double y) {
        setLocation(this.x + x, this.y + y);
    }

    public void setLocation(Point point) {
        setLocation(point.getX(), point.getY());
    }

    public static double getDistance(Point point, Point point2) {
        return getDistance(point.getX(), point.getY(), point2.getX(), point2.getY());
    }

    public static double getDistance(double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
    }

    public void setX(double x2) {
        this.x = x2;
    }

    public void setY(double y2) {
        this.y = y2;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
