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
    
}
