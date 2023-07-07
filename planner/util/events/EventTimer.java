package util.events;

public class EventTimer {
	
	private TimerEvent event;
	private int msDelay;
	private boolean repeat;
	private boolean autoStartPostDelay;
	private int msPassed;
	private boolean running;

	public EventTimer(TimerEvent event, int msDelay, boolean repeat, boolean autoStartPostDelay) {
		super();
		this.event = event;
		this.msDelay = msDelay;
		this.repeat = repeat;
		this.autoStartPostDelay = autoStartPostDelay;
		this.msPassed = 0;
		this.running = false;
	}

	public EventTimer(TimerEvent event, int msDelay, boolean repeat) {
		this(event, msDelay, repeat, false);
	}

	public EventTimer(TimerEvent event, int msDelay) {
		this(event, msDelay, false, true);
	}

	public EventTimer(TimerEvent event) {
		this(event, 0, false, true);
	}

	public void update(long timePassed) {
		if (running) {
			msPassed += timePassed;
			if (msPassed >= msDelay) {
				event.execute();
				if (repeat) {
					msPassed = 0;
				} else {
					running = false;
				}
			}
		} else if (autoStartPostDelay) {
			msPassed += timePassed;
			if (msPassed >= msDelay) {
				running = true;
				msPassed = 0;
			}
		}
	}

	public void start() {
		running = true;
	}

	public void stop() {
		running = false;
	}

	public void reset() {
		msPassed = 0;
	}

	public void setEvent(TimerEvent event) {
		this.event = event;
	}

	public void setMsDelay(int msDelay) {
		this.msDelay = msDelay;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public void setAutoStartPostDelay(boolean autoStartPostDelay) {
		this.autoStartPostDelay = autoStartPostDelay;
	}

	public void setMsPassed(int msPassed) {
		this.msPassed = msPassed;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public TimerEvent getEvent() {
		return event;
	}

	public int getMsDelay() {
		return msDelay;
	}

	public boolean doesRepeat() {
		return repeat;
	}

	public boolean doesAutoStartPostDelay() {
		return autoStartPostDelay;
	}

	public int getMsPassed() {
		return msPassed;
	}

	public boolean isRunning() {
		return running;
	}

}
