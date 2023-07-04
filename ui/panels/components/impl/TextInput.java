package ui.panels.components.impl;

import ui.Frame;
import ui.managers.ClipboardManager;
import ui.managers.KeyManager;
import ui.panels.components.ArtAssistant;
import ui.panels.components.DrawnComponent;
import ui.panels.components.interfaces.RecievesText;
import ui.panels.components.style.Style;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class TextInput extends DrawnComponent implements RecievesText {
  
    private String placeholder;
    private int cursorPosition = 0;
    private long lastCursorUpdate = 0;
    private boolean cursorVisible = false;
    private Graphics2D currentGraphics;
    private String selectedText;

    public TextInput(Frame frame, String text, int x, int y, int width, int height, String placeholder) {
        super(frame);
        getStyle().setText(text);
        getStyle().setX(x);
        getStyle().setY(y);
        getStyle().setWidth(width);
        getStyle().setHeight(height);
        getStyle().setAction(text);
        getStyle().addDefaultBorder();
        getStyle().setPlaceholderColor(Color.gray);
        getStyle().setPadding(2);
        this.placeholder = placeholder;
    }

    public TextInput(Frame frame) {
        this(frame, "", 0, 0, 220, 36, "Placeholder");
    }
    //TODO cursors
    @Override
    public void draw(Graphics2D g) {
        Style style = getStyle();
        if (style.isDisabled()) return;
        currentGraphics = g;
        ArtAssistant.attemptBackground(g, style, getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        ArtAssistant.attemptBorder(g, style, getX(), getY(), getWidth(), getHeight(), isHovered());
        String text = style.getText();
        if (text == null || text.isEmpty()) {
            text = placeholder;
            g.setColor(style.getPlaceholderColor());
        } else {
            g.setColor(style.getColor());
        }
        
        ArtAssistant.drawText(g, style, getX(), getY(), getWidth(), getHeight(), text);
        drawCursor(g, text, style);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public String getSelectedText() {
        return selectedText;
    }

    private void drawCursor(Graphics2D g, String text, Style style) {
        if (getFrame().getActiveTextComponent() != this) return;
        if (System.currentTimeMillis() - lastCursorUpdate > 500) {
            cursorVisible = !cursorVisible;
            lastCursorUpdate = System.currentTimeMillis();
        }
        if (!cursorVisible) return;
        g.setColor(style.getColor());
        int endIndex = Math.min(cursorPosition, text.length());
        int x = getX() + g.getFontMetrics().stringWidth(text.substring(0, endIndex)) - 2;
        int padding = style.getPadding();
        int borderThickness = style.getBorderWidth();
        int wallOffset = borderThickness + padding;
        x += wallOffset;
        int y = getY() + wallOffset;
        g.fillRect(x, y, 1, getHeight() - 8);
    }

    @Override
    public void click() {
        if (getStyle().isDisabled()) return;
        if (currentGraphics == null) return;
        String text = getStyle().getText();
        if (text == null) text = "";
        cursorPosition = text.length();//TODO cursor position
    }

    private void backspace() {
        if (getStyle().isDisabled()) return;
        String currentText = getStyle().getText();
        if (currentText == null) currentText = "";
        if (cursorPosition > 0) {
            String newText = currentText.substring(0, cursorPosition - 1) + currentText.substring(cursorPosition);
            getStyle().setText(newText);
            cursorPosition--;
        }
    }

    private void delete() {
        if (getStyle().isDisabled()) return;
        String currentText = getStyle().getText();
        if (currentText == null) currentText = "";
        if (cursorPosition < currentText.length()) {
            String newText = currentText.substring(0, cursorPosition) + currentText.substring(cursorPosition + 1);
            getStyle().setText(newText);
        }
    }

    private boolean checkShortcuts(int keyCode) {
        KeyManager listener = getFrame().getListener().getKeyManager();
        int keyModifier = listener.getModifier();
        ClipboardManager clipboardManager = getFrame().getClipboardManager();
        //TODO implement selected text in shortcuts
        if (keyModifier == RecievesText.CONTROL) {
            if (keyCode == KeyEvent.VK_C) {
                clipboardManager.copy(getStyle().getText());
                return true;
            } else if (keyCode == KeyEvent.VK_V) {
                clipboardManager.paste();
                return true;
            } else if (keyCode == KeyEvent.VK_X) {
                clipboardManager.cut(getStyle().getText());
                getStyle().setText("");
                cursorPosition = 0;
                return true;
            }
        } else if (keyModifier == RecievesText.SHIFT) {
            if (keyCode == KeyEvent.VK_LEFT) {
                cursorPosition = 0;//TODO previous whitespace index or beginning of line
                return true;
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                cursorPosition = getStyle().getText().length();//TODO next whitespace index or end of line
                return true;
            }
        }
        return false;
    }

    @Override
    public void sendKeycode(int keyCode) {
        if (getStyle().isDisabled()) return;
        if (checkShortcuts(keyCode)) return;
        //if (getFrame().checkShortcuts(keyCode)) return;//TODO implement program wide shortcuts in frame
        if (keyCode == KeyEvent.VK_ENTER) {
            getFrame().setActiveTextComponent(null);
        } else if (KeyEvent.getKeyText(keyCode).length() == 1) {
            sendText(KeyEvent.getKeyText(keyCode));
        } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            backspace();
        } else if (keyCode == KeyEvent.VK_DELETE) {
            delete();
        } else if (keyCode == KeyEvent.VK_LEFT) {
            cursorPosition = Math.max(0, cursorPosition-1);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            cursorPosition = Math.min(getStyle().getText().length(), cursorPosition+1);
        } else if (keyCode == RecievesText.END) {
            cursorPosition = getStyle().getText().length();
        } else if (keyCode == RecievesText.HOME) {
            cursorPosition = 0;
        }
    }

    //TODO enable repeated keys for held keys

    @Override
    public void sendText(String text) {
        if (getStyle().isDisabled()) return;
        String currentText = getStyle().getText();
        if (currentText == null) currentText = "";
        String newText = currentText.substring(0, cursorPosition) + text + currentText.substring(cursorPosition);
        getStyle().setText(newText);
        cursorPosition += text.length();
    }
}
