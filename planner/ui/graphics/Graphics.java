package ui.graphics;

import java.awt.Graphics2D;

import ui.components.style.Style;
import ui.graphics.fonts.Font;
import ui.graphics.fonts.CharacterData;

public class Graphics {
    
    private Graphics2D g;
    private int strokeSize = 1;
    private Style style;
    private Font font;
    
    public void updateGraphics(Graphics2D g) {
        this.g = g;
    }

    public void drawBackground(int x, int y, int width, int height, boolean isHovered, boolean isPressed) {
        if (!style.getBooleanAttribute("opaque")) { return; }
        String backgroundColor = isHovered ? isPressed ? "backgroundPressColor" : 
        "backgroundHoverColor" : "backgroundColor";
        setColor(style.getColorAttribute(backgroundColor));
        fillRect(x, y, width, height);
    }

    public void drawCenteredText(int x, int y, int width, int height, String text) {
        int textWidth = getStringWidth(text);
        int textHeight = getFontHeight();
        drawString(text, x + (width / 2) - (textWidth / 2), y + (height / 2) + (textHeight / 2));
    }

    public void attemptBorder(int x, int y, int width, int height, boolean isHovered) {
        if (!style.hasBorder()) { return; }
        String borderColor = isHovered ? "borderHoverColor" : "borderColor";
        setColor(style.getColorAttribute(borderColor));
        setStroke(style.getIntAttribute("borderWidth"));
        drawRect(x, y, width, height);
    }

    public void drawText(int x, int y, int width, int height, String text) {
        setFont(style.getFont());
        int textWidth = getStringWidth(text);
        String alignment = style.getAttribute("alignment");
        int padding = style.getIntAttribute("padding");
        int borderThickness = style.getIntAttribute("borderWidth");
        int wallOffset = borderThickness + padding;
        
        x += alignment.equals("center") ? 
            ((width / 2) - (textWidth / 2)) : //Centered
            alignment.equals("right") ? 
            (width - textWidth - wallOffset) : //Right aligned
        wallOffset;//Left aligned
        
        if (!alignment.equals("center") && style.hasBorder()) {
            x += (borderThickness * (alignment.equals("left") ? 1 : -1));
        }
        int drawY = y + height / 2 + getFontHeight() / 2;
        drawString(text, x, drawY);
    }

    public void drawStandardText(int x, int y, int width, int height) {
        if (style.getAttribute("text") == null) return;
        drawText(x, y, width, height, style.getAttribute("text"));
    }

    public void drawScrollbar(Style buttonStyle, int x, int y, int width, int height, 
            int scrollIndex, int compCount) {
        int scrollbarSize = style.getIntAttribute("scrollbarSize");
        int scrollerSize = Math.min((scrollbarSize * 3), width - (scrollbarSize * 2));
        int scrollbarX,scrollbarY;
        int scrollbarWidth, scrollbarHeight;
        int scrollerX, scrollerY, scrollerWidth, scrollerHeight;
        int innerScrollSize;
        int borderWidth = style.getIntAttribute("borderWidth");
        int buttonBorderWidth = buttonStyle.getIntAttribute("borderWidth");
        if (style.getBooleanAttribute("resizesVertically")) {
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
        setColor(style.getColorAttribute("scrollbarColor"));
        fillRect(scrollbarX, scrollbarY, scrollbarWidth, scrollbarHeight);
        setColor(style.getColorAttribute("scrollerBackgroundColor"));
        fillRect(scrollerX, scrollerY, scrollerWidth, scrollerHeight);
        setColor(buttonStyle.getColorAttribute("borderColor"));
        drawRect(scrollbarX, scrollbarY, scrollbarWidth, scrollbarHeight);
    }

    public void setStroke(int size) {
        if (size == strokeSize) { return; }
        g.setStroke(new java.awt.BasicStroke(size));
        strokeSize = size;
    }

    public void setFont(Font font) {
        if (this.font == font) { return; }
        this.font = font;
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
        if (text.trim().length() == 0) { return; }
        String[] chars = text.split("");
        y -= font.getDrawnHeight();
        for (String s : chars) {
            CharacterData data = font.getCharacterData(s);
            boolean[][] characterData = data.getData();
            int charIndex = 0;
            for (int i = 0; i < characterData.length; i++) {
                for (int j = 0; j < characterData[i].length; j++) {
                    if (characterData[i][j]) {
                        fillRect(x + (j * charIndex) + (j * font.getSize()), 
                            y + (i * font.getSize()), 
                            font.getSize(),font.getSize());
                    }
                }
            }
            charIndex += 1;
            x += font.getDrawnWidth(s) + font.getSize();
        }
        //g.drawString(text, x, y);
    }

    public int getFontHeight() {
        return font.getDrawnHeight();
    }

    public int getStringWidth(String text) {
        return font.getStringWidth(text);
    }

    public int getFontCharWidth(char charAt) {
        return font.getDrawnWidth(String.valueOf(charAt));
    }

    public void setStyle(Style style) {
        this.style = style;
    }

}
