package ui.managers;

import ui.Frame;
import ui.components.interfaces.Focusable;
import ui.components.interfaces.RecievesText;

public class KeyManager {
    
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
        for (int i = 0; i < RecievesText.modifiers.length; i++) {
            if (isHeld(RecievesText.modifiers[i])) {
                return RecievesText.modifiers[i];
            }
        }
        return -1;
    }




}
