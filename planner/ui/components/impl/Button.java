package ui.components.impl;

import ui.Frame;
import ui.components.DrawnComponent;
import ui.components.style.Style;

import ui.graphics.Color;
import ui.graphics.Graphics;

public class Button extends DrawnComponent {

    public Button(Frame frame) {
        super(frame);
        Style s = getStyle();
        s.setColorAttribute("backgroundHoverColor", Color.lightGray);
        s.setColorAttribute("backgroundPressColor", Color.gray);
        s.addDefaultBorder();
        s.setAttribute("alignment", "center");
        s.setAttribute("text", "Button");
        s.setAttribute("action", "UnsetAction");
        s.setAttribute("width", 220);
        s.setAttribute("height", 36);
    }

    public Button(Frame frame, String text, int x, int y, int width, int height) {
        this(frame);
        Style s = getStyle();
        s.setAttribute("text", text);
        s.setAttribute("x", x);
        s.setAttribute("y", y);
        s.setAttribute("width", width);
        s.setAttribute("height", height);
        s.setAttribute("action", text);
    }

    public Button(Frame frame, String arrow) {
        this(frame, arrow, 0, 0, 220, 36);
    }

    @Override
    public void draw(Graphics g, Style style) {
        g.drawBackground(getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        g.attemptBorder(getX(), getY(), getWidth(), getHeight(), isHovered());
        String textColor = isHovered() ? isPressed() ? "pressColor" : "hoverColor" : "color";
        g.setColor(style.getColorAttribute(textColor));
        g.drawStandardText(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void click(int x, int y) {
        if (getStyle().getBooleanAttribute("disabled")) return;
        String action = getStyle().getAttribute("action");
        if (action != null) {
            getFrame().getActivePanel().processAction(action);
        }
    }

    @Override
    public void setHoveredCursor(int x, int y) {
        getFrame().setCursor(Frame.HAND_CURSOR);
    }
    
}
