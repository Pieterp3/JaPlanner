package ui.managers;

import ui.Frame;
import ui.components.DrawnComponent;
import ui.components.containers.ContainerComponent;
import ui.components.interfaces.Focusable;
import util.math.Point;
import util.structures.List;

public class MouseManager {
    
    private Point mousePosition;
    private boolean mousePressed;
    private Point lastPressedPosition;
    private boolean mouseInFrame;
    private long mousePressedTime, lastClickTime;
    private Frame frame;

    public MouseManager(Frame frame) {
        super();
        this.frame = frame;
        mousePosition = new Point(0, 0);
        lastPressedPosition = new Point(0, 0);
    }

    public void setMouseInFrame(boolean inFrame) {
        this.mouseInFrame = inFrame;
    }

    public boolean isMouseInFrame() {
        return mouseInFrame;
    }

    public void setMousePosition(int x, int y) {
        y = fixY(y);
        x = fixX(x);
        mousePosition.setLocation(x, y);
        checkHovers();
        //frame.getActivePanel().mouseMoved(x, y, mousePressed);
    }

    public void setLastMousePosition(int x, int y) {
        y = fixY(y);
        x = fixX(x);
        lastPressedPosition.setLocation(x, y);
    }

    public long getClickDuration() {
        return System.currentTimeMillis() - mousePressedTime;
    }

    private int fixX(int x) {
        return frame.isUndecorated() ? x : x - 8;
    }

    private int fixY(int y) {
        return frame.isUndecorated() ? y : y - 32;
    }

    public int getMouseX() {
        return mousePosition.getIntX();
    }

    public int getMouseY() {
        return mousePosition.getIntY();
    }

    public int getLastPressedX() {
        return lastPressedPosition.getIntX();
    }

    public int getLastPressedY() {
        return lastPressedPosition.getIntY();
    }

    private void click() {
        if (System.currentTimeMillis() - lastClickTime < 5) return;
        lastClickTime = System.currentTimeMillis();
        int y = getMouseY();
        int x = getMouseX();
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

    public void scroll(int wheelRotation) {
        frame.getActivePanel().scroll(wheelRotation);
        checkHovers();
    }

    private DrawnComponent checkHovers(List<DrawnComponent> hovers) {
        DrawnComponent foundComponent = null;
        for (DrawnComponent c : hovers) {
            c.setSetHovered(c.contains(getMouseX(), getMouseY()));
            if (c.isHovered()) {
                c.setHoveredCursor(getMouseX(), getMouseY());
                c.setPressed(mousePressed);
                foundComponent = c;
            } else {
                c.setPressed(false);
            }
            if (c instanceof ContainerComponent) {
                if (foundComponent instanceof Focusable) {frame.getActivePanel().setFocusableComponent((Focusable) foundComponent);}
                foundComponent = checkHovers(((ContainerComponent) c).getComponents());
            }
        }
        return foundComponent;
    }

    public void checkHovers() {
        DrawnComponent foundComponent = checkHovers(frame.getActivePanel().getDrawnComponents());
        if (foundComponent == null) {
            frame.setCursor(Frame.DEFAULT_CURSOR);
        } else if (foundComponent instanceof Focusable) {
            frame.getActivePanel().setFocusableComponent((Focusable) foundComponent);
        }
    }

    public void press(int x, int y) {
        setLastMousePosition(x, y);
        mousePressed = true;
        setMousePosition(x, y);
        mousePressedTime = System.currentTimeMillis();
    }

    public void release(int x, int y) {
        frame.getActivePanel().drop(fixX(x), fixY(y));
        mousePressed = false;
        setMousePosition(x, y);
        if (System.currentTimeMillis() - mousePressedTime < 500) {
            if (System.currentTimeMillis() - lastClickTime > 5) {
                click();
                lastClickTime = System.currentTimeMillis();
            }
        }
    }
    
}
