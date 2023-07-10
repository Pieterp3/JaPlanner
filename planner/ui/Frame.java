package ui;

import javax.swing.JFrame;

import ui.managers.ClipboardManager;
import ui.managers.IOManager;
import util.AttributeUseTracker;
import util.events.EventManager;
import util.events.EventTimer;
import util.events.Event;
import util.io.Save;
import util.structures.List;
import util.structures.Map;

public class Frame {

    private JFrame frame;
    private Panel activePanel;
    private List<Panel> panelHistory;
    private Map<String, List<Long>> commandHistory;
    private PrimaryListener primaryListener;
    private IOManager ioManager;
    private boolean active;
    private boolean isIcon;
    private ClipboardManager clipboardManager;
    private EventManager eventManager;

    public static final int DEFAULT_CURSOR = 0;
    public static final int HAND_CURSOR = 12;
    public static final int TEXT_CURSOR = 2;
    public static final int LOADING_CURSOR = 3;
    
    public Frame() {
        frame = new JFrame();
        frame.setTitle("Java Floor Planner");
        ioManager = new IOManager(this);
        panelHistory = new List<Panel>();
        commandHistory = new Map<>();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        primaryListener = new PrimaryListener(this);
        frame.addKeyListener(primaryListener);
        frame.addMouseListener(primaryListener);
        frame.addMouseMotionListener(primaryListener);
        frame.addWindowListener(primaryListener);
        frame.addMouseWheelListener(primaryListener);
        clipboardManager = new ClipboardManager(this);
        eventManager = new EventManager();
        AttributeUseTracker.init();
    }

    public void beginEnter() {
        System.out.println("Entering...");
        frame.requestFocus();
    }

    public void scheduleEvent(Event event, int delay, boolean repeats) {
        eventManager.addEventTimer(new EventTimer(event, delay, repeats));
    }

    public void addTimer(EventTimer timer) {
        eventManager.addEventTimer(timer);
    }

    public void beginExit() {
        frame.setVisible(false);
        ioManager.save();
        saveCommands();
        frame.dispose();
    }

    public void addCommand(String command) {
        if (commandHistory.containsKey(command)) {
            commandHistory.get(command).add(System.currentTimeMillis());
        } else {
            List<Long> times = new List<>();
            times.add(System.currentTimeMillis());
            commandHistory.put(command, times);
        }
    }

    public void saveCommands() {
        List<String> lines = new List<>();
        for (String command : commandHistory.keySet()) {
            List<Long> times = commandHistory.get(command);
            String line = command + " (" + times.size() +" times):";
            for (long time : times) {
                time = System.currentTimeMillis() - time;
                time /= 1000.0;
                double newTime = Math.round(time * 100.0) / 100.0;
                line += " " + newTime +"s";
            }
            lines.add(line);
        }
        Save.saveDataFile("Commands", lines);
    }

    public boolean isIcon() {
        return isIcon;
    }

    public void setActivePanel(Panel activePanel) {
        if (this.activePanel != null) {
            panelHistory.add(this.activePanel);
            frame.remove(this.activePanel.getPanel());
        }
        this.activePanel = activePanel;
        frame.add(activePanel.getPanel());
    }

    public Panel getActivePanel() {
        return activePanel;
    }

    public void repaint() {
        frame.repaint();
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

    public boolean isUndecorated() {
        return frame.isUndecorated();
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

    public PrimaryListener getListener() {
        return primaryListener;
    }

    public ClipboardManager getClipboardManager() {
        return clipboardManager;
    }

    public void setCursor(int cursor) {
        frame.setCursor(java.awt.Cursor.getPredefinedCursor(cursor));
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    public void update() {
        eventManager.update();
        getActivePanel().update();
    }

}