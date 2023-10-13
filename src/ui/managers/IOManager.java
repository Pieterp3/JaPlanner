package ui.managers;

import ui.frames.Frame;
import util.AttributeUseTracker;

public class IOManager {
    private Frame frame;

    public IOManager(Frame frame) {
        this.frame = frame;
    }

    public void save() {
        AttributeUseTracker.save();
    }

    public void load() {

    }

    public Frame getFrame() {
        return frame;
    }

}
