package ui.components.style;


import ui.components.DrawnComponent;
import ui.graphics.Color;
import ui.graphics.fonts.Font;
import ui.graphics.fonts.impl.DefaultFont;
import util.AttributeUseTracker;
import util.io.Load;
import util.structures.Map;

public class Style {

    private static Map<String, String> defaultAttributes;
    private Map<String, String> attributes;
    private DrawnComponent component;

    public Style(DrawnComponent component) {
        this.component = component;
        defaultAttributes = Load.loadCFG("StyleDefaults");
        attributes = new Map<>(defaultAttributes);
    }

    public DrawnComponent getComponent() {
        return component;
    }

    public void setAttribute(String key, Object value) {
        attributes.put(key, value.toString());
        AttributeUseTracker.updateStat(key, value.toString());
    }

    public String getAttribute(String key) {
        String value = attributes.get(key);
        AttributeUseTracker.setFetchedByUser(key);
        return value;
    }

    public int getIntAttribute(String key) {
        return (int) getFloatAttribute(key);
    }

    public Color getColorAttribute(String key) {
        String attr = getAttribute(key);
        if (attr == null) {
            return Color.black;
        }
        return Color.decode(getAttribute(key));
    }

    public void setColorAttribute(String key, Color color) {
        setAttribute(key, color.toAttributeString());
    }

    public float getFloatAttribute(String key) {
        String attr = getAttribute(key);
        if (attr == null) {
            return 0;
        }
        return Float.parseFloat(getAttribute(key));
    }

    public boolean getBooleanAttribute(String key) {
        String attr = getAttribute(key);
        if (attr == null) {
            return false;
        }
        return Boolean.parseBoolean(getAttribute(key));
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public boolean hasAttribute(String key) {
        return attributes.containsKey(key);
    }

    public boolean matchesAttribute(String key, String value) {
        return attributes.get(key.trim()).equalsIgnoreCase(value.trim());
    }

    public void addDefaultBorder() {
        setAttribute("borderWidth", 2);
        setColorAttribute("borderColor", Color.black);
        setColorAttribute("borderHoverColor", Color.black.brighter());
        setColorAttribute("borderPressColor", Color.black.darker());
    }

    public void setAttributes(Map<String, Object> map) {
        for (String key : map.getKeys()) {
            setAttribute(key, map.get(key));
        }
    }

    public boolean hasBorder() {
        return getIntAttribute("borderWidth") > 0;
    }

    public Font getFont() {
        return new DefaultFont(getIntAttribute("fontStyle"), getIntAttribute("fontSize"));
    }
}