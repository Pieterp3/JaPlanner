package ui.components;

import java.awt.Graphics2D;

import ui.components.style.Style;

import java.awt.BasicStroke;
import java.awt.Color;

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

    public static void drawScrollbar(Graphics2D g, Style style, Style buttonStyle, int x, int y, int width, int height, 
            int scrollIndex, int compCount) {
        int scrollbarSize = style.getScrollbarSize();
        int scrollerSize = Math.min((scrollbarSize * 3), width - (scrollbarSize * 2));
        int scrollbarX,scrollbarY;
        int scrollbarWidth, scrollbarHeight;
        int scrollerX, scrollerY, scrollerWidth, scrollerHeight;
        int innerScrollSize;
        int borderWidth = style.getBorderWidth();
        int buttonBorderWidth = buttonStyle.getBorderWidth();
        if (style.getResizesVertically()) {
            scrollbarWidth = scrollbarSize;
            scrollbarHeight = height - (scrollbarSize * 2) - (borderWidth * 2) - (buttonBorderWidth * 2);
            scrollbarX = x + width - scrollbarSize - borderWidth;
            scrollbarY = y + scrollbarSize + borderWidth + buttonBorderWidth;
            scrollerX = scrollbarX;
            innerScrollSize = (scrollbarHeight - (scrollbarSize * 2) - borderWidth - (buttonBorderWidth * 2));
            scrollerY = scrollbarY + (innerScrollSize * scrollIndex) / (compCount - 1);
            scrollerWidth = scrollbarWidth;
            scrollerHeight = (scrollbarHeight * scrollerSize) / width;
        } else {
            scrollbarWidth = width - (scrollbarSize * 2) - (borderWidth * 2) - (buttonBorderWidth * 2);
            scrollbarHeight = scrollbarSize;
            scrollbarX = x + scrollbarSize + borderWidth + buttonBorderWidth;
            scrollbarY = y + height - scrollbarSize - borderWidth;
            scrollerY = scrollbarY;
            innerScrollSize = (scrollbarWidth - (scrollbarSize * 2) - borderWidth - (buttonBorderWidth * 2));
            scrollerX = scrollbarX + (innerScrollSize * scrollIndex) / (compCount - 1);
            scrollerWidth = (scrollbarWidth * scrollerSize) / width;
            scrollerHeight = scrollbarHeight;
        }
        g.setColor(style.getScrollbarColor());
        g.fillRect(scrollbarX, scrollbarY, scrollbarWidth, scrollbarHeight);
        g.setColor(style.getScrollerBackgroundColor());
        g.fillRect(scrollerX, scrollerY, scrollerWidth, scrollerHeight);
        g.setColor(buttonStyle.getBorderColor());
        g.drawRect(scrollbarX, scrollbarY, scrollbarWidth, scrollbarHeight);
    }
}
