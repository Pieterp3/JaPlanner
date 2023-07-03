package ui.panels.components;

import ui.Frame;
import ui.panels.components.style.Style;

import java.awt.*;

public class Button extends DrawnComponent {

    public Button(Frame frame) {
        super(frame);
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

    @Override
    public void draw(Graphics2D g) {
        Style style = getStyle();
        if (style.isDisabled()) return;
       // System.out.println("Drawing Button: " + getText() + " at (" + getX() + ", " + getY() + ") with width: " + getWidth() + " and height: " + getHeight());
       // System.out.println("Font: " + getFont());
       // System.out.println("is Opaque: " + isOpaque());
        if (style.isOpaque()) {
           // System.out.print("Background Color: (hovering, beingPressed) = (" + isHovered() + ", " + isPressed() + ") ");
            Color backgroundColor = isHovered() ? isPressed() ? style.getBackgroundPressColor() : style.getBackgroundHoverColor() : style.getBackgroundColor();
            //System.out.println(backgroundColor);
            g.setColor(backgroundColor);
            g.fillRect(getX(), getY(), getWidth(), getHeight());
        }
        if (style.hasBorder()) {
            Color borderColor = isHovered() ? isPressed() ? style.getBorderPressColor() : style.getBorderHoverColor() : style.getBorderColor();
            g.setColor(borderColor);
            g.setStroke(new BasicStroke(style.getBorderWidth()));
            g.drawRect(getX(), getY(), getWidth(), getHeight());
        }
        Color textColor = isHovered() ? isPressed() ? style.getPressColor() : style.getHoverColor() : style.getColor();
        g.setColor(textColor);
        g.setFont(style.getFont());
        int textWidth = g.getFontMetrics().stringWidth(style.getText());
        String alignment = style.getAlignment();
        int x = getX();
        if (alignment.equals("center")) {
            x += getWidth() / 2 - textWidth / 2;
        } else if (alignment.equals("right")) {
            x += getWidth() - textWidth;
        }
        g.drawString(style.getText(), x, getY() + getHeight() / 2 + g.getFontMetrics().getHeight() / 2 - g.getFontMetrics().getDescent());
    }

    @Override
    public void click() {
        if (getStyle().isDisabled()) return;
        System.out.println("Button clicked: " + getStyle().getAction());
        String action = getStyle().getAction();
        if (action != null) {
            getFrame().getActivePanel().processAction(action);
        }
    }
    
}
