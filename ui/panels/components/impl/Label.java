package ui.panels.components.impl;

import ui.Frame;
import ui.panels.components.ArtAssistant;
import ui.panels.components.DrawnComponent;
import ui.panels.components.style.Style;

import java.awt.*;

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
    public void draw(Graphics2D g) {
        Style style = getStyle();
        if (style.isDisabled()) return;
        ArtAssistant.attemptBackground(g, style, getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        ArtAssistant.attemptBorder(g, style, getX(), getY(), getWidth(), getHeight(), isHovered());
        g.setColor(style.getColor());
        ArtAssistant.drawStandardText(g, style, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void click() {
        // Do nothing
    }
    
}
