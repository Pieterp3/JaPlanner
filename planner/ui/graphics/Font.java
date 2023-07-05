package ui.graphics;

public class Font extends java.awt.Font {

    public Font(java.awt.Font font) {
        super(font);
    }

    public Font(String fontName, int fontStyle, int fontSize) {
        this(new java.awt.Font(fontName, fontStyle, fontSize));
    }

    public Font() {
        this(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
    }
    
}
