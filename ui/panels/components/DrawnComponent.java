package ui.panels.components;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.awt.Font;
import ui.Frame;

public abstract class DrawnComponent {
    
    private Style style;
    private boolean beingHovered = false;
    private boolean beingClicked = false;
    private Frame frame;

    public DrawnComponent(Frame frame) {
        this.style = new Style(this);
        this.frame = frame;
    }

    public abstract void draw(Graphics2D g);
    public abstract void click();

    public void checkHover(int mouseX, int mouseY) {
        if (mouseX >= getX() && mouseX <= getX() + getWidth() && mouseY >= getY() && mouseY <= getY() + getHeight()) {
            beingHovered = true;
        } else {
            beingHovered = false;
        }
    }

    public Style getStyle() {
        return style;
    }

    protected Frame getFrame() {
        return frame;
    }

    public boolean contains(int x, int y) {
        return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
    }

    public boolean isHovered() {
        return beingHovered;
    }

    public boolean isPressed() {
        return beingClicked;
    }

    public int getX() {
        return style.getX();
    }

    public int getY() {
        return style.getY();
    }

    public int getWidth() {
        return style.getWidth();
    }

    public int getHeight() {
        return style.getHeight();
    }


}
