package ui.managers;

import util.AttributeUseTracker;

import ui.Frame;

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
