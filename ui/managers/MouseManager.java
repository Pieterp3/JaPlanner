package ui.managers;

import ui.Frame;
import ui.panels.components.DrawnComponent;
import ui.panels.components.interfaces.RecievesText;

public class MouseManager {
    
    private int mouseX, mouseY;
    private int lastX, lastY;
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
        return lastX;
    }

    public int getLastPressedY() {
        return lastY;
    }

    public void click(int x, int y) {
        setLastPressedPosition(x,y);//Call before fixY because setLastPressedPosition uses the unmodified y value
        y=fixY(y);
        x=fixX(x);
        boolean setActiveTextComponent = false;
        for (DrawnComponent c : frame.getActivePanel().getDrawnComponents()) {
            if (c.contains(x, y)) {
                c.click(x, y);
                if (c instanceof RecievesText) {
                    frame.setActiveTextComponent((RecievesText) c);
                    setActiveTextComponent = true;
                }
            }
        }
        if (!setActiveTextComponent) {
            frame.setActiveTextComponent(null);
        }
    }

    public void setLastPressedPosition(int x, int y) {
        y=fixY(y);
        x=fixX(x);
        lastX = x;
        lastY = y;
    }

    public void drag(int fromX, int fromY, int toX, int toY) {
        fromY=fixY(fromY);
        toY=fixY(toY);
        fromX=fixX(fromX);
        toX=fixX(toX);
        setLastPressedPosition(toX, toY);
    }

    public void move(int x, int y) {
        setMousePosition(x, y);
    }

    public void scroll(int wheelRotation) {
        frame.getActivePanel().scroll(wheelRotation);
    }

}
