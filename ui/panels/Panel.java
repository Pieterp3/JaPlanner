// Written by: Pieter Barone
package ui.panels;

import javax.swing.JPanel;

import ui.Frame;
import ui.panels.components.DrawnComponent;
import ui.panels.components.interfaces.ContainerComponent;
import ui.panels.components.interfaces.Focusable;
import ui.panels.components.interfaces.Scrollable;

import java.util.List;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * A Panel is a JPanel that is used to display a certain part of the frame
 * at a time. It is used to separate the different parts of the frame into
 * different sections.
 * 
 */
public abstract class Panel extends JPanel {
    
    private Frame frame;
    private List<DrawnComponent> components;
    private Focusable focusedComponent;

    public Panel(Frame frame, String name) {
        super();
        setName(name);
        this.frame = frame;
        setBounds(0, 0, frame.getWidth(), frame.getHeight());
        components = new ArrayList<DrawnComponent>();
        init();
    }

    public abstract void update();
    protected abstract void init();
    public abstract void processAction(String command);
    public abstract void keyTyped(int c);
    public abstract void preComponentDrawing(Graphics2D g);
    public abstract void finishPanelDrawing(Graphics2D g);

    public void addComponent(DrawnComponent c) {
        components.add(c);
    }

    @Override
    public void paintComponent(java.awt.Graphics g2) {
        super.paintComponent(g2);
        Graphics2D g = (Graphics2D) g2;
        preComponentDrawing(g);
        for (DrawnComponent c : components) {
            c.draw(g);
        }
        finishPanelDrawing(g);
    }

    public void setFocusableComponent(Focusable c) {
        focusedComponent = c;
    }

    public void scroll(int scroll) {
        if (focusedComponent != null && focusedComponent instanceof Scrollable) {
            ((Scrollable) focusedComponent).scroll(scroll);
        }
    }
    
    public Frame getFrame() {   
        return frame;
    }

    public void mouseMoved(int x, int y) {
        DrawnComponent foundHover = null;
        for (DrawnComponent c : components) {
            if (c.checkHover(x, y)) foundHover = c;
            if (c instanceof ContainerComponent) {
                ContainerComponent cc = (ContainerComponent) c;
                if (cc.mouseMoved(x, y)) foundHover = c;
            }
        }
        if (foundHover == null) {
            frame.setCursor(Cursor.DEFAULT_CURSOR);
        } else if (foundHover instanceof Focusable) {
            focusedComponent = (Focusable) foundHover;
        }
    }

    public List<DrawnComponent> getDrawnComponents() {
        return components;
    }

    public void drag(int toX, int toY) {

    }

    public Focusable getFocusableComponent() {
        return focusedComponent;
    }
}
