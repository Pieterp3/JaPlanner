package ui.managers;

import ui.Frame;
import ui.panels.components.interfaces.Focusable;
import ui.panels.components.interfaces.RecievesText;

public class ClipboardManager {
    private String clipboard;
    private Frame frame;

    public ClipboardManager(Frame frame) {
        this.frame = frame;
    }

    public void copy(String text) {
        clipboard = text;
    }

    public String getPaste() {
        return clipboard;
    }

    public void paste() {
        if (clipboard == null) return;
        Focusable focus = frame.getActivePanel().getFocusableComponent();
        if (focus instanceof RecievesText) {
            ((RecievesText) focus).sendText(clipboard);
        }
    }

    public void cut(String text) {
        copy(text);
        Focusable focus = frame.getActivePanel().getFocusableComponent();
        if (focus instanceof RecievesText) {
            ((RecievesText) focus).sendKeycode(RecievesText.DELETE);
        }
    }

}
