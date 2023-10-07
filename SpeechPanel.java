
import ui.Panel;
import ui.frames.Frame;
import ui.components.impl.*;
import util.events.EventTimer;
import util.engine.SpeechEngine;
import util.events.Event;

public class SpeechPanel extends Panel {

    private LoadingBar speechBar;
    private SpeechEngine engine;

    public SpeechPanel(Frame frame, SpeechEngine engine) {
        super(frame, "speech");
        this.engine = engine;
    }

    protected void init() {
        speechBar = new LoadingBar(getFrame(), "Listening Time", 10, 10, 400, 30);
        Event event = new Event() {

            @Override
            public void execute() {
                speechBar.setAttribute("text", engine.getSpeechState());
            }

        };
        EventTimer timer = new EventTimer(event, 3000, true);
        getFrame().addTimer(timer);
        speechBar.setEventTimer(timer);
        addComponent(speechBar);
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void keyTyped(int keyCode) {
        System.out.println("Speechpanel Key typed: " + keyCode);
    }

    @Override
    public void handleAction(String command) {
        System.out.println("Processing action: '" + command + "' in speech panel");
    }

    @Override
    public void preComponentDrawing() {

    }

    @Override
    public void finishPanelDrawing() {

    }

}
