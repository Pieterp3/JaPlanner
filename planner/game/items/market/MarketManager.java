package game.items.market;

import game.GameManager;

public class MarketManager {
	
	private Market market;
	private GameManager gameManager;

	public MarketManager(GameManager gameManager) {
		this.gameManager = gameManager;
		this.market = new Market(this);
	}

	public Market getMarket() {
		return market;
	}

	public GameManager getGameManager() {
		return gameManager;
	}

}
