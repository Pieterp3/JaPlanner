package ui.managers;

import ui.Frame;

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
        keyPresses[keyCode] = System.currentTimeMillis();
    }

    public void release(int keyCode) {
        if (keyCode == 0) return;
        keyReleases[keyCode] = System.currentTimeMillis();
        if (keyReleases[keyCode] - keyPresses[keyCode] < 500) {
            keyTyped(keyCode);
        }
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
        if (frame.getActiveTextComponent() != null) {
            frame.getActiveTextComponent().sendKeycode(keyCode);
        } else {
            frame.getActivePanel().keyTyped(keyCode);
        }
    }




}
