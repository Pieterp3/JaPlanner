package ui.panels.components.impl;

import ui.Frame;
import ui.panels.components.ArtAssistant;
import ui.panels.components.DrawnComponent;
import ui.panels.components.style.Style;

import java.awt.*;

public class Button extends DrawnComponent {

    public Button(Frame frame) {
        super(frame);
        Style s = getStyle();
        s.setColor(Color.black);
        s.setBackgroundColor(Color.WHITE);
        s.setBackgroundHoverColor(Color.LIGHT_GRAY);
        s.setBackgroundPressColor(Color.GRAY);
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
    public void draw(Graphics2D g) {
        Style style = getStyle();
        if (style.isDisabled()) return;
        ArtAssistant.attemptBackground(g, style, getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        ArtAssistant.attemptBorder(g, style, getX(), getY(), getWidth(), getHeight(), isHovered());
        Color textColor = isHovered() ? isPressed() ? style.getPressColor() : style.getHoverColor() : style.getColor();
        g.setColor(textColor);
        ArtAssistant.drawStandardText(g, style, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void click() {
        if (getStyle().isDisabled()) return;
        String action = getStyle().getAction();
        if (action != null) {
            getFrame().getActivePanel().processAction(action);
        }
    }
    
}
