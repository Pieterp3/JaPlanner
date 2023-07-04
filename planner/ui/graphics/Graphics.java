package ui.graphics;

import java.awt.Graphics2D;

import ui.components.style.Style;

public class Graphics {
    
    private Graphics2D g;
    private int strokeSize = 1;

    public Graphics(Graphics2D g) {
        this.g = g;
    }

    public void drawBackground(Style style, int x, int y, int width, int height, boolean isHovered, boolean isPressed) {
        if (!style.isOpaque()) { return; }
        Color backgroundColor = isHovered ? isPressed ? style.getBackgroundPressColor() : style.getBackgroundHoverColor() : style.getBackgroundColor();
        setColor(backgroundColor);
        fillRect(x, y, width, height);
    }

    public void drawCenteredText(int x, int y, int width, int height, String text) {
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getHeight();
        drawString(text, x + (width / 2) - (textWidth / 2), y + (height / 2) + (textHeight / 2) - g.getFontMetrics().getDescent());
    }

    public void attemptBorder(Style style, int x, int y, int width, int height, boolean isHovered) {
        if (!style.hasBorder()) { return; }
        Color borderColor = isHovered ? style.getBorderHoverColor() : style.getBorderColor();
        setColor(borderColor);
        setStroke(style.getBorderWidth());
        drawRect(x, y, width, height);
    }

    public void drawText(Style style, int x, int y, int width, int height, String text) {
        setFont(style.getFont());
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
        drawString(text, x, drawY);
    }

    public void drawStandardText(Style style, int x, int y, int width, int height) {
        drawText(style, x, y, width, height, style.getText());
    }

    public void drawScrollbar(Style style, Style buttonStyle, int x, int y, int width, int height, 
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
        setColor(style.getScrollbarColor());
        fillRect(scrollbarX, scrollbarY, scrollbarWidth, scrollbarHeight);
        setColor(style.getScrollerBackgroundColor());
        fillRect(scrollerX, scrollerY, scrollerWidth, scrollerHeight);
        setColor(buttonStyle.getBorderColor());
        drawRect(scrollbarX, scrollbarY, scrollbarWidth, scrollbarHeight);
    }

    public void setFont(java.awt.Font font) {
        g.setFont(font);
    }

    public void setStroke(int size) {
        if (size == strokeSize) { return; }
        g.setStroke(new java.awt.BasicStroke(size));
        strokeSize = size;
    }

    public void setColor(Color color) {
        g.setColor(color.getAwtColor());
    }

    public void drawRect(int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
    }

    public void fillRect(int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }

    public void drawString(String text, int x, int y) {
        g.drawString(text, x, y);
    }

}
