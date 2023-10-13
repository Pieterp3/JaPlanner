package game.items.market;

import game.GameManager;
import game.entity.live.LiveEntity;
import game.items.inventory.Inventory;
import game.items.shops.ShopManager;
import util.structures.List;
import util.structures.Map;

public class MarketManager {

	private Map<Integer, List<Offer>> marketOffers;
	private GameManager gameManager;

	public MarketManager(GameManager gameManager) {
		this.gameManager = gameManager;
		this.marketOffers = new Map<>();
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public Map<Integer, List<Offer>> getMarketOffers() {
		return marketOffers;
	}

	public boolean createNewOffer(LiveEntity entity, int itemId, int amount, int price, boolean sale) {
		Inventory inventory = entity.getInventory();
		if (inventory == null)
			return false;
		if (sale) {
			if (!inventory.contains(itemId, amount))
				return false;
			inventory.removeItem(itemId, amount);
		} else {
			if (!inventory.contains(ShopManager.DEFAULT_CURRENCY_ID, amount * price))
				return false;
			inventory.removeItem(ShopManager.DEFAULT_CURRENCY_ID, amount * price);
		}
		Offer offer = sale ? new SellOffer(entity, itemId, amount, price) : new BuyOffer(entity, itemId, amount, price);
		submitOffer(offer);
		return true;
	}

	private void submitOffer(Offer offer) {
		List<Offer> offers = marketOffers.get(offer.getItemId());
		if (offers == null) {
			offers = new List<>();
			offers.add(offer);
			marketOffers.put(offer.getItemId(), offers);
			return;
		}
		for (int i = 0; i < offers.size(); i++) {
			Offer other = offers.get(i);
			if (other.isComplete())
				continue;
			offer.update(other);
		}
	}

	public void gatherOffers(LiveEntity entity) {
		List<Offer> completedOffers = gatherCompletedOffers(entity);
		List<Offer> gatheredOffers = new List<>();
		for (Offer offer : completedOffers) {
			if (offer.isComplete()) {
				if (entity.getInventory().hasSpaceFor(offer.getPayout())) {
					entity.getInventory().addItems(offer.getPayout());
					gatheredOffers.add(offer);
				}
			}
		}
		removeOffers(gatheredOffers);
	}

	private List<Offer> gatherCompletedOffers(LiveEntity entity) {
		List<Offer> completedOffers = new List<>();
		for (List<Offer> offers : marketOffers.getValues()) {
			for (int i = 0; i < offers.size(); i++) {
				Offer offer = offers.get(i);
				if (offer.isComplete() && offer.getEntity() == entity) {
					completedOffers.add(offer);
				}
			}
		}
		return completedOffers;
	}

	private void removeOffers(List<Offer> offers) {
		for (int i = 0; i < offers.size(); i++) {
			Offer offer = offers.get(i);
			List<Offer> list = marketOffers.get(offer.getItemId());
			list.remove(offer);
		}
	}
}
