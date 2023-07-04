package ui.managers;

import ui.Frame;
import ui.panels.components.interfaces.RecievesText;

public class ClipboardManager {
    private String clipboard;
    private Frame frame;

    public ClipboardManager(Frame frame) {
        super();
        this.frame = frame;
    }

    public void copy(String text) {
        clipboard = text;
    }

    public String getPaste() {
        return clipboard;
    }

    public void paste() {
        if (clipboard != null) {
            frame.getActiveTextComponent().sendText(clipboard);
        }
    }

    public void cut(String text) {
        copy(text);
        frame.getActiveTextComponent().sendKeycode(RecievesText.DELETE);
    }

}
