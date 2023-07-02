package ui.listeners;
import ui.Panel;

import java.awt.event.*;
import managers.MouseManager;

public class PrimaryListener implements MouseListener, KeyListener, MouseMotionListener, WindowListener, ActionListener {

    private MouseManager mouseManager;
    private long mousePressedTime;
    private Panel panel;

    public PrimaryListener(Panel panel) {
        super();
        mouseManager = new MouseManager();
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseManager.click(e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressedTime = System.currentTimeMillis();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (System.currentTimeMillis() - mousePressedTime < 500) {
            mouseManager.click(e.getPoint());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseManager.drag(mouseManager.getMousePosition(), e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseManager.setMousePosition(e.getPoint());
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowClosing(WindowEvent e) {
        panel.beginExit();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }
    
}
