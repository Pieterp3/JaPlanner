package ui.components.shapes.impl;


import ui.components.shapes.Polygon;
import ui.components.style.Style;
import ui.frames.Frame;
import ui.graphics.Graphics;
import util.math.Point;
import util.structures.lists.List;

public class Circle extends Polygon {

	private double radius;
	private double verticalSkew;
	private double horizontalSkew;

	public Circle(Frame frame, Point center, double radius, double xSkew, double ySkew) {
		super(frame, new List<Point>() {
			{
				add(center);
			}
		});
		this.radius = radius;
		verticalSkew = ySkew;
		horizontalSkew = xSkew;
	}

	public Circle(Frame frame, double centerX, double centerY, double radius, double xSkew, double ySkew) {
		this(frame, new Point(centerX, centerY), radius, xSkew, ySkew);
	}

	public Circle(Frame frame, double centerX, double centerY, double radius) {
		this(frame, centerX, centerY, radius, 0, 0);
	}

	public Circle(Frame frame, Point center, double radius) {
		this(frame, center, radius, 0, 0);
	}

	public Circle(Frame frame, double radius) {
		this(frame, 0, 0, radius);
	}

	public Circle(Frame frame, Point center) {
		this(frame, center, 0);
	}

	public Circle(Frame frame) {
		this(frame, 0, 0, 0);
	}

	@Override
	public void draw(Graphics g, Style style) {
		int centerX = (int) getPoints().get(0).getX();
		int centerY = (int) getPoints().get(0).getY();
		int width = (int) radius * 2 + (int) horizontalSkew;
		int height = (int) radius * 2 + (int) verticalSkew;
		int bottomLeftX = centerX - (width / 2);
		int bottomLeftY = centerY - (height / 2);
		g.setColor(style.getColorAttribute("backgroundColor"));
		g.fillCircle(bottomLeftX, bottomLeftY, width, height);
		g.setColor(style.getColorAttribute("borderColor"));
		g.drawCircle(bottomLeftX, bottomLeftY, width, height);
	}

	@Override
	public void click(int x, int y) {
	}

	@Override
	public void setHoveredCursor(int x, int y) {
	}

}
