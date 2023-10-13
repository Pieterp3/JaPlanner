package game.items.market;

import game.entity.live.LiveEntity;
import game.items.Item;
import util.structures.List;

public class SellOffer extends Offer {

	public SellOffer(LiveEntity entity, int itemId, int amount, int priceEach) {
		super(entity, itemId, amount, priceEach);
	}

	@Override
	public boolean update(Offer other) {
		//It's not a buy offer, so we can't update it
		if (!(other instanceof BuyOffer)) {
			return false;
		}
		BuyOffer buyOffer = (BuyOffer) other;
		//It's not for the same item, so we can't update it
		if (buyOffer.getItemId() != getItemId()) {
			return false;
		}
		//It's not cheaper than our offer, so we can't update it
		if (buyOffer.getPriceEach() < getPriceEach()) {
			return false;
		}
		//Set the amount we can sell to the minimum of the two offers
		int amount = Math.min(buyOffer.getAmountRemaining(), getAmountRemaining());
		//Set the price were selling for higher if theyre buying for more
		int sellPrice = Math.max(getPriceEach(), buyOffer.getPriceEach());
		//Set the amount remaining for both offers
		buyOffer.setAmountRemaining(buyOffer.getAmountRemaining() - amount);
		setAmountRemaining(getAmountRemaining() - amount);
		//Give ourselves our currency
		increaseWaitingCurrency(amount * (sellPrice ));
		//Buy offer gets its items from the its quantity being reduced
		return getAmountRemaining() == 0;
	}

	@Override
	public boolean isComplete() {
		return getAmountRemaining() == 0;
	}

	@Override
	public boolean isForSale() {
		return true;
	}

	@Override
	public List<Item> getPayout() {
		List<Item> payout = new List<>();
		payout.add(new Item(getCurrencyType(), getWaitingCurrency()));
		payout.add(new Item(getItemId(), getAmountRemaining()));
		return payout;
	}
	
}
