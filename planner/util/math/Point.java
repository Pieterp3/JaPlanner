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

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public double getExactX() {
        return x;
    }

    public double getExactY() {
        return y;
    }

    public double distanceTo(int x, int y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    public double distanceTo(Point point) {
        return distanceTo(point.getX(), point.getY());
    }

    public double distanceToExact(double x, double y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    public double distanceToExact(Point point) {
        return distanceToExact(point.getExactX(), point.getExactY());
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void translate(double x, double y) {
        setLocation(this.x + x, this.y + y);
    }

    public void setLocation(Point point) {
        setLocation(point.getExactX(), point.getExactY());
    }

    public static double getDistance(int x, int y, int x2, int y2) {
        return Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
    }

    public static double getDistance(Point point, Point point2) {
        return getDistance(point.getX(), point.getY(), point2.getX(), point2.getY());
    }

    public static double getDistanceExact(double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
    }

    public static double getDistanceExact(Point point, Point point2) {
        return getDistanceExact(point.getExactX(), point.getExactY(), point2.getExactX(), point2.getExactY());
    }

    public void setX(double x2) {
        this.x = x2;
    }

    public void setY(double y2) {
        this.y = y2;
    }

}
