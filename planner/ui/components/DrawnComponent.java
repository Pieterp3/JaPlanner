package ui.components;

import java.awt.Graphics2D;

import ui.Frame;
import ui.Panel;
import ui.components.style.Style;

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
    public abstract void click(int x, int y);
    public abstract void setHoveredCursor(int x, int y);

    public boolean checkHover(int mouseX, int mouseY) {
        if (mouseX >= getX() && mouseX <= getX() + getWidth() && mouseY >= getY() && mouseY <= getY() + getHeight()) {
            beingHovered = true;
            setHoveredCursor(mouseX, mouseY);
            return true;
        }
        beingHovered = false;
        return false;
    }

   public Style getStyle() {
        return style;
   }

   public Panel getPanel() {
        return frame.getActivePanel();
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
        return style.getX() + style.getXOffset();
    }

    public int getY() {
        return style.getY() + style.getYOffset();
    }

    public int getWidth() {
        return style.getWidth();
    }

    public int getHeight() {
        return style.getHeight();
    }


}
