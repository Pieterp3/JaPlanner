package game.items.inventory;

import game.entity.Entity;
import game.items.ItemContainer;

public class Inventory extends ItemContainer {
	
	private Entity owner;

	public Inventory(Entity owner, int size) {
		super(size);
		this.owner = owner;
	}

	public Entity getOwner() {
		return this.owner;
	}

}
