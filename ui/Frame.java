package ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import ui.listeners.PrimaryListener;
import ui.managers.ClipboardManager;
import ui.managers.IOManager;
import ui.panels.Panel;
import ui.panels.components.interfaces.RecievesText;

public class Frame extends JFrame {
    private Panel activePanel;
    private List<Panel> panelHistory;
    private PrimaryListener primaryListener;
    private IOManager ioManager;
    private boolean active;
    private boolean isIcon;
    private RecievesText recievesText;
    private ClipboardManager clipboardManager;
    
    public Frame() {
        setTitle("Java Floor Planner");
        ioManager = new IOManager(this);
        panelHistory = new ArrayList<Panel>();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        primaryListener = new PrimaryListener(this);
        addKeyListener(primaryListener);
        addMouseListener(primaryListener);
        addMouseMotionListener(primaryListener);
        addWindowListener(primaryListener);
        addMouseWheelListener(primaryListener);
        clipboardManager = new ClipboardManager(this);
    }

    public void beginEnter() {
        System.out.println("Entering...");
        requestFocus();
    }

    public void beginExit() {
        setVisible(false);
        ioManager.save();
        dispose();
    }

    public boolean isIcon() {
        return isIcon;
    }

    public void setActivePanel(Panel activePanel) {
        if (this.activePanel != null) {
            panelHistory.add(this.activePanel);
            remove(this.activePanel);
        }
        this.activePanel = activePanel;
        add(activePanel);
    }

    public Panel getActivePanel() {
        return activePanel;
    }

    @Override
    public void repaint() {
        super.repaint();
        if (activePanel != null) {
            activePanel.repaint();
        }
    }

    public void goBack() {
        if (panelHistory.size() > 0) {
            setActivePanel(panelHistory.get(panelHistory.size() - 1));
            panelHistory.remove(panelHistory.size() - 1);
        }
    }

    public IOManager getIOManager() {
        return ioManager;
    }

    public void setActive(boolean active) {
        this.active = active;
        System.out.println("Window is now " + (active ? "active" : "inactive") + ".");
    }

    public boolean isActive() {
        return active;
    }

    public void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }

    public void setIconState(boolean isIcon) {
        this.isIcon = isIcon;
        System.out.println("Window is now " + (isIcon ? "iconified" : "not iconified") + ".");
    }

    public void setActiveTextComponent(RecievesText c) {
        this.recievesText = c;
    }

    public RecievesText getActiveTextComponent() {
        return recievesText;
    }

    public PrimaryListener getListener() {
        return primaryListener;
    }

    public ClipboardManager getClipboardManager() {
        return clipboardManager;
    }

}