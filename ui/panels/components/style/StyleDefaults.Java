package ui.panels.components;

import java.awt.Color;
import java.awt.Font;

public enum StyleDefaults {
    FONT("Font", "Arial"),
    FONT_SIZE("Font Size", "12"),
    FONT_STYLE("Font Style", Integer.toString(Font.PLAIN)),
    COLOR("Color", "000"),
    BACKGROUND_COLOR("Background color", "FFF"),
    BORDER_COLOR("Border color", "000"),
    BORDER_THICKNESS("Border thickness", 1),
    BORDER("Border", "false"),
    X("X", "0"),
    Y("Y", "0"),
    WIDTH("Width", "0"),
    HEIGHT("Height", "0"),
    ALIGNMENT("Alignment", "LEFT"),
    OPAQUE("Opaque", "true"),
    DISABLED("Disabled", "false"),
    );

    private static final String defaultText = "Button";
    private static final int defaultWidth = 60;
    private static final int defaultHeight = 18;
    private static final int defaultX = 0;
    private static final int defaultY = 0;

    private static final int defaultBorderWidth = 3;
    private static final boolean defaultBorder = true;
    private static final boolean defaultEnabled = true;
    private static final Color defaultDisabledColor = Color.white;
    private static final boolean defaultOpaque = true;

    private static final Color defaultFontColor = Color.white;
    private static final Color defaultBackgroundColor = Color.black;
    private static final Color defaultBorderColor = Color.gray;
    
    private static final Color defaultHoverColor = Color.black;
    private static final Color defaultBackgroundHoverColor = Color.black;
    private static final Color defaultBorderHoverColor = Color.gray;

    private static final Color defaultPressColor = Color.lightGray;
    private static final Color defaultBackgroundPressColor = Color.black;
    private static final Color defaultBorderPressColor = Color.gray;

    private String name, value;

    private StyleDefaults(String name, String value) {
        this.value = value;
        this.name = name;
    }

    public static String getDefaultValue(String name) {
        for (StyleDefaults styleDefault : StyleDefaults.values()) {
            if (styleDefault.name().equalsIgnoreCase(name)) {
                return styleDefault.value;
            }
        }
        return null;
    }

    public static void initializeStyle(Style style) {
        for (StyleDefaults styleDefault : StyleDefaults.values()) {
            style.setAttribute(styleDefault.name(), styleDefault.value);
        }
    }
}