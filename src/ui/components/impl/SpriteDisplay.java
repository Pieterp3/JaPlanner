package ui.components.impl;

import game.sprites.Sprite;
import ui.components.DrawnComponent;
import ui.components.style.Style;
import ui.frames.Frame;
import ui.graphics.Color;
import ui.graphics.Graphics;
import util.math.Point;
import util.structures.lists.List;

public class SpriteDisplay extends DrawnComponent {

	private Sprite sprite;
	private int drawnScale = 16;
	private int spriteSize;
	private List<Point> selectedPoints = new List<>();
	private Point selectedPoint = new Point(0,0);

	public SpriteDisplay(Frame frame, int spriteSize, int x, int y, int width, int height) {
		super(frame);
        style.setAttribute("x", x);
        style.setAttribute("y", y);
        style.setAttribute("width", width);
        style.setAttribute("height", height);
		style.setColorAttribute("backgroundColor", Color.white);
        style.addDefaultBorder();
        style.setAttribute("width", spriteSize*drawnScale);
        style.setAttribute("height", style.getIntAttribute("width"));
		this.spriteSize = spriteSize;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public void draw(Graphics g, Style style) {
		g.drawBackground(getX(), getY(), getWidth(), getHeight(), false, false);
		//Draw sprite
		if (sprite != null) {
			sprite.setDrawnHeight(drawnScale);
			sprite.setDrawnWidth(drawnScale);
			sprite.setTopLeftLocation(new Point(getX(), getY()));
			sprite.draw(g);
		} else {
			//TODO draw Sprite.INVALID_SPRITE
		}
		//Highlight selected point in pink
		g.setColor(Color.pink);
		g.setStroke(1);
		g.drawRect(getX() + selectedPoint.getIntX()*drawnScale, getY() + selectedPoint.getIntY()*drawnScale, drawnScale, drawnScale);
	}

	public Point getSelectedPoint() {
		return selectedPoint;
	}

	public String getSelectedColor() {
		if (sprite == null) {
			return null;
		}
		return sprite.getColor(selectedPoint.getIntX(), selectedPoint.getIntY());
	}

	@Override
	public void click(int x, int y) {
		x -= getX();
		y -= getY();
		selectedPoint = new Point(x/drawnScale, y/drawnScale);
	}

	@Override
	public void setHoveredCursor(int x, int y) {
        getFrame().setCursor(Frame.DEFAULT_CURSOR);
	}

	public Sprite getSprite() {
		return sprite;
	}
	
}
