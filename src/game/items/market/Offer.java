package game.items.market;

import game.entity.live.LiveEntity;
import game.items.Item;
import game.items.shops.ShopManager;
import util.structures.lists.List;

/**
 * Contains an offer for the market
 * 
 */
public abstract class Offer {
	
	private int itemId;
	private int amountRemaining;
	private int startingAmount;
	private int priceEach;
	private int waitingCurrency;
	private int currencyType = ShopManager.DEFAULT_CURRENCY_ID;
	private LiveEntity entity;

	public Offer(LiveEntity entity, int itemId, int amount, int priceEach) {
		this.entity = entity;
		this.itemId = itemId;
		this.amountRemaining = amount;
		this.startingAmount = amount;
		this.priceEach = priceEach;
	}

	public int getItemId() {
		return itemId;
	}

	public int getAmountRemaining() {
		return amountRemaining;
	}

	public int getStartingAmount() {
		return startingAmount;
	}

	public int getPriceEach() {
		return priceEach;
	}

	public int getWaitingCurrency() {
		return waitingCurrency;
	}

	public int getCurrencyType() {
		return currencyType;
	}

	public LiveEntity getEntity() {
		return entity;
	}

	public void setAmountRemaining(int amountRemaining) {
		this.amountRemaining = amountRemaining;
	}

	public void increaseWaitingCurrency(int amount) {
		this.waitingCurrency += amount;
	}

	public abstract boolean update(Offer other);
	public abstract boolean isForSale();
	public abstract boolean isComplete();
	public abstract List<Item> getPayout();

	public int getTotalPrice() {
		return amountRemaining * priceEach;
	}

}