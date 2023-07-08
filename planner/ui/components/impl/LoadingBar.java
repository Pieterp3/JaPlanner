package ui.components.impl;

import ui.Frame;
import ui.components.DrawnComponent;
import ui.components.style.Style;
import ui.graphics.Graphics;
import util.events.TimerEvent;

public class LoadingBar extends DrawnComponent {

	private TimerEvent timerEvent;

	public LoadingBar(Frame frame, String text, int x, int y, int width, int height) {
		super(frame);
		setAttribute("text", text);
		setAttribute("x", x);
		setAttribute("y", y);
		setAttribute("width", width);
		setAttribute("height", height);
		setAttribute("centered", true);
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
	/*
	 * add variables and methods to the TimerEvent class to allow for the
	 * loading bar to be updated
	 * 
	 */

	public void setEventTimer(TimerEvent event) {
		this.timerEvent = event;
	}

	@Override
	public void draw(Graphics g, Style style) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'draw'");
	}

	@Override
	public void click(int x, int y) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'click'");
	}

	@Override
	public void setHoveredCursor(int x, int y) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setHoveredCursor'");
	}

	
	
}
