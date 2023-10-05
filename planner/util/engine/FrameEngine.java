package util.engine;

import ui.frames.Frame;

public class FrameEngine extends Engine {

    private Frame frame;
	
	public FrameEngine(Frame frame, int eps) {
		super(eps);
		this.frame = frame;
        if (frame != null) {
            frame.setEngine(this);
        }
	}

	@Override
	public void execute() {
		frame.update();
	}
	
}
