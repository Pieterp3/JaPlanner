package ui.components.impl;


import ui.Frame;
import ui.components.DrawnComponent;
import ui.components.interfaces.Dragable;
import ui.components.interfaces.RecievesText;
import ui.components.style.Style;
import ui.managers.ClipboardManager;
import ui.managers.KeyManager;
import util.math.Misc;
import util.structures.Map;
import ui.graphics.Graphics;
import ui.graphics.Color;

/**
 * Todo:
 * 10. Add spellcheck to text type text inputs
 * 
 */

public class UserInput extends DrawnComponent implements RecievesText, Dragable {

    private String placeholder;
    private int cursorPosition = 0;
    private long lastCursorUpdate = 0;
    private boolean cursorVisible = false;
    private int selectedStartIndex, selectedStopIndex;

    public UserInput(Frame frame, String text, int x, int y, int width, int height, String placeholder) {
        super(frame);
        setAttributes(new Map<>() {
            {
                put("text", text);
                put("x", x);
                put("y", y);
                put("width", width);
                put("height", height);
                put("action", text);
                put("padding", 2);
            }
        });
        style.addDefaultBorder();
        style.setColorAttribute("placeholder", Color.gray);
        style.setColorAttribute("selectedTextColor", Color.gray);
        this.placeholder = placeholder;
    }

    public UserInput(Frame frame) {
        this(frame, "", 0, 0, 220, 36, "Placeholder");
    }

    @Override
    public void draw(Graphics g, Style style) {
        g.drawBackground(getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        g.attemptBorder(getX(), getY(), getWidth(), getHeight(), isHovered());

        // Draw background behind selected text
        String text = getText();
        if (selectedStartIndex != selectedStopIndex) {
            int padding = style.getIntAttribute("padding");
            int borderThickness = style.getIntAttribute("borderWidth");
            int wallOffset = borderThickness + padding;
            int textX = getX() + wallOffset;
            int textY = getY() + wallOffset;
            int textHeight = g.getFontHeight();
            int startIndex = Math.min(selectedStartIndex, selectedStopIndex);
            int stopIndex = Math.max(selectedStartIndex, selectedStopIndex);
            String selectedText = text.substring(startIndex, stopIndex);
            int selectedTextWidth = g.getStringWidth(selectedText);
            g.setColor(style.getColorAttribute("selectedTextColor"));
            int drawY = textY + getHeight() / 2 + g.getFontHeight() / 2 - textHeight - 1;
            g.fillRect(textX + g.getStringWidth(text.substring(0, startIndex)), drawY, selectedTextWidth, textHeight);
        }
        if (text == null || text.isEmpty()) {
            text = placeholder;
            g.setColor(style.getColorAttribute("placeholderColor"));
        } else {
            g.setColor(style.getColorAttribute("color"));
        }

        g.drawText(getX(), getY(), getWidth(), getHeight(), text);
        drawCursor(g, text, style);
    }

    public String getText() {
        return style.getAttribute("text");
    }

    public void setText(String text) {
        style.setAttribute("text", text);
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

    private int getSelectedStartIndex() {
        return Math.min(selectedStartIndex, selectedStopIndex);
    }

    private int getSelectedStopIndex() {
        return Math.max(selectedStartIndex, selectedStopIndex);
    }

    private boolean hasSelectedText() {
        return selectedStartIndex != selectedStopIndex;
    }

    public String getSelectedText() {
        if (!hasSelectedText())
            return null;
        return getText().substring(Math.max(0, getSelectedStartIndex()),
                Math.min(getText().length(), getSelectedStopIndex()));
    }

    private void drawCursor(Graphics g, String text, Style style) {
        if (getPanel().getFocusableComponent() != this)
            return;
        if (System.currentTimeMillis() - lastCursorUpdate > 500) {
            cursorVisible = !cursorVisible;
            lastCursorUpdate = System.currentTimeMillis();
        }
        if (!cursorVisible)
            return;
        g.setColor(style.getColorAttribute("color"));
        int endIndex = Math.min(cursorPosition, text.length());
        int x = getX() + g.getStringWidth(text.substring(0, endIndex)) - 2;
        int padding = style.getIntAttribute("padding");
        int borderThickness = style.getIntAttribute("borderWidth");
        int wallOffset = borderThickness + padding;
        x += wallOffset;
        int y = getY() + wallOffset;
        g.fillRect(x, y, 1, getHeight() - 8);
    }

    @Override
    public void click(int x, int y) {
        resetSelectedText();
        if (style.getBooleanAttribute("disabled"))
            return;
        if (g == null)
            return;
        String text = getText();
        if (text == null)
            text = "";
        int padding = style.getIntAttribute("padding");
        int borderThickness = style.getIntAttribute("borderWidth");
        int wallOffset = borderThickness + padding;
        int textX = getX() + wallOffset;
        int textY = getY() + wallOffset;
        int textWidth = g.getStringWidth(text);
        int textHeight = g.getFontHeight();
        if (x >= textX && x <= textX + textWidth && y >= textY && y <= textY + textHeight) {
            int index = x - textX;
            for (int i = 0; i < text.length(); i++) {
                int charWidth = g.getFontCharWidth(text.charAt(i));
                if (index < charWidth) {
                    cursorPosition = i;
                    break;
                }
                index -= charWidth;
            }
            getPanel().setFocusableComponent(this);
        } else {
            getPanel().setFocusableComponent(null);
        }
    }

    private void backspace() {
        if (style.getBooleanAttribute("disabled"))
            return;
        String currentText = getText();
        if (currentText == null)
            return;
        if (getSelectedText() != null) {
            String newText = currentText.substring(0, Math.max(0, getSelectedStartIndex())) +
                    currentText.substring(getSelectedStopIndex());
            setText(newText);
            cursorPosition = Math.max(0, getSelectedStartIndex());
            resetSelectedText();
            return;
        }
        if (cursorPosition > 0) {
            String newText = currentText.substring(0, cursorPosition - 1) + currentText.substring(cursorPosition);
            setText(newText);
            cursorPosition--;
        }
    }

    private void resetSelectedText() {
        selectedStartIndex = -1;
        selectedStopIndex = -1;
    }

    private void delete() {
        if (style.getBooleanAttribute("disabled"))
            return;
        String currentText = getText();
        if (currentText == null)
            return;
        if (getSelectedText() != null) {
            String newText = currentText.substring(0, Math.max(0, getSelectedStartIndex())) +
                    currentText.substring(getSelectedStopIndex());
            setText(newText);
            cursorPosition = Math.max(0, getSelectedStartIndex());
            resetSelectedText();
            return;
        }
        if (cursorPosition < currentText.length()) {
            String newText = currentText.substring(0, cursorPosition) + currentText.substring(cursorPosition + 1);
            setText(newText);
        }
    }

    private boolean checkShortcuts(int keyCode) {
        KeyManager listener = getFrame().getListener().getKeyManager();
        int keyModifier = listener.getModifier();
        ClipboardManager clipboardManager = getFrame().getClipboardManager();
        if (keyModifier == KeyManager.CONTROL) {
            if (keyCode == KeyManager.C) {
                if (hasSelectedText())
                    clipboardManager.copy(getSelectedText());
                else
                    clipboardManager.copy(getText());
                return true;
            } else if (keyCode == KeyManager.V) {
                clipboardManager.paste();
                return true;
            } else if (keyCode == KeyManager.X) {
                if (hasSelectedText()) {
                    clipboardManager.copy(getSelectedText());
                    String newText = getText().substring(0, Math.max(0, getSelectedStartIndex())) +
                            getText().substring(getSelectedStopIndex());
                    setText(newText);
                    cursorPosition = Math.max(0, getSelectedStartIndex());
                    resetSelectedText();
                    return true;
                }
                clipboardManager.cut(getText());
                setText("");
                cursorPosition = 0;
                return true;
            }
        } else if (keyModifier == KeyManager.SHIFT) {
            if (keyCode == KeyManager.LEFT) {
                String text = getText().substring(0, cursorPosition);
                int lastWhitespace = text.lastIndexOf(" ");
                if (lastWhitespace == -1)
                    lastWhitespace = 0;
                cursorPosition = lastWhitespace;
                return true;
            } else if (keyCode == KeyManager.RIGHT) {
                String text = getText().substring(cursorPosition);
                int nextWhitespace = text.indexOf(" ");
                if (nextWhitespace == -1)
                    nextWhitespace = text.length();
                cursorPosition += nextWhitespace;
                return true;
            }
        }
        return false;
    }

    @Override
    public void sendKeycode(int keyCode) {
        if (style.getBooleanAttribute("disabled"))
            return;
        if (checkShortcuts(keyCode))
            return;
        // if (getFrame().checkShortcuts(keyCode)) return;//TODO implement program wide
        // shortcuts in frame
        if (keyCode == KeyManager.ENTER) {
            getPanel().setFocusableComponent(null);
        } else if (keyCode == KeyManager.BACKSPACE) {
            backspace();
        } else if (keyCode == KeyManager.DELETE) {
            delete();
        } else if (keyCode == KeyManager.LEFT) {
            cursorPosition = Math.max(0, cursorPosition - 1);
        } else if (keyCode == KeyManager.RIGHT) {
            cursorPosition = Math.min(getText().length(), cursorPosition + 1);
        } else if (keyCode == KeyManager.END) {
            cursorPosition = getText().length();
        } else if (keyCode == KeyManager.HOME) {
            cursorPosition = 0;
        } else if (keyCode == KeyManager.SPACE) {
            sendText(" ");
        }
        if (KeyManager.getKeyText(keyCode).length() > 1) {
            System.out.println("Keycode: " + keyCode + " " + KeyManager.getKeyText(keyCode));
            return;
        }
        KeyManager listener = getFrame().getListener().getKeyManager();
        int keyModifier = listener.getModifier();
        if (keyModifier == KeyManager.SHIFT) {
            if (Misc.isInteger(KeyManager.getKeyText(keyCode))) {
                String capNums = ")!@#$%^&*(";
                sendText(capNums.substring(keyCode - KeyManager.ZERO, keyCode - KeyManager.ZERO + 1));
            }
        } else {
            sendText(KeyManager.getKeyText(keyCode));
        }
    }

    @Override
    public void sendText(String text) {
        if (style.getBooleanAttribute("disabled"))
            return;
        if (g == null)
            return;
        String currentText = getText();
        if (currentText == null)
            currentText = "";
        if (text.length() > 1) {
            for (int i = 0; i < text.length(); i++) {
                sendText(text.substring(i, i + 1));
            }
            return;
        }
        if (hasSelectedText()) {
            String newText = currentText.substring(0, Math.max(0, getSelectedStartIndex())) +
                    text + currentText.substring(getSelectedStopIndex());
            setText(newText);
            cursorPosition = Math.max(0, getSelectedStartIndex()) + text.length();
            resetSelectedText();
            return;
        }
        String newText = currentText.substring(0, cursorPosition) + text + currentText.substring(cursorPosition);
        if (g.getStringWidth(newText) > getWidth())
            return;
        setText(newText);
        cursorPosition += text.length();
    }

    @Override
    public void setHoveredCursor(int x, int y) {
        getFrame().setCursor(Frame.TEXT_CURSOR);
    }

    @Override
    public void setDragStart(int x, int y) {
        if (style.getBooleanAttribute("disabled"))
            return;
        if (g == null)
            return;
        String text = getText();
        if (text == null)
            text = "";
        int padding = style.getIntAttribute("padding");
        int borderThickness = style.getIntAttribute("borderWidth");
        int wallOffset = borderThickness + padding;
        int textX = getX() + wallOffset;
        int textY = getY() + wallOffset;
        int textWidth = g.getStringWidth(text);
        int textHeight = g.getFontHeight();
        if (x >= textX && x <= textX + textWidth && y >= textY && y <= textY + textHeight) {
            int index = x - textX;
            for (int i = 0; i < text.length(); i++) {
                int charWidth = g.getFontCharWidth(text.charAt(i));
                if (index < charWidth) {
                    selectedStartIndex = i;
                    selectedStopIndex = i;
                    cursorPosition = i;
                    break;
                }
                index -= charWidth;
            }
        }
    }

    @Override
    public void drop(int x, int y) {
        if (style.getBooleanAttribute("disabled"))
            return;
        if (g == null)
            return;
        String text = getText();
        if (text == null)
            text = "";
        int padding = style.getIntAttribute("padding");
        int borderThickness = style.getIntAttribute("borderWidth");
        int wallOffset = borderThickness + padding;
        int textX = getX() + wallOffset;
        int textY = getY() + wallOffset;
        int textWidth = g.getStringWidth(text);
        int textHeight = g.getFontHeight();
        if (x >= textX && x <= textX + textWidth && y >= textY && y <= textY + textHeight) {
            int index = x - textX;
            for (int i = 0; i < text.length(); i++) {
                int charWidth = g.getFontCharWidth(text.charAt(i));
                if (index < charWidth) {
                    selectedStopIndex = i;
                    cursorPosition = i;
                    break;
                }
                index -= charWidth;
            }
        }
    }

    @Override
    public void drag(int x, int y) {
        if (style.getBooleanAttribute("disabled"))
            return;
        if (g == null)
            return;
        if (selectedStartIndex == -1)
            return;
        String text = getText();
        if (text == null)
            text = "";
        int padding = style.getIntAttribute("padding");
        int borderThickness = style.getIntAttribute("borderWidth");
        int wallOffset = borderThickness + padding;
        int textX = getX() + wallOffset;
        int textY = getY() + wallOffset;
        int textWidth = g.getStringWidth(text);
        int textHeight = g.getFontHeight();
        if (x >= textX && x <= textX + textWidth && y >= textY && y <= textY + textHeight) {
            int index = x - textX;
            for (int i = 0; i < text.length(); i++) {
                int charWidth = g.getFontCharWidth(text.charAt(i));
                if (index < charWidth) {
                    selectedStopIndex = i;
                    cursorPosition = i;
                    break;
                }
                index -= charWidth;
            }
        }
    }
}
