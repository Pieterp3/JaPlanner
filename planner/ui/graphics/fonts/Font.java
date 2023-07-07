package ui.graphics.fonts;

import util.structures.List;
import util.structures.Map;

public abstract class Font {

    /**Shit to do when font done so i dont get distracted
     * 1. Load default styles through static means and retrieve the defaults when attributes arent found
     * 2. Announce when default attributes are looked for but not found
     * 3. Create script to make and edit fonts
     * 4. 
     */

    public static final int PLAIN = 0;
    public static final int BOLD = 1;
    public static final int ITALIC = 2;
    private static final String minimumAcceptableCharacters = 
        "ABCDEFGHIJKLMNOP?QRSTUVWXYZ +-/*='\"1234567890.,<>/\\";
    private final List<Integer> acceptableStyles = new List<>();

    //Stops fonts from being too small accidentally
    private final int minimumSize;

    private Map<String, CharacterData> characters = new Map<>();
    private int size;
    private int style;
    private int maxHeight;

    public Font(int style, int size) {
        this.style = style;
        minimumSize = getMinimumSize();
        setSize(size);
        setAcceptableStyles();
        initCharacters();
    }

    public Font(int size) {
        this(size, PLAIN);
    }

    public Font() {
        this(12, PLAIN);
    }

    protected abstract void initCharacters();
    public abstract int getMinimumSize();
    protected abstract void setAcceptableStyles();

    protected String getMinimumChars() {
        return minimumAcceptableCharacters;
    }

    public String getAcceptableChars() {
        String chars = "";
        for (String s : characters.getKeys()) {
            chars += s;
        }
        return chars;
    }

    public CharacterData getCharacterData(String character) {
        if (character.length() > 1) {
            character = character.substring(0, 1);
        } else if (character.length() == 0) {
            return null;
        }
        CharacterData data = characters.get(character);
        if (data == null) {
            character = character.toUpperCase();
            data = characters.get(character);
        }
        if (data == null) {
            character = character.toLowerCase(); 
            data = characters.get(character);
        }
        try {
            if (data== null) {
                String error = "\nCharacter: '" + character + "'\nAcceptable Characters: " + getAcceptableChars()+"\n Does Contain: " + getAcceptableChars().contains(character);
                throw new UnsupportedCharacterException(error);
            }
        } catch (UnsupportedCharacterException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return data;
    }

    public void setCharacterData(String character, CharacterData data) {
        characters.put(character, data);
        maxHeight = Math.max(maxHeight, data.getData().length);
    }

    public int getStringWidth(String text) {
        int width = 0;
        String[] chars = text.split("");
        for (String s : chars) {
            CharacterData data = getCharacterData(s);
            if (data == null) {  continue; }
            width += getDrawnWidth(s) + getSize();
        }
        return width;
    }

    public int getDrawnWidth(String s) {
        CharacterData data = getCharacterData(s);
        if (data == null) { return 0; }
        return data.getData()[0].length * getSize();
    }

    public int getSize() {
        return size;
    }

    public int getStyle() {
        return style;
    }

    public void setSize(int size) {
        if (size <= 0) {
            this.size = 1;
            return;
        }
        this.size = size;
    }

    public void setStyle(int style) {
        if (!acceptableStyles.contains(style)) {
            this.style = PLAIN;
            return;
        }
        this.style = style;
    }

    public boolean isBold() {
        return style == BOLD || style == BOLD + ITALIC;
    }

    public boolean isItalic() {
        return style == ITALIC || style == BOLD + ITALIC;
    }

    public boolean isPlain() {
        return style == PLAIN;
    }

    public boolean isAcceptableStyle(int style) {
        return acceptableStyles.contains(style);
    }

    public boolean isAcceptableSize(int size) {
        return size >= minimumSize;
    }

    protected void setAcceptableStyles(int[] styles) {
        for (int style : styles) {
            acceptableStyles.add(style);
        }
    }
    
    protected void addAcceptableStyle(int style) {
        acceptableStyles.add(style);
    }

    public int getDrawnHeight() {
        return maxHeight * getSize();
    }
}
