package game.items.market;

import game.items.Item;

public class Offer {
	
	private Item item;
	private int price;
	private LivingEntity entity;
	

	public Offer(LivingEntity entity, Item item, int price) {
		this.entity = entity;
		this.item = item;
		this.price = price;
	}

	public Item getItem() {
		return item;
	}

	public int getPrice() {
		return price;
	}

	public LivingEntity getEntity() {
		return entity;
	}

	public int getTotalCost() {
		return price * item.getAmount();
	}

	public void setPrice(int price) {
		this.price = price;
	}

}