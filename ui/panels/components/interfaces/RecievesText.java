package ui.panels.components.interfaces;

public interface RecievesText extends Focusable {
    public final int BACKSPACE = 8;
    public final int ENTER = 10;
    public final int SHIFT = 16;
    public final int CTRL = 17;
    public final int CONTROL = CTRL;
    public final int ALT = 18;
    public final int ESCAPE = 27;
    public final int SPACE = 32;
    public final int LEFT = 37;
    public final int UP = 38;
    public final int RIGHT = 39;
    public final int DOWN = 40;
    public final int DELETE = 127;
    public final int HOME = 36;
    public final int END = 35;


    public static final int[] modifiers = {SHIFT, CTRL, ALT, HOME, END};

    public void sendKeycode(int keyCode);
    public void sendText(String text);

}
