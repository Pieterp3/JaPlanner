package ui.components;


import ui.Frame;
import ui.Panel;
import ui.components.style.Style;

import ui.graphics.Graphics;
import util.structures.Map;

/** TODO
 * 5. Create Drawn Components
    a. ItemChooser
    b. Checkbox
    c. MenuBar
    d. TabbedPanel
    e. VideoPlayer
    f. ImageDisplay
    g. WebDisplay
    h. PaintingPanel

    
20. Allow VideoPlayer to read streams from internet or play local VideoPlayer
22. Add voice recognition


 */
public abstract class DrawnComponent {

    // private static int compCount = 0;

    protected Style style;
    private boolean beingHovered = false;
    private boolean beingClicked = false;
    private Frame frame;
    protected Graphics g;
    // private final int compId = (compCount += 1);

    public DrawnComponent(Frame frame) {
        this.style = new Style(this);
        this.frame = frame;
    }

    public void updateGraphicsStyle(Graphics g) {
        this.g = g;
        if (style.getBooleanAttribute("disabled"))
            return;
        g.setStyle(style);
        draw(g, style);
    }

    public abstract void draw(Graphics g, Style style);

    public abstract void click(int x, int y);

    public abstract void setHoveredCursor(int x, int y);

    public void setAttribute(String key, Object value) {
        style.setAttribute(key, value);
    }

    public String getAttribute(String key) {
        return style.getAttribute(key);
    }

    public void setAttributes(Map<String, Object> attributes) {
        style.setAttributes(attributes);
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

    public void setPressed(boolean pressed) {
        beingClicked = pressed;
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

    public Style getStyle() {
        return style;
    }

    public void setSetHovered(boolean b) {
        beingHovered = b;
    }

}
