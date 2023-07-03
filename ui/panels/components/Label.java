package ui.panels.components;

import ui.Frame;
import java.awt.*;

import ui.panels.components.style.Style;

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
        if (style.isOpaque()) {
            g.setColor(style.getBackgroundColor());
            g.fillRect(getX(), getY(), getWidth(), getHeight());
        }
        g.setColor(style.getColor());
        g.setFont(style.getFont());
        int stringWidth = g.getFontMetrics().stringWidth(style.getText());
        if (style.matchesAttribute("alignment", "left")) {
            g.drawString(style.getText(), getX(), getY());
        } else if (style.matchesAttribute("alignment", "center")) {
            g.drawString(style.getText(), getX() + (getWidth() / 2) - (stringWidth / 2), getY());
        } else if (style.matchesAttribute("alignment", "right")) {
            g.drawString(style.getText(), getX() + getWidth() - stringWidth, getY());
        }
    }

    @Override
    public void click() {
        // Do nothing
    }
    
}
