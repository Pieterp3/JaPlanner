package game.items.market;

import game.entity.live.LiveEntity;
import game.items.Item;
import util.structures.lists.List;

public class BuyOffer extends Offer {

	public BuyOffer(LiveEntity entity, int itemId, int amount, int priceEach) {
		super(entity, itemId, amount, priceEach);
	}

	@Override
	public boolean update(Offer other) {
		//It's not a sell offer, so we can't update it
		if (!(other instanceof SellOffer)) {
			return false;
		}
		SellOffer sellOffer = (SellOffer) other;
		//It's not for the same item, so we can't update it
		if (sellOffer.getItemId() != getItemId()) {
			return false;
		}
		//It's not cheaper than our offer, so we can't update it
		if (sellOffer.getPriceEach() > getPriceEach()) {
			return false;
		}
		//Set the amount we can buy to the minimum of the two offers
		int amount = Math.min(sellOffer.getAmountRemaining(), getAmountRemaining());
		//Set the price were paying lower if theyre selling for less
		int buyPrice = Math.min(getPriceEach(), sellOffer.getPriceEach());
		//Set the amount remaining for both offers
		sellOffer.setAmountRemaining(sellOffer.getAmountRemaining() - amount);
		setAmountRemaining(getAmountRemaining() - amount);
		//If we're paying less than we thought, increase the amount of currency we're waiting for
		if (buyPrice < getPriceEach()) {
			increaseWaitingCurrency(amount * (getPriceEach() - buyPrice));
		}
		//Give the sell offer its currency
		sellOffer.increaseWaitingCurrency(amount * buyPrice);
		return getAmountRemaining() == 0;
	}

	@Override
	public boolean isComplete() {
		return getAmountRemaining() == 0;
	}

	@Override
	public boolean isForSale() {
		return false;
	}

	@Override
	public List<Item> getPayout() {
		List<Item> payout = new List<>();
		payout.add(new Item(getCurrencyType(), getWaitingCurrency()));
		payout.add(new Item(getItemId(), getStartingAmount() - getAmountRemaining()));
		return payout;
	}

}
