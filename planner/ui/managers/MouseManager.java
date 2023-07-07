package ui.managers;

import ui.Frame;
import ui.components.DrawnComponent;
import ui.components.interfaces.ContainerComponent;
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
        return mousePosition.getX();
    }

    public int getMouseY() {
        return mousePosition.getY();
    }

    public int getLastPressedX() {
        return lastPressedPosition.getX();
    }

    public int getLastPressedY() {
        return lastPressedPosition.getY();
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

    public void checkHovers() {
        DrawnComponent foundComponent = null;
        for (DrawnComponent c : frame.getActivePanel().getDrawnComponents()) {
            c.setSetHovered(c.contains(getMouseX(), getMouseY()));
            if (c.isHovered()) {
                c.setHoveredCursor(getMouseX(), getMouseY());
                c.setPressed(mousePressed);
                foundComponent = c;
            } else {
                c.setPressed(false);
            }
            if (c instanceof ContainerComponent) {
                ContainerComponent cc = (ContainerComponent) c;
                List<DrawnComponent> components = cc.getComponents();
                for (DrawnComponent ccc : components) {
                    ccc.setSetHovered(ccc.contains(getMouseX(), getMouseY()));
                    if (ccc.isHovered()) {
                        ccc.setHoveredCursor(getMouseX(), getMouseY());
                        ccc.setPressed(mousePressed);
                        foundComponent = ccc;
                    } else {
                        ccc.setPressed(false);
                    }
                }
            }
        }
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
