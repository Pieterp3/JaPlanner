import ui.Frame;
import ui.Panel;
import util.engine.Engine;
import util.engine.SpeechEngine;
import util.structures.List;

public class Main {
    private static Frame frame;
    private static List<Panel> panels;
    public static final boolean DEBUG = true;
    private static Engine engine;

    public static void main(String[] args) {
       // Speech.speak("Hi baby! What do you want to watch?");
        
        
        // frame = new Frame();
        engine = new SpeechEngine();
        // panels = new List<Panel>();
        // panels.add(new HomePanel(frame));

        //Last panel added is the active panel for dev purposes.
        // Panel devPanel = panels.get(DEBUG ? panels.size() - 1 : 0);
        // frame.setActivePanel(devPanel);
        // frame.setVisible(true);
        engine.start();
    }
}
