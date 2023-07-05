package ui.components;

import ui.Frame;
import ui.Panel;
import ui.components.style.Style;

import ui.graphics.Graphics;

public abstract class DrawnComponent {
    
    private Style style;
    private boolean beingHovered = false; 
    private boolean beingClicked = false;
    private Frame frame;
    protected Graphics g;

    public DrawnComponent(Frame frame) {
        this.style = new Style(this);
        this.frame = frame;
    }

    public void updateGraphicsStyle(Graphics g) {
        this.g = g;
        Style style = getStyle();
        if (style.getBooleanAttribute("disabled")) return;
        g.setStyle(style);
        draw(g, style);
    }

    public abstract void draw(Graphics g, Style style);
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
        return style.getIntAttribute("x") + style.getIntAttribute("xOffset");
    }

    public int getY() {
        return style.getIntAttribute("y") + style.getIntAttribute("yOffset");
    }

    public int getWidth() {
        return style.getIntAttribute("width");
    }

    public int getHeight() {
        return style.getIntAttribute("height");
    }


}
