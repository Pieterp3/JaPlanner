package ui.managers;

import ui.Frame;
import ui.panels.components.DrawnComponent;
import ui.panels.components.interfaces.Focusable;

public class MouseManager {
    
    private int mouseX, mouseY;
    private int lastPressedX, lastPressedY;
    private int dragStartX, dragStartY;
    private long lastClick;
    private boolean mouseInFrame;
    private Frame frame;

    public MouseManager(Frame frame) {
        super();
        this.frame = frame;
    }

    public void setMouseInFrame(boolean inFrame) {
        this.mouseInFrame = inFrame;
    }

    public boolean isMouseInFrame() {
        return mouseInFrame;
    }

    public void setMousePosition(int x, int y) {
        y=fixY(y);
        x=fixX(x);
        mouseX = x;
        mouseY = y;
        frame.getActivePanel().mouseMoved(x, y);
    }

    private int fixX(int x) {
        return x -= 8;
    }

    private int fixY(int y) {
        return y -= 32;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getLastPressedX() {
        return lastPressedX;
    }

    public int getLastPressedY() {
        return lastPressedY;
    }

    public void click(int x, int y) {
        if (System.currentTimeMillis() - lastClick < 5) {
            return;
        }
        lastClick = System.currentTimeMillis();
        setLastPressedPosition(x,y);
        y=fixY(y);
        x=fixX(x);
        boolean setActiveTextComponent = false;
        for (DrawnComponent c : frame.getActivePanel().getDrawnComponents()) {
            if (c.contains(x, y)) {
                c.click(x, y);
                if (c instanceof Focusable) {
                    frame.getActivePanel().setFocusableComponent((Focusable) c);
                    setActiveTextComponent = true;
                }
            }
        }
        if (!setActiveTextComponent) {
            frame.getActivePanel().setFocusableComponent(null);
        }
    }

    public void setLastPressedPosition(int x, int y) {
        y=fixY(y);
        x=fixX(x);
        lastPressedX = x;
        lastPressedY = y;
    }

    public void drag(int toX, int toY) {
        toY=fixY(toY);
        toX=fixX(toX);
        setLastPressedPosition(toX, toY);
        frame.getActivePanel().drag(dragStartX, dragStartY, toX, toY);
    }

    public void move(int x, int y) {
        setMousePosition(x, y);
    }

    public void scroll(int wheelRotation) {
        frame.getActivePanel().scroll(wheelRotation);
    }

    /*
     * Just Used for dragging
     */
    public void press(int x, int y) {
        frame.getActivePanel().beginDrags(dragStartX = fixX(x), dragStartY = fixY(y));
    }

    /*
     * Just used for dragging
     */
    public void release(int x, int y) {
        frame.getActivePanel().drop(fixX(x), fixY(y));
    }

    public int getDragStartX() {
        return dragStartX;
    }

    public int getDragStartY() {
        return dragStartY;
    }
    
}
