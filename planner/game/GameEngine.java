package game;

import util.events.EventManager;
import util.events.EventTimer;
import util.engine.Engine;
import util.events.Event;

public class GameEngine extends Engine {

	private EventManager eventManager;
	private GameManager gameManager;

	public GameEngine() {
		super(25);
		eventManager = new EventManager();
		gameManager = new GameManager(this);
		Event gameTick = new Event() {
			public void execute() {
				gameManager.tick();
				System.out.println("Game tick: " + System.currentTimeMillis());
			}
		};
		EventTimer gameTimer = new EventTimer(gameTick, 600);
		eventManager.addEventTimer(gameTimer);
	}

	@Override
	protected void execute() {
		eventManager.update();
	}

}
