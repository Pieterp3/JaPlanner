import ui.Frame;
import ui.Panel;
import structures.List;

public class Main {
    private static Frame frame;
    private static List<Panel> panels;
    public static final boolean DEBUG = true;
    private static Engine engine;

    public static void main(String[] args) {
        frame = new Frame();
        engine = new Engine(frame);
        panels = new List<Panel>();
        panels.add(new HomePanel(frame));

        //Last panel added is the active panel for dev purposes.
        Panel devPanel = panels.get(DEBUG ? panels.size() - 1 : 0);
        frame.setActivePanel(devPanel);
        frame.setVisible(true);
        engine.start();
    }
}
