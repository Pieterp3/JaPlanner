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
        s.setColor(Color.black);
        s.setBackgroundColor(Color.white);
        s.setBackgroundHoverColor(Color.lightGray);
        s.setBackgroundPressColor(Color.gray);
        s.addDefaultBorder();
        s.setAlignment("center");
        s.setText("Button");
        s.setAction("UnsetAction");
        s.setWidth(220);
        s.setHeight(36);
    }

    public Button(Frame frame, String text, int x, int y, int width, int height) {
        this(frame);
        getStyle().setText(text);
        getStyle().setX(x);
        getStyle().setY(y);
        getStyle().setWidth(width);
        getStyle().setHeight(height);
        getStyle().setAction(text);
    }

    public Button(Frame frame, String arrow) {
        this(frame, arrow, 0, 0, 220, 36);
    }

    @Override
    public void draw(Graphics g) {
        Style style = getStyle();
        if (style.isDisabled()) return;
        g.drawBackground(style, getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        g.attemptBorder(style, getX(), getY(), getWidth(), getHeight(), isHovered());
        Color textColor = isHovered() ? isPressed() ? style.getPressColor() : style.getHoverColor() : style.getColor();
        g.setColor(textColor);
        g.drawStandardText(style, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void click(int x, int y) {
        if (getStyle().isDisabled()) return;
        String action = getStyle().getAction();
        if (action != null) {
            getFrame().getActivePanel().processAction(action);
        }
    }

    @Override
    public void setHoveredCursor(int x, int y) {
        getFrame().setCursor(Frame.HAND_CURSOR);
    }
    
}
