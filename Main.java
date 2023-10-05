import floorplanning.display.PlannerTestPanel;
import ui.Panel;
import ui.frames.Frame;
import util.structures.List;

public class Main {
    private static Frame frame;
    private static List<Panel> panels;
    public static final boolean DEBUG = true;

    public static void main(String[] args) {
        frame = new Frame("Java Floor planner", 1200, 840);
        panels = new List<Panel>();
        panels.add(new PlannerTestPanel(frame));
        Panel devPanel = panels.get(DEBUG ? panels.size() - 1 : 0);
        frame.setActivePanel(devPanel);
        frame.setVisible(true);
    }

    // speechEngine = new SpeechEngine();
    // panels.add(new SpeechPanel(frame, (SpeechEngine) speechEngine));
    // drawnEngine.start();
    // Speaker.speak("Hi baby! What do you want to watch?");
    // speechEngine.start();
}
