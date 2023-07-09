package ui.components.impl.shapes.impl;

import ui.Frame;
import ui.components.impl.shapes.Polygon;
import ui.components.style.Style;
import ui.graphics.Graphics;
import util.math.Point;
import util.structures.List;

public class Triangle extends Polygon {

	public Triangle(Frame frame, Point p1, Point p2, Point p3) {
		super(frame, new List<Point>(){{add(p1); add(p2); add(p3);}});
	}

	public Triangle(Frame frame, double x1, double y1, double x2, double y2, double x3, double y3) {
		this(frame, new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
	}

	public Triangle(Frame frame, double x1, double y1, double width, double height) {
		this(frame, x1, y1, x1 + width, y1, x1 + width / 2, y1 + height);
	}

	@Override
	public void draw(Graphics g, Style style) {
		g.setColor(style.getColorAttribute("backgroundColor"));
		g.fillShape(getPoints());
		g.setColor(style.getColorAttribute("borderColor"));
		g.drawShapeBorder(getPoints());
	}

	@Override
	public void click(int x, int y) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setHoveredCursor(int x, int y) {
		// TODO Auto-generated method stub
	}
	
}
