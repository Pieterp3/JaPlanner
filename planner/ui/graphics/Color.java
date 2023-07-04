package ui.graphics;

public class Color {
    
    private int r, g, b;

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color(String hex) {
        this.r = Integer.parseInt(hex.substring(1, 3), 16);
        this.g = Integer.parseInt(hex.substring(3, 5), 16);
        this.b = Integer.parseInt(hex.substring(5, 7), 16);
    }

    public Color(java.awt.Color decoded) {
        this.r = decoded.getRed();
        this.g = decoded.getGreen();
        this.b = decoded.getBlue();
    }

    public java.awt.Color getAwtColor() {
        return new java.awt.Color(r, g, b);
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getR() {
        return this.r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getG() {
        return this.g;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getB() {
        return this.b;
    }

    public String getHex() {
        return "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
    }

    public static Color getContrastColor(Color color) {
        int r = color.getR();
        int g = color.getG();
        int b = color.getB();
        int yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000;
        return yiq >= 128 ? new Color(0, 0, 0) : new Color(255, 255, 255);
    }

    public static Color getContrastColor(String hex) {
        return getContrastColor(new Color(hex));
    }

    public static Color getHoverColor(Color color) {
        int r = color.getR();
        int g = color.getG();
        int b = color.getB();
        int yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000;
        return yiq >= 128 ? new Color(r - 20, g - 20, b - 20) : new Color(r + 20, g + 20, b + 20);
    }

    public static Color getHoverColor(String hex) {
        return getHoverColor(new Color(hex));
    }

    public static Color getPressColor(Color color) {
        int r = color.getR();
        int g = color.getG();
        int b = color.getB();
        int yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000;
        return yiq >= 128 ? new Color(r - 40, g - 40, b - 40) : new Color(r + 40, g + 40, b + 40);
    }

    public static Color getPressColor(String hex) {
        return getPressColor(new Color(hex));
    }

    public static Color getDisabledColor(Color color) {
        int r = color.getR();
        int g = color.getG();
        int b = color.getB();
        int yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000;
        return yiq >= 128 ? new Color(r - 60, g - 60, b - 60) : new Color(r + 60, g + 60, b + 60);
    }

    public static Color getDisabledColor(String hex) {
        return getDisabledColor(new Color(hex));
    }

    public static Color decode(String string) {
        return new Color(java.awt.Color.decode(string));
    }

    public int getRGB() {
        return getAwtColor().getRGB();
    }




}
