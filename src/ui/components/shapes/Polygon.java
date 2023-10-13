package ui.components.shapes;


import ui.components.DrawnComponent;
import ui.frames.Frame;
import util.math.Point;
import util.structures.List;

public abstract class Polygon extends DrawnComponent implements Shape {

    private List<Point> points;

    public Polygon(Frame frame) {
        super(frame);
    }

    public Polygon(Frame frame, List<Point> points) {
        super(frame);
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        if (points == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }
        this.points = points;
    }

    public void addPoint(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        points.add(point);
    }

    public void removePoint(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        points.remove(point);
    }

    public void removePoint(int index) {
        points.remove(index);
    }

    public void clearPoints() {
        points.clear();
    }

    public int getPointCount() {
        return points.size();
    }

    public void setPoint(int index, Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        points.set(point, index);
    }

    public void setPoint(int index, int x, int y) {
        points.set(new Point(x, y), index);
    }

    public void setPointX(int index, int x) {
        points.get(index).setX(x);
    }

    @Override
    public double getArea() {
        double sum = 0;
        for (int i = 0; i < points.size(); i++) {
            if (i == 0) {
                sum += points.get(i).getX() * (points.get(i + 1).getY() - points.get(points.size() - 1).getY());
            } else if (i == points.size() - 1) {
                sum += points.get(i).getX() * (points.get(0).getY() - points.get(i - 1).getY());
            } else {
                sum += points.get(i).getX() * (points.get(i + 1).getY() - points.get(i - 1).getY());
            }
        }
        double area = 0.5 * Math.abs(sum);
        return area;
    }

    @Override
    public double getPerimeter() {
        double perimeter = 0;
        for (int i = 0; i < points.size(); i++) {
            if (i == points.size() - 1) {
                perimeter += points.get(i).distanceTo(points.get(0));
            } else {
                perimeter += points.get(i).distanceTo(points.get(i + 1));
            }
        }
        return perimeter;
    }

    @Override
    public boolean contains(Point test) {
        return contains(test.getX(), test.getY());
    }

    @Override
    public boolean contains(double x, double y) {
        for (int i = 0, j = points.size() - 1; i < points.size(); j = i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(j);
            if ((p1.getY() > y) != (p2.getY() > y) &&
                    (x < (p2.getX() - p1.getX()) * (y - p1.getY()) / (p2.getY() - p1.getY()) + p1.getX())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Polygon p) {
        for (Point point : p.getPoints()) {
            if (!contains(point)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean intersects(Polygon p) {
        boolean isContained = false;
        boolean inUncontained = false;
        for (Point point : p.getPoints()) {
            if (contains(point)) {
                isContained = true;
            } else {
                inUncontained = true;
            }
            if (isContained && inUncontained) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void translate(Point p) {
        translate(p.getX(), p.getY());
    }

    @Override
    public void scale(double factor) {
        Point center = getCenter();
        for (Point point : points) {
            point.translate(-center.getX(), -center.getY());
            point.setLocation(point.getX() * factor, point.getY() * factor);
            point.translate(center.getX(), center.getY());
        }
    }

    @Override
    public void skew(double xFactor, double yFactor) {
        Point center = getCenter();
        for (Point point : points) {
            point.translate(-center.getX(), -center.getY());
            point.setLocation(point.getX() * xFactor, point.getY() * yFactor);
            point.translate(center.getX(), center.getY());
        }
    }

    @Override
    public void rotate(double angle) {
        Point center = getCenter();
        angle = Math.toRadians(angle);
        for (Point point : points) {
            point.translate(-center.getX(), -center.getY());
            double x = point.getX();
            double y = point.getY();
            point.setLocation(x * Math.cos(angle) - y * Math.sin(angle), x * Math.sin(angle) + y * Math.cos(angle));
            point.translate(center.getX(), center.getY());
        }
    }

    @Override
    public void moveInArc(double centerX, double centerY, double degrees, double radius) {
        // "Orbits this polygon around the given point by the given angle and distance"
        degrees = Math.toRadians(degrees);
        // Point center = getCenter();
        double changeInX = radius * Math.cos(degrees) + centerX - getCenter().getX();
        double changeInY = radius * Math.sin(degrees) + centerY - getCenter().getY();
        translate(changeInX, changeInY);
    }

    @Override
    public void moveInArc(Point p, double angle, double distance) {
        // "Orbits this polygon around the given point by the given angle and distance"
        moveInArc(p.getX(), p.getY(), angle, distance);
    }

    @Override
    public void moveInArc(Polygon p, double angle, double distance) {
        // "Orbits this polygon around the given polygon by the given angle and
        // distance"
        moveInArc(p.getCenter(), angle, distance);
    }

    @Override
    public Point getCenter() {
        double x = 0;
        double y = 0;
        for (Point point : points) {
            x += point.getX();
            y += point.getY();
        }
        x /= points.size();
        y /= points.size();
        return new Point(x, y);
    }

    @Override
    public void translate(double x, double y) {
        for (Point point : points) {
            point.translate(x, y);
        }
    }

}
