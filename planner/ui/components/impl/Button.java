package ui.components.impl;


import ui.Frame;
import ui.components.DrawnComponent;
import ui.components.style.Style;

import ui.graphics.Color;
import ui.graphics.Graphics;

public class Button extends DrawnComponent {

    public Button(Frame frame) {
        super(frame);
        style.setColorAttribute("backgroundHoverColor", Color.lightGray);
        style.setColorAttribute("backgroundPressColor", Color.gray);
        style.addDefaultBorder();
        style.setAttribute("alignment", "center");
        style.setAttribute("text", "Button");
        style.setAttribute("action", "UnsetAction");
        style.setAttribute("width", 220);
        style.setAttribute("height", 36);
    }

    public Button(Frame frame, String text, int x, int y, int width, int height) {
        this(frame);
        style.setAttribute("text", text);
        style.setAttribute("x", x);
        style.setAttribute("y", y);
        style.setAttribute("width", width);
        style.setAttribute("height", height);
        style.setAttribute("action", text);
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
        if (style.getBooleanAttribute("disabled"))
            return;
        String action = getAttribute("action");
        if (action != null) {
            getFrame().getActivePanel().processAction(action);
        }
    }

    @Override
    public void setHoveredCursor(int x, int y) {
        getFrame().setCursor(Frame.HAND_CURSOR);
    }

}
