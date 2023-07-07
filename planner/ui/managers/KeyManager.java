package ui.managers;

import java.awt.event.KeyEvent;

import ui.Frame;
import ui.components.interfaces.Focusable;
import ui.components.interfaces.RecievesText;

public class KeyManager {
    
    public static final int BACKSPACE = 8;
    public static final int ENTER = 10;
    public static final int SHIFT = 16;
    public static final int CTRL = 17;
    public static final int CONTROL = CTRL;
    public static final int ALT = 18;
    public static final int ESCAPE = 27;
    public static final int SPACE = 32;
    public static final int LEFT = 37;
    public static final int UP = 38;
    public static final int RIGHT = 39;
    public static final int DOWN = 40;
    public static final int DELETE = 127;
    public static final int HOME = 36;
    public static final int END = 35;
    public static final int A = 65;
    public static final int B = 66;
    public static final int C = 67;
    public static final int D = 68;
    public static final int E = 69;
    public static final int F = 70;
    public static final int G = 71;
    public static final int H = 72;
    public static final int I = 73;
    public static final int J = 74;
    public static final int K = 75;
    public static final int L = 76;
    public static final int M = 77;
    public static final int N = 78;
    public static final int O = 79;
    public static final int P = 80;
    public static final int Q = 81;
    public static final int R = 82;
    public static final int S = 83;
    public static final int T = 84;
    public static final int U = 85;
    public static final int V = 86;
    public static final int W = 87;
    public static final int X = 88;
    public static final int Y = 89;
    public static final int Z = 90;
    public static final int ZERO = 48;

    public static final int[] modifiers = {SHIFT, CTRL, ALT, HOME, END};

    private long[] keyPresses = new long[65535];
    private long[] keyReleases = new long[65535];
    private Frame frame;

    public KeyManager(Frame frame) {
        super();
        this.frame = frame;
    }

    public void press(int keyCode) {
        if (keyCode == 0) return;
        long lastPress = keyPresses[keyCode];
        if (System.currentTimeMillis() - lastPress < 100) return;
        keyTyped(keyCode);
        keyPresses[keyCode] = System.currentTimeMillis();
    }

    public void release(int keyCode) {
        // if (keyCode == 0) return;
        // keyReleases[keyCode] = System.currentTimeMillis();
        // if (keyReleases[keyCode] - keyPresses[keyCode] < 500) {
        //     keyTyped(keyCode);
        // }
    }

    public boolean isHeld(int keyCode) {
        return keyPresses[keyCode] > keyReleases[keyCode];
    }

    public boolean isReleased(int keyCode) {
        return keyReleases[keyCode] > keyPresses[keyCode];
    }

    public boolean isPressed(int keyCode) {
        return keyPresses[keyCode] > keyReleases[keyCode] && System.currentTimeMillis() - keyPresses[keyCode] < 500;
    }

    public void keyTyped(int keyCode) {
        if (keyCode == 0) return;
        Focusable focus = frame.getActivePanel().getFocusableComponent();
        if (focus instanceof RecievesText) {
            ((RecievesText) focus).sendKeycode(keyCode);
        } else {
            frame.getActivePanel().keyTyped(keyCode);
        }
    }

    public int getModifier() {
        for (int i = 0; i < modifiers.length; i++) {
            if (isHeld(modifiers[i])) {
                return modifiers[i];
            }
        }
        return -1;
    }

    public static String getKeyText(int keyCode) {
        return KeyEvent.getKeyText(keyCode);
    }

}
