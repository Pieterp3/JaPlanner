package game.definitions.impl;

import game.definitions.Definition;

public class ItemDefinition extends Definition {
	
	private int value;
	private boolean stackable;

	public ItemDefinition(int id, String name, int value, boolean stackable, String description) {
		super(name, id, description);
		this.value = value;
		this.stackable = stackable;
	}

	public int getValue() {
		return this.value;
	}

	public boolean isStackable() {
		return this.stackable;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}
}
