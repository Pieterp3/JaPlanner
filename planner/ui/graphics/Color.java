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

    public Color brighter() {
        return new Color(getAwtColor().brighter());
    }

    public Color darker() {
        return new Color(getAwtColor().darker());
    }

    public static final Color black = new Color(java.awt.Color.black);
    public static final Color white = new Color(java.awt.Color.white);
    public static final Color red = new Color(java.awt.Color.red);
    public static final Color green = new Color(java.awt.Color.green);
    public static final Color blue = new Color(java.awt.Color.blue);
    public static final Color yellow = new Color(java.awt.Color.yellow);
    public static final Color orange = new Color(java.awt.Color.orange);
    public static final Color pink = new Color(java.awt.Color.pink);
    public static final Color magenta = new Color(java.awt.Color.magenta);
    public static final Color cyan = new Color(java.awt.Color.cyan);
    public static final Color gray = new Color(java.awt.Color.gray);
    public static final Color lightGray = new Color(java.awt.Color.lightGray);
    public static final Color darkGray = new Color(java.awt.Color.darkGray);

}