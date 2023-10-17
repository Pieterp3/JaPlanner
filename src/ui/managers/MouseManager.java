package ui.managers;

import ui.components.DrawnComponent;
import ui.components.containers.ContainerComponent;
import ui.components.interfaces.Focusable;
import ui.frames.Frame;
import util.math.Point;
import util.structures.lists.List;

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
        // frame.getActivePanel().mouseMoved(x, y, mousePressed);
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

    private void click(DrawnComponent c, int x, int y) {
        c.click(x, y);
        if (c instanceof Focusable) {
            frame.getActivePanel().setFocusableComponent((Focusable) c);
        }
    }

    private boolean checkComponentClick(List<DrawnComponent> components, int x, int y, DrawnComponent container) {
        for (DrawnComponent c : components) {
            if (c instanceof ContainerComponent) {
                if (!checkComponentClick(((ContainerComponent) c).getComponents(), x, y, c)) {
                    if (c.contains(x, y)) {
                        click(c, x, y);
                        return true;
                    }
                }
            } else if (c.contains(x, y)) {
                if (container == null) {
                    click(c, x, y);
                } else {
                    container.click(x, y);
                }
                return true;
            }
        }
        return false;
    }

    private void click() {
        if (System.currentTimeMillis() - lastClickTime < 5)
            return;
        lastClickTime = System.currentTimeMillis();
        if (!checkComponentClick(frame.getActivePanel().getDrawnComponents(), getMouseX(), getMouseY(), null)) {
            frame.getActivePanel().setFocusableComponent(null);
        }
    }

    public void scroll(int wheelRotation) {
        frame.getActivePanel().scroll(wheelRotation);
        checkHovers();
    }

    private List<DrawnComponent> checkHovers(List<DrawnComponent> hovers) {
        List<DrawnComponent> foundComponents = new List<>();
        for (DrawnComponent c : hovers) {
            if (c.getAttribute("disabled") != null && c.getStyle().getBooleanAttribute("disabled"))
                continue;
            c.setSetHovered(c.contains(getMouseX(), getMouseY()));
            if (c.isHovered()) {
                foundComponents.add(c);
                c.setPressed(mousePressed);
            } else {
                c.setPressed(false);
            }
        }
        return foundComponents;
    }

    public void checkHovers() {
        List<DrawnComponent> foundComponents = checkHovers(frame.getActivePanel().getDrawnComponents());
        if (foundComponents.isEmpty()) {
            frame.setCursor(Frame.DEFAULT_CURSOR);
            return;
        }
        ContainerComponent container;
        if ((container = componentsContainsContainer(foundComponents)) != null) {
            foundComponents = checkHovers(container.getComponents());
        }
        if (foundComponents.isEmpty()) {
            frame.setCursor(Frame.DEFAULT_CURSOR);
            return;
        }
        if (foundComponents.size() > 1) {
            for (DrawnComponent c : foundComponents) {
                if (c instanceof Focusable) {
                    frame.getActivePanel().setFocusableComponent((Focusable) c);
                }
            }
        } else {
            DrawnComponent foundComponent = foundComponents.get(0);
            if (foundComponent instanceof Focusable) {
                frame.getActivePanel().setFocusableComponent((Focusable) foundComponent);
            }
        }
        foundComponents.get(0).setHoveredCursor(getMouseX(), getMouseY());
    }

    private ContainerComponent componentsContainsContainer(List<DrawnComponent> components) {
        for (DrawnComponent c : components) {
            if (c instanceof ContainerComponent) {
                return (ContainerComponent) c;
            }
        }
        return null;
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
