package game.items.market;

import util.structures.List;

public class Market {
	
	private List<Offer> offers;
	private MarketManager marketManager;

	public Market(MarketManager marketManager) {
		this.marketManager = marketManager;
		this.offers = new List<Offer>();
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public MarketManager getMarketManager() {
		return marketManager;
	}

}
