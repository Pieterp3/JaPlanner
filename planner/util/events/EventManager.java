package util.events;

import util.structures.List;

public class EventManager {
	
	private List<EventTimer> timers;
	private long lastTime;

	public EventManager() {
		super();
		timers = new List<EventTimer>();
		lastTime = System.currentTimeMillis();
	}

	public void update() {
		long currentTime = System.currentTimeMillis();
		long timePassed = currentTime - lastTime;
		lastTime = currentTime;
		for (int i = 0; i < timers.size(); i++) {
			EventTimer timer = timers.get(i);
			timer.update(timePassed);
		}
	}

	public void scheduleEvent(TimerEvent event, int msDelay, boolean repeat, boolean autoStartPostDelay) {
		timers.add(new EventTimer(event, msDelay, repeat, autoStartPostDelay));
	}

	public void addEventTimer(EventTimer timer) {
		timers.add(timer);
	}

}
