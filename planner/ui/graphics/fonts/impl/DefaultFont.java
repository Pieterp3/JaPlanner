package ui.graphics.fonts.impl;

import ui.graphics.fonts.Font;

public class DefaultFont extends Font {

    public DefaultFont(int style, int size) {
        super(style, size);
    }

    public DefaultFont(int size) {
        super(size);
    }

    public DefaultFont() {
        super();
    }

    @Override
    protected void initCharacters() {
        for (DefaultChar ch : DefaultChar.values()) {
            setCharacterData(ch.getCharacter(), ch.getCharacterData());
        }
    }

    @Override
    public int getMinimumSize() {
        return 5;
    }

    @Override
    protected void setAcceptableStyles() {
        setAcceptableStyles(new int[] { PLAIN, BOLD, ITALIC, BOLD + ITALIC });
    }

}
