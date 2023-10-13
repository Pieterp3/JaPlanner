package game.definitions.impl;

import game.definitions.Definition;

public class NpcDefinition extends Definition {

	private int attackRange;
	private int attackSpeed;
	private boolean isAggressive;

	public NpcDefinition(String name, int id, String description, int attackRange, int attackSpeed, boolean aggressive) {
		super(name, id, description);
		this.attackRange = attackRange;
		this.attackSpeed = attackSpeed;
		this.isAggressive = aggressive;
	}

	public boolean isAggressive() {
		return isAggressive;
	}

    public int getAttackRange() {
        return attackRange;
    }

	public int getAttackSpeed() {
		return attackSpeed;
	}

}
