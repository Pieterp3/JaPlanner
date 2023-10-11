package game.definitions.impl;

import game.definitions.Definition;

public class NpcDefinition extends Definition {

	private int attackRange;
	private int attackSpeed;

	public NpcDefinition(String name, int id, String description, int attackRange, int attackSpeed) {
		super(name, id, description);
		this.attackRange = attackRange;
		this.attackSpeed = attackSpeed;
	}

    public int getAttackRange() {
        return attackRange;
    }

	public int getAttackSpeed() {
		return attackSpeed;
	}

}
