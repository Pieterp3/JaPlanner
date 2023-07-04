package ui.panels.components;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

import ui.panels.components.style.Style;

public class ArtAssistant {
    
    public static void attemptBackground(Graphics2D g, Style style, int x, int y, int width, int height, boolean isHovered, boolean isPressed) {
        if (!style.isOpaque()) { return; }
        Color backgroundColor = isHovered ? isPressed ? style.getBackgroundPressColor() : style.getBackgroundHoverColor() : style.getBackgroundColor();
        g.setColor(backgroundColor);
        g.fillRect(x, y, width, height);
    }

    public static void drawCenteredText(Graphics2D g, int x, int y, int width, int height, String text) {
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getHeight();
        g.drawString(text, x + (width / 2) - (textWidth / 2), y + (height / 2) + (textHeight / 2) - g.getFontMetrics().getDescent());
    }

    public static void attemptBorder(Graphics2D g, Style style, int x, int y, int width, int height, boolean isHovered) {
        if (!style.hasBorder()) { return; }
        Color borderColor = isHovered ? style.getBorderHoverColor() : style.getBorderColor();
        g.setColor(borderColor);
        g.setStroke(new BasicStroke(style.getBorderWidth()));
        g.drawRect(x, y, width, height);
    }

    public static void drawText(Graphics2D g, Style style, int x, int y, int width, int height, String text) {
        g.setFont(style.getFont());
        int textWidth = g.getFontMetrics().stringWidth(text);
        String alignment = style.getAlignment();
        int padding = style.getPadding();
        int borderThickness = style.getBorderWidth();
        int wallOffset = borderThickness + padding;
        
        x += alignment.equals("center") ? 
            ((width / 2) - (textWidth / 2)) : //Centered
            alignment.equals("right") ? 
            (width - textWidth - wallOffset) : //Right aligned
        wallOffset;//Left aligned
        
        if (!alignment.equals("center") && style.hasBorder()) {
            x += (style.getBorderWidth() * (alignment.equals("left") ? 1 : -1));
        }
        int drawY = y + height / 2 + g.getFontMetrics().getHeight() / 2 - g.getFontMetrics().getDescent();
        g.drawString(text, x, drawY);
    }

    public static void drawStandardText(Graphics2D g, Style style, int x, int y, int width, int height) {
        drawText(g, style, x, y, width, height, style.getText());
    }
}
