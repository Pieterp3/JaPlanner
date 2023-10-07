import floorplanning.display.panels.WelcomePanel;
import ui.Panel;
import ui.frames.Frame;

public class Main {
    private static Frame frame;
    public static final boolean DEBUG = true;

    public static void main(String[] args) {
        frame = new Frame("Java Floor planner", 1200, 840);
        Panel welcome = new WelcomePanel(frame);
        frame.setActivePanel(welcome);
        frame.setVisible(true);
    }

    // speechEngine = new SpeechEngine();
    // panels.add(new SpeechPanel(frame, (SpeechEngine) speechEngine));
    // drawnEngine.start();
    // Speaker.speak("Hi baby! What do you want to watch?");
    // speechEngine.start();
}
