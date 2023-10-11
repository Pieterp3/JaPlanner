package game.items;

import game.definitions.impl.ItemDefinition;

public class Item {

	private ItemDefinition definition;
	private int amount;

	public Item(int id) {
		this(id, 1);
	}

	public Item(int id, int amount) {
		this.definition = ItemManager.getDefinition(id);
		this.amount = amount;
	}

    public ItemDefinition getDefinition() {
        return null;
    }

	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int add(int additional) {
		if (amount + additional > Integer.MAX_VALUE) {
			int overflow = (amount + additional) - Integer.MAX_VALUE;
			amount = Integer.MAX_VALUE;
			return overflow;
		}
		amount += additional;
		return 0;
	}

	public int remove(int removal) {
		if (amount - removal < 0) {
			int overflow = (amount - removal);
			amount = 0;
			return overflow;
		}
		amount -= removal;
		return 0;
	}

	public boolean isStackable() {
		return definition.isStackable();
	}

	public boolean isSellable() {
		return definition.getValue() >= 0;
	}

	public String getName() {
		return definition.getName();
	}

	public int getId() {
		return definition.getId();
	}

	public int getValue() {
		return definition.getValue();
	}

	public String getDescription() {
		return definition.getDescription();
	}

}
