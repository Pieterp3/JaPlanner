package game.items.equipment;

import game.entity.Entity;
import game.items.ItemContainer;

public class Equipment extends ItemContainer {
	
	private Entity owner;

	public Equipment(Entity owner, int slots) {
		super(slots);
	}

	public Entity getOwner() {
		return this.owner;
	}

}
