package ui.graphics.fonts;

public class CharacterData {

    private boolean[][] data;
    private String character;

    public CharacterData(String character, boolean[][] data) {
        this.data = data;
        this.character = character;
    }

    public boolean[][] getData() {
        return data;
    }

    public void setData(boolean[][] data) {
        this.data = data;
    }

    public String getCharacterString() {
        return character;
    }

}
