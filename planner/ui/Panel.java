// Written by: Pieter Barone
package ui;

import javax.swing.JPanel;

import ui.components.DrawnComponent;
import ui.components.containers.ContainerComponent;
import ui.components.interfaces.Dragable;
import ui.components.interfaces.Focusable;
import ui.components.interfaces.Scrollable;
import ui.graphics.Graphics;
import util.structures.List;

public abstract class Panel implements Comparable<Panel> {
    
    private JPanel panel;
    private Frame frame;
    private Graphics g = new Graphics();
    private List<DrawnComponent> components;
    private Focusable focusedComponent;
    private long creationTime = System.currentTimeMillis();

    public Panel(Frame frame, String name) {
        panel = new JPanel() {
            @Override
            public void paintComponent(java.awt.Graphics g2) {
                super.paintComponent(g2);
                g.updateGraphics((java.awt.Graphics2D) g2);
                preComponentDrawing();
                for (DrawnComponent c : components) {
                    c.updateGraphicsStyle(g);
                }
                finishPanelDrawing();
            }
        };
        panel.setName(name);
        this.frame = frame;
        panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        components = new List<DrawnComponent>();
        init();
    }

    public abstract void update();
    protected abstract void init();
    public abstract void handleAction(String command);
    public abstract void keyTyped(int c);
    public abstract void preComponentDrawing();
    public abstract void finishPanelDrawing();

    public void processAction(String command) {
        frame.addCommand(command);
        handleAction(command);
    }

    public Graphics getGraphics() {
        return g;
    }

    public void addComponent(DrawnComponent c) {
        components.add(c);
    }

    public void setFocusableComponent(Focusable c) {
        focusedComponent = c;
    }

    public void scroll(int scroll) {
        if (focusedComponent != null && focusedComponent instanceof Scrollable) {
            ((Scrollable) focusedComponent).scroll(scroll);
        }
    }
    
    @Override
    public int compareTo(Panel o) {
        return (int) (creationTime - o.creationTime);
    }

    public Frame getFrame() {   
        return frame;
    }

    public void mouseMoved(int x, int y) {
        for (DrawnComponent c : components) {
            if (c instanceof ContainerComponent) {
                ContainerComponent cc = (ContainerComponent) c;
                cc.mouseMoved(x, y);
            }
        }
    }

    public void beginDrags(int x, int y) {
        for (DrawnComponent c : components) {
            if (c instanceof Dragable) {
                ((Dragable) c).setDragStart(x, y);
            }
        }
    }

    public void drop(int x, int y) {
        for (DrawnComponent c : components) {
            if (c instanceof Dragable) {
                ((Dragable) c).drop(x, y);
            }
        }
    }

    public List<DrawnComponent> getDrawnComponents() {
        return components;
    }

    public void drag(int startX, int startY, int toX, int toY) {   
        for (DrawnComponent c : components) {
            if (c instanceof Dragable) {
                ((Dragable) c).drag(toX, toY);
            }
        }
    }

    public Focusable getFocusableComponent() {
        return focusedComponent;
    }

    public int getWidth() {
        return panel.getWidth();
    }
    
    public int getHeight() {
        return panel.getHeight();
    }

    public int getX() {
        return panel.getX();
    }

    public int getY() {
        return panel.getY();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void repaint() {
        panel.repaint();
    }
}
