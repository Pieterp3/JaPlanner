package ui.components.impl.shapes;

import math.Point;
import structures.List;
import ui.Frame;
import ui.components.DrawnComponent;

public abstract class Polygon extends DrawnComponent {

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

    public Point getPoint(int index) {
        return points.get(index);
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
    
}
