package ui;

import ui.managers.KeyManager;
import ui.managers.MouseManager;

import java.awt.event.*;

public class PrimaryListener implements MouseListener, KeyListener, MouseMotionListener, WindowListener, MouseWheelListener {

    private MouseManager mouseManager;
    private long mousePressedTime;
    private KeyManager keyManager;
    private Frame frame;
    private long lastClick;

    public PrimaryListener(Frame frame) {
        super();
        mouseManager = new MouseManager(frame);
        keyManager = new KeyManager(frame);
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (System.currentTimeMillis() - lastClick > 5) {
            mouseManager.click(e.getPoint().x, e.getPoint().y);
            lastClick = System.currentTimeMillis();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseManager.press(e.getPoint().x, e.getPoint().y);
        mousePressedTime = System.currentTimeMillis();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseManager.release(e.getPoint().x, e.getPoint().y);
        if (System.currentTimeMillis() - mousePressedTime < 500) {
            if (System.currentTimeMillis() - lastClick > 5) {
                mouseManager.click(e.getPoint().x, e.getPoint().y);
                lastClick = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseManager.setMouseInFrame(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseManager.setMouseInFrame(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyManager.keyTyped(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyManager.press(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyManager.release(e.getKeyCode());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseManager.drag(e.getPoint().x, e.getPoint().y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseManager.setMousePosition(e.getPoint().x, e.getPoint().y);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        frame.beginEnter();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        frame.beginExit();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        frame.exit();
    }

    @Override
    public void windowIconified(WindowEvent e) {
        frame.setIconState(true);
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        frame.setIconState(true);
    }

    @Override
    public void windowActivated(WindowEvent e) {
        frame.setActive(true);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        frame.setActive(true);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseManager.scroll(e.getWheelRotation());
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
    
}
