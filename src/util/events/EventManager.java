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
		List<EventTimer> completedEvents = new List<>();
		for (int i = 0; i < timers.size(); i++) {
			EventTimer timer = timers.get(i);
			if (timer.update(timePassed)) {
				completedEvents.add(timer);
			}
		}
		timers.remove(completedEvents);
	}

	public void scheduleEvent(Event event, int delay, boolean repeat) {
		addEventTimer(new EventTimer(event, delay, repeat));
	}

	public void addEventTimer(EventTimer timer) {
		timers.add(timer);
	}

}
