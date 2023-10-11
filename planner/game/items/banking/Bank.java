package game.items.banking;

import game.entity.Entity;
import game.items.ItemContainer;

public class Bank extends ItemContainer {
	
	private Entity owner;

	public Bank(Entity entity, int slots) {
		super(slots);
		this.owner = entity;
	}

	public Entity getOwner() {
		return this.owner;
	}

}
