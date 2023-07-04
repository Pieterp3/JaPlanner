package ui.components.style;

import ui.components.DrawnComponent;
import structures.Map;
import ui.graphics.Color;

import java.awt.Font;

public class Style {

    private Map<String, String> attributes = new Map<String, String>();
    private DrawnComponent component;
    
    public Style(DrawnComponent component) {
        this.component = component;
        StyleDefaults.initializeStyle(this);
    }

    public DrawnComponent getComponent() {
        return component;
    }

    // Direct access to attributes
    private void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public void assignDefaultAttribute(String name, String value) {
        if (!hasAttribute(name)) {
            setAttribute(name, value);
        }
    }

    private String getAttribute(String key) {
        return attributes.get(key);
    }

    private int getIntAttribute(String key) {
        return (int) getFloatAttribute(key);
    }

    private void setIntAttribute(String key, int value) {
        setAttribute(key, Integer.toString(value));
    }

    private Color getColorAttribute(String key) {
        String attr = getAttribute(key);
        if (attr == null) {
            return Color.black;
        }
        return Color.decode("0x" + getAttribute(key));
    }

    private void setColorAttribute(String key, Color value) {
        setAttribute(key, Integer.toHexString(value.getRGB()).substring(2));
    }

    private float getFloatAttribute(String key) {
        String attr = getAttribute(key);
        if (attr == null) {
            return 0;
        }
        return Float.parseFloat(getAttribute(key));
    }

    @SuppressWarnings("unused")
    private void setFloatAttribute(String key, float value) {
        setAttribute(key, Float.toString(value));
    }

    private boolean getBooleanAttribute(String key) {
        String attr = getAttribute(key);
        if (attr == null) {
            return false;
        }
        return Boolean.parseBoolean(getAttribute(key));
    }

    private void setBooleanAttribute(String key, boolean value) {
        setAttribute(key, Boolean.toString(value));
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        for (String key : attributes.keySet()) {
            setAttribute(key, attributes.get(key));
        }
    }

    public boolean hasAttribute(String key) {
        return attributes.containsKey(key);
    }

    public boolean matchesAttribute(String key, String value) {
        return attributes.get(key.trim()).equalsIgnoreCase(value.trim());
    }

    // Setters for common attributes

    public void addDefaultBorder() {
        setHasBorder(true);
        setBorderWidth(2);
        setBorderColor(Color.BLACK);
        setBorderHoverColor(Color.BLACK.brighter());
        setBorderPressColor(Color.BLACK.darker());
    }

    public void setX(int x) {
        setIntAttribute("x", x);
    }

    public void setY(int y) {
        setIntAttribute("y", y);
    }
    
    public void setYOffset(int y) {
        setIntAttribute("yOffset", y);
    }

    public void setXOffset(int x) {
        setIntAttribute("xOffset", x);
    }

    public void setAction(String action) {
        setAttribute("action", action);
    }

    public void setColor(Color color) {
        setColorAttribute("color", color);
    }

    public void setSelectedTextColor(Color color) {
        setColorAttribute("selectedTextColor", color);
    }

    public Color getSelectedTextColor() {
        return getColorAttribute("selectedTextColor");
    }

    public void setAlignment(String alignment) {
        setAttribute("alignment", alignment);
    }

    public void setResizesVertically(boolean resizesVertically) {
        setBooleanAttribute("resizesVertically", resizesVertically);
    }


    public void setResizesHorizontally(boolean resizesHorizontally) {
        setBooleanAttribute("resizesHorizontally", resizesHorizontally);
    }

    public void setDisabled(boolean disabled) {
        setBooleanAttribute("disabled", disabled);
    }

    public void setBackgroundHoverColor(Color backgroundHoverColor) {
        setColorAttribute("backgroundHoverColor", backgroundHoverColor);
    }

    public void setBackgroundPressColor(Color backgroundPressColor) {
        setColorAttribute("backgroundPressColor", backgroundPressColor);
    }

    public void setBorderHoverColor(Color borderHoverColor) {
        setColorAttribute("borderHoverColor", borderHoverColor);
    }

    public void setBorderPressColor(Color borderPressColor) {
        setColorAttribute("borderPressColor", borderPressColor);
    }

    public void setBorderDisabledColor(Color borderDisabledColor) {
        setColorAttribute("borderDisabledColor", borderDisabledColor);
    }

    public void setPadding(int padding) {
        setIntAttribute("padding", padding);
    }

    public void setDisabledColor(Color disabledColor) {
        setColorAttribute("disabledColor", disabledColor);
    }

    public void setHoverColor(Color hoverColor) {
        setColorAttribute("hoverColor", hoverColor);
    }

    public void setPressColor(Color pressColor) {
        setColorAttribute("pressColor", pressColor);
    }

    public void setOpaque(boolean opaque) {
        setBooleanAttribute("opaque", opaque);
    }

    public void setBackgroundColor(Color backgroundColor) {
        setColorAttribute("backgroundColor", backgroundColor);
    }

    public void setWidth(int width) {
        setIntAttribute("width", width);
    }

    public void setHeight(int height) {
        setIntAttribute("height", height);
    }

    public void setFont(Font font) {
        setAttribute("font", font.getName());
        setIntAttribute("fontSize", font.getSize());
        setIntAttribute("fontStyle", font.getStyle());
    }

    public void setFont(String fontName, int fontStyle, int fontSize) {
        setAttribute("font", fontName);
        setIntAttribute("fontSize", fontSize);
        setIntAttribute("fontStyle", fontStyle);
    }

    public void setFontSize(int fontSize) {
        setIntAttribute("fontSize", fontSize);
    }

    public void setFontStyle(int fontStyle) {
        setIntAttribute("fontStyle", fontStyle);
    }

    public void setFontName(String fontName) {
        setAttribute("font", fontName);
    }

    public void setText(String text) {
        setAttribute("text", text);
    }

    public void setBorderWidth(int borderWidth) {
        setIntAttribute("borderWidth", borderWidth);
    }

    public void setPlaceholderColor(Color gray) {
        setColorAttribute("placeholderColor", gray);
    }

    public void setBorderColor(Color borderColor) { 
        setColorAttribute("borderColor", borderColor);
    }
    
    public void setHasBorder(boolean border) {
        setBooleanAttribute("hasBorder", border);
    }

    //Getters for common attributes
    
    public int getX() {
        return getIntAttribute("x");
    }

    public int getY() {
        return getIntAttribute("y");
    }

    public int getXOffset() {
        return getIntAttribute("xOffset");
    }

    public int getYOffset() {
        return getIntAttribute("yOffset");
    }

    public Color getColor() {
        return getColorAttribute("color");
    }

    public String getAlignment() {
        return getAttribute("alignment");
    }

    public Color getPlaceholderColor() {
        return getColorAttribute("placeholderColor");
    }

    public boolean isOpaque() {
        return getBooleanAttribute("opaque");
    }

    public boolean isDisabled() {
        return getBooleanAttribute("disabled");
    }

    public Color getBackgroundHoverColor() {
        return getColorAttribute("backgroundHoverColor");
    }

    public String getAction() {
        return getAttribute("action");
    }

    public Color getBackgroundPressColor() {
        return getColorAttribute("backgroundPressColor");
    }

    public Color getBorderHoverColor() {
        return getColorAttribute("borderHoverColor");
    }

    public Color getBorderPressColor() {
        return getColorAttribute("borderPressColor");
    }

    public boolean getResizesVertically() {
        return getBooleanAttribute("resizesVertically");
    }
    public boolean getResizesHorizontally() {
        return getBooleanAttribute("resizesHorizontally");
    }

    public int getPadding() {
        return getIntAttribute("padding");
    }

    public Color getBorderDisabledColor() {
        return getColorAttribute("borderDisabledColor");
    }

    public Color getHoverColor() {
        return getColorAttribute("hoverColor");
    }

    public Color getPressColor() {
        return getColorAttribute("pressColor");
    }

    public Color getDisabledColor() {
        return getColorAttribute("disabledColor");
    }

    public Color getBackgroundColor() {
        return getColorAttribute("backgroundColor");
    }
    
    public int getWidth() {
        return getIntAttribute("width");
    }

    public Font getFont() {
        return new Font(getAttribute("font"), getIntAttribute("fontStyle"), getIntAttribute("fontSize"));
    }

    public int getHeight() {
        return getIntAttribute("height");
    }

    public int getFontSize() {
        return getIntAttribute("fontSize");
    }

    public int getFontStyle() {
        return getIntAttribute("fontStyle");
    }

    public String getFontName() {
        return getAttribute("font");
    }

    public String getText() {
        return getAttribute("text");
    }

    public int getBorderWidth() {
        return getIntAttribute("borderWidth");
    }

    public Color getBorderColor() {
        return getColorAttribute("borderColor");
    }

    public boolean hasBorder() {
        return getBooleanAttribute("hasBorder");
    }

    public void setScrollbarColor(Color gray) {
        setColorAttribute("scrollbarColor", gray);
    }

    public void setScrollerBackgroundColor(Color lightgray) {
        setColorAttribute("scrollerBackgroundColor", lightgray);
    }

    public void setScrollbarSize(int size) {
        setIntAttribute("scrollbarSize", size);
    }

    public int getScrollbarSize() {
        return getIntAttribute("scrollbarSize");
    }

    public Color getScrollbarColor() {
        return getColorAttribute("scrollbarColor");
    }

    public Color getScrollerBackgroundColor() {
        return getColorAttribute("scrollerBackgroundColor");
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setLocation(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setBounds(int x, int y, int width, int height) {
        setLocation(x, y);
        setSize(width, height);
    }

    public void setScrollMultiplier(int i) {
        setIntAttribute("scrollMultiplier", i);
    }

    public int getScrollMultiplier() {
        return getIntAttribute("scrollMultiplier");
    }

    

}