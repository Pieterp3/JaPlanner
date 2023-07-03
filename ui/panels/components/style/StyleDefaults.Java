package ui.panels.components.style;

public enum StyleDefaults {
    FONT("font", "Arial"),
    FONT_SIZE("fontSize", "12"),
    FONT_STYLE("fontStyle", "0"),
    BORDER_THICKNESS("borderThickness", "1"),
    BORDER("border", "false"),
    X("x", "0"),
    Y("y", "0"),
    X_OFFSET("xOffset", "0"),
    Y_OFFSET("yOffset", "0"),
    PADDING("padding", "0"),
    WIDTH("width", "0"),
    HEIGHT("height", "0"),
    ALIGNMENT("alignment", "LEFT"),
    OPAQUE("opaque", "true"),
    DISABLED("disabled", "false"),
    DISABLED_COLOR("disabledColor", "FFF"),
    COLOR("color", "000"),
    BACKGROUND_COLOR("backgroundColor", "FFF"),
    BORDER_COLOR("borderColor", "000"),
    HOVER_COLOR("hoverColor", "000"),
    BACKGROUND_HOVER_COLOR("backgroundHoverColor", "FFF"),
    BORDER_HOVER_COLOR("borderHoverColor", "000"),
    PRESS_COLOR("pressColor", "000"),
    BACKGROUND_PRESS_COLOR("backgroundPressColor", "FFF"),
    BORDER_PRESS_COLOR("borderPressColor", "000");

    private String name, value;

    private StyleDefaults(String name, String value) {
        this.value = value;
        this.name = name;
    }

    public static String getDefaultValue(String name) {
        for (StyleDefaults styleDefault : StyleDefaults.values()) {
            if (styleDefault.name.equalsIgnoreCase(name)) {
                return styleDefault.value;
            }
        }
        return null;
    }

    public static void initializeStyle(Style style) {
        for (StyleDefaults styleDefault : StyleDefaults.values()) {
            style.setAttribute(styleDefault.name, styleDefault.value);
        }
    }
}