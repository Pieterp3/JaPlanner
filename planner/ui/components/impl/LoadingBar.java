package ui.components.impl;


import ui.Frame;
import ui.components.DrawnComponent;
import ui.components.style.Style;
import ui.graphics.Graphics;
import util.events.EventTimer;

public class LoadingBar extends DrawnComponent {

	private EventTimer timerEvent;

	public LoadingBar(Frame frame, String text, int x, int y, int width, int height) {
		super(frame);
		setAttribute("text", text);
		setAttribute("x", x);
		setAttribute("y", y);
		setAttribute("width", width);
		setAttribute("height", height);
		setAttribute("alignment", "center");
		setAttribute("borderWidth", 3);
		setAttribute("fillColor", "A1A1A1");
	}

	public LoadingBar(Frame frame, int x, int y, int width, int height) {
		this(frame, "", x, y, width, height);
	}

	public LoadingBar(Frame frame, String text, int x, int y) {
		this(frame, text, x, y, 0, 0);
	}

	public LoadingBar(Frame frame, String text) {
		this(frame, text, 0, 0);
	}

	public LoadingBar(Frame frame) {
		this(frame, "");
	}

	public void setEventTimer(EventTimer event) {
		this.timerEvent = event;
	}

	@Override
	public void draw(Graphics g, Style style) {
		g.drawBackground(getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
		g.attemptBorder(getX(), getY(), getWidth(), getHeight(), isHovered());
		if (timerEvent != null) {
			int width = (int) (getWidth() * timerEvent.getProgress());
			g.setColor(style.getColorAttribute("fillColor"));
			int borderSize = style.getIntAttribute("borderWidth") - 1;
			g.fillRect(getX() + borderSize, getY() + borderSize, width - borderSize * 2 + 1,
					getHeight() - borderSize * 2 + 1);
		}
		g.setColor(style.getColorAttribute("color"));
		g.drawStandardText(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void click(int x, int y) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setHoveredCursor(int x, int y) {
		getFrame().setCursor(Frame.LOADING_CURSOR);
	}

}
