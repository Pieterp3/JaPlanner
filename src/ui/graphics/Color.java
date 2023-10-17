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

    public String toString() {
        return "Color[r=" + r + ",g=" + g + ",b=" + b + "]";
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
        return color.getContrastColor();
    }

    public Color getContrastColor() {
        int r = getR();
        int g = getG();
        int b = getB();
        int yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000;
        return yiq >= 128 ? new Color(0, 0, 0) : new Color(255, 255, 255);
    }

    public static Color getComplimentaryColor(Color color) {
        return color.getComplimentaryColor();
    }

    public Color getComplimentaryColor() {
        int r = getR();
        int g = getG();
        int b = getB();
        return new Color(255 - r, 255 - g, 255 - b);
    }

    public static Color getContrastColor(String hex) {
        return getContrastColor(new Color(hex));
    }

    public static Color decode(String string) {
        return new Color(java.awt.Color.decode("0x" + string));
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
    public static final Color[] defaultColors = new Color[] { black, white, red, green, blue, yellow, orange, pink,
            magenta, cyan, gray, lightGray, darkGray };

    public String toAttributeString() {
        return Integer.toHexString(getRGB()).substring(2);
    }

    public static Color randomDefaultColor() {
        return defaultColors[(int) (Math.random() * defaultColors.length)];
    }

    /**
     * Split all colors in a gradiant from red>green>blue>red
     * Returns a color from the gradiant
     */
    public static Color getGradientColor(int stepIndex, int gradientSteps) {
        int r = 0;
        int g = 0;
        int b = 0;
        int step = (gradientSteps - 1) / 3;
        if (stepIndex < step) {
            r = 255;
            g = (int) (255 * ((double) stepIndex / step));
        } else if (stepIndex < 2 * step) {
            r = (int) (255 * (1 - ((double) (stepIndex - step) / step)));
            g = 255;
        } else if (stepIndex < 3 * step) {
            g = (int) (255 * (1 - ((double) (stepIndex - 2 * step) / step)));
            b = 255;
        } else {
            g = 0;
            b = (int) (255 * (1 - ((double) (stepIndex - 3 * step) / step)));
        }
        return new Color(r, g, b);
    }

    /**
     * Splits colors in a gradient from white>@color>black
     */
    public static Color getGradientColor(Color color, int step, int stepCount) {
        if (step < stepCount / 2) {
            return getGradientColor(white, step, stepCount / 2, color);
        } else {
            return getGradientColor(color, step - stepCount / 2, stepCount / 2, black);
        }
    }

    /**
     * Splits colors in a gradient from @color1>@color2
     */
    public static Color getGradientColor(Color color1, int step, int stepCount, Color color2) {
        int r = color1.getR() + (int) ((color2.getR() - color1.getR()) * ((double) step / stepCount));
        int g = color1.getG() + (int) ((color2.getG() - color1.getG()) * ((double) step / stepCount));
        int b = color1.getB() + (int) ((color2.getB() - color1.getB()) * ((double) step / stepCount));
        return new Color(r, g, b);
    }

}
