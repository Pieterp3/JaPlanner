package util.events;

public class EventTimer {
	
	private Event event;

	private int msPassed;
	private int delay;

	private boolean repeats;
	private int executions;

	public EventTimer(Event event, int delay, boolean repeats) {
		super();
		this.event = event;
		this.msPassed = 0;
		this.repeats = repeats;
		this.delay = delay;
		this.executions = 0;
	}

	public EventTimer(Event event, int startDelay) {
		this(event, startDelay, false);
	}

	public EventTimer(Event event) {
		this(event, 0);
	}

	public boolean update(long timePassed) {
		msPassed += timePassed;
		if (msPassed >= delay) {
			event.execute();
			executions++;
			if (repeats) {
				msPassed = 0;
			} else {
				return true;
			}
		}
		return false;
	}

	public void reset() {
		msPassed = 0;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setMsPassed(int msPassed) {
		this.msPassed = msPassed;
	}

	public void setRepeats(boolean repeats) {
		this.repeats = repeats;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public Event getEvent() {
		return event;
	}

	public int getMsPassed() {
		return msPassed;
	}

	public int getDelay() {
		return delay;
	}

	public boolean doesRepeat() {
		return repeats;
	}

	public int getExecutions() {
		return executions;
	}

	public double getSecondsRemaining() {
		return (delay - msPassed) / 1000.0;
	}

	public int getMsRemaining() {
		return delay - msPassed;
	}

	public double getProgress() {
		return Math.min(1, msPassed / (double) delay);
	}

}
