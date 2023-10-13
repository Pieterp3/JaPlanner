package game.definitions.impl;

import game.definitions.Definition;

public class ItemCombatDefinition extends Definition {

	private int attackRange;
	private int meleeAccuracy, meleeStrength;
	private int rangeAccuracy, rangeStrength;
	private int magicAccuracy, magicDamage;
	private int attackSpeed;

	public ItemCombatDefinition(ItemDefinition definition, int attackSpeed, int attackRange, int meleeAccuracy, int meleeStrength, int rangeAccuracy, int rangeStrength, int magicAccuracy, int magicDamage) {
		super(definition.getName(), definition.getId(), definition.getDescription());
		this.attackSpeed = attackSpeed;
		this.attackRange = attackRange;
		this.meleeAccuracy = meleeAccuracy;
		this.meleeStrength = meleeStrength;
		this.rangeAccuracy = rangeAccuracy;
		this.rangeStrength = rangeStrength;
		this.magicAccuracy = magicAccuracy;
		this.magicDamage = magicDamage;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public int getMeleeAccuracy() {
		return meleeAccuracy;
	}

	public int getMeleeStrength() {
		return meleeStrength;
	}

	public int getRangeAccuracy() {
		return rangeAccuracy;
	}

	public int getRangeStrength() {
		return rangeStrength;
	}

	public int getMagicAccuracy() {
		return magicAccuracy;
	}

	public int getMagicDamage() {
		return magicDamage;
	}

    public int getAttackSpeed() {
        return attackSpeed;
    }
	
}
