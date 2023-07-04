package ui.components.impl;

import ui.Frame;
import ui.components.DrawnComponent;
import ui.components.style.Style;

import ui.graphics.Graphics;

public class Label extends DrawnComponent {

    public Label(Frame frame) {
        super(frame);
       getStyle().setOpaque(false);
    }

    public Label(Frame frame, String text, int x, int y) {
        this(frame);
       getStyle().setText(text);
       getStyle().setX(x);
       getStyle().setY(y);
    }

    @Override
    public void draw(Graphics g) {
        Style style = getStyle();
        if (style.isDisabled()) return;
        g.drawBackground(getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        g.attemptBorder(getX(), getY(), getWidth(), getHeight(), isHovered());
        g.setColor(style.getColor());
        g.drawStandardText(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void click(int x, int y) {
        // Do nothing
    }

    @Override
    public void setHoveredCursor(int x, int y) {
        getFrame().setCursor(Frame.DEFAULT_CURSOR);
    }
    
}
