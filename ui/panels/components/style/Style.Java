package ui.panels.components.style;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.Font;
import ui.panels.components.DrawnComponent;

public class Style {

    private Map<String, String> attributes = new HashMap<String, String>();
    private DrawnComponent component;
    
    public Style(DrawnComponent component) {
        this.component = component;
        StyleDefaults.initializeStyle(this);
    }

    public DrawnComponent getComponent() {
        return component;
    }

    // Direct access to attributes
    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public String getAttribute(String key) {
        return attributes.get(key);
    }

    public int getIntAttribute(String key) {
        return (int) getFloatAttribute(key);
    }

    public Color getColorAttribute(String key) {
        return Color.decode("0x" + getAttribute(key));
    }

    public float getFloatAttribute(String key) {
        return Float.parseFloat(getAttribute(key));
    }

    public boolean getBooleanAttribute(String key) {
        return Boolean.parseBoolean(getAttribute(key));
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
        setAttribute("x", Integer.toString(x));
    }

    public void setY(int y) {
        setAttribute("y", Integer.toString(y));
    }
    
    public void setAction(String action) {
        setAttribute("action", action);
    }

    public void setColor(Color color) {
        setAttribute("color", Integer.toHexString(color.getRGB()).substring(2));
    }

    public void setAlignment(String alignment) {
        setAttribute("alignment", alignment);
    }

    public void setDisabled(boolean disabled) {
        setAttribute("disabled", Boolean.toString(disabled));
    }

    public void setBackgroundHoverColor(Color backgroundHoverColor) {
        setAttribute("backgroundHoverColor", Integer.toHexString(backgroundHoverColor.getRGB()).substring(2));
    }

    public void setBackgroundPressColor(Color backgroundPressColor) {
        setAttribute("backgroundPressColor", Integer.toHexString(backgroundPressColor.getRGB()).substring(2));
    }

    public void setBorderHoverColor(Color borderHoverColor) {
        setAttribute("borderHoverColor", Integer.toHexString(borderHoverColor.getRGB()).substring(2));
    }

    public void setBorderPressColor(Color borderPressColor) {
        setAttribute("borderPressColor", Integer.toHexString(borderPressColor.getRGB()).substring(2));
    }

    public void setBorderDisabledColor(Color borderDisabledColor) {
        setAttribute("borderDisabledColor", Integer.toHexString(borderDisabledColor.getRGB()).substring(2));
    }

    public void setDisabledColor(Color disabledColor) {
        setAttribute("disabledColor", Integer.toHexString(disabledColor.getRGB()).substring(2));
    }

    public void setHoverColor(Color hoverColor) {
        setAttribute("hoverColor", Integer.toHexString(hoverColor.getRGB()).substring(2));
    }

    public void setPressColor(Color pressColor) {
        setAttribute("pressColor", Integer.toHexString(pressColor.getRGB()).substring(2));
    }

    public void setOpaque(boolean opaque) {
        setAttribute("opaque", Boolean.toString(opaque));
    }

    public void setBackgroundColor(Color backgroundColor) {
        setAttribute("backgroundColor", Integer.toHexString(backgroundColor.getRGB()).substring(2));
    }

    public void setWidth(int width) {
        setAttribute("width", Integer.toString(width));
    }

    public void setHeight(int height) {
        setAttribute("height", Integer.toString(height));
    }

    public void setFont(Font font) {
        setAttribute("font", font.getName());
        setAttribute("fontSize", Float.toString(font.getSize()));
        setAttribute("fontStyle", Float.toString(font.getStyle()));
    }

    public void setFont(String fontName, int fontStyle, int fontSize) {
        setAttribute("font", fontName);
        setAttribute("fontSize", Integer.toString(fontSize));
        setAttribute("fontStyle", Integer.toString(fontStyle));
    }

    public void setFontSize(int fontSize) {
        setAttribute("fontSize", Integer.toString(fontSize));
    }

    public void setFontStyle(int fontStyle) {
        setAttribute("fontStyle", Integer.toString(fontStyle));
    }

    public void setFontName(String fontName) {
        setAttribute("font", fontName);
    }

    public void setText(String text) {
        setAttribute("text", text);
    }

    public void setBorderWidth(int borderWidth) {
        setAttribute("borderWidth", Integer.toString(borderWidth));
    }

    public void setBorderColor(Color borderColor) { 
        setAttribute("borderColor", Integer.toHexString(borderColor.getRGB()).substring(2));
    }
    
    public void setHasBorder(boolean border) {
        setAttribute("hasBorder", Boolean.toString(border));
    }

    //Setters for common attributes

    
    public int getX() {
        return getIntAttribute("x");
    }

    public int getY() {
        return getIntAttribute("y");
    }

    public Color getColor() {
        return getColorAttribute("color");
    }

    public String getAlignment() {
        return getAttribute("alignment");
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
}