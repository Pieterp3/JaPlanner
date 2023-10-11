package game.combat;

import game.entity.LiveEntity;
import game.skills.Skill;
import game.skills.SkillManager;
import util.structures.List;
import util.structures.Map;

/**
 * Manages the combat system for an entity
 * 
 */
public abstract class CombatSystemManager {

	private LiveEntity entity;
	private Map<LiveEntity, List<Damage>> attackers;
	private LiveEntity primaryTarget;

	private int ticksSinceLastAttack;
	private int lastPassiveHealTick;

	private SkillManager skillManager;
	private Skill healthSkill;
	private int currentHealth;
	private int passiveHealTickRate;

	private Map<DamageType, Double> weaknesses;
	private DamageType damageType;

	private boolean autoRetaliate;

	public CombatSystemManager(int passiveHealTickRate, boolean autoRetaliate) {
		this.attackers = new Map<>();
		this.primaryTarget = null;
		this.lastPassiveHealTick = 0;
		this.ticksSinceLastAttack = 0;
		this.passiveHealTickRate = passiveHealTickRate;
		this.autoRetaliate = autoRetaliate;
		this.weaknesses = new Map<>();
		this.damageType = DamageType.MELEE;
	}

	public void tick() {
		if (isDead()) {
			return;
		}
		// Check if we have a target
		if (!hasTarget()) {
			// Check if we can set a target
			if (isAggressive()) {
				if (!findAggressionTarget()) 
					return;
			} else if (!setTarget())
				return;
		}
		// Check if we are in range
		if (!withinAttackRange()) {
			entity.getMovement().setRunning(true);
			entity.getMovement().setTarget(primaryTarget.getLocation());
			return;
		}
		lastPassiveHealTick++;
		if (lastPassiveHealTick >= getPassiveHealRate()) {
			lastPassiveHealTick = 0;
		}
		if (ticksSinceLastAttack < getAttackSpeed()) {
			ticksSinceLastAttack++;
			return;
		}
		if (canAttack()) {
			attack(primaryTarget);
			ticksSinceLastAttack = 0;
		}
	}

	public abstract boolean isAggressive();

	protected abstract boolean findAggressionTarget();

	public abstract void handleDeath();

	protected abstract boolean canAttack();

	protected abstract void attack(LiveEntity target);

	public abstract void processKill(LiveEntity killed);

	protected List<LiveEntity> getAttackers() {
		return attackers.getKeys();
	}

	protected LiveEntity getHighestDamageAttacker() {
		LiveEntity highestDamageAttacker = null;
		int highestDamage = 0;
		for (LiveEntity attacker : attackers.getKeys()) {
			int damage = 0;
			for (Damage dmg : attackers.get(attacker)) {
				damage += dmg.getAmount();
			}
			if (damage > highestDamage) {
				highestDamage = damage;
				highestDamageAttacker = attacker;
			}
		}
		return highestDamageAttacker;
	}

	protected boolean withinAttackRange() {
		return primaryTarget.getLocation().withinDistance(getEntity().getLocation(),
				getAttackRange());
	}

	public LiveEntity getEntity() {
		return entity;
	}

	/**
	 * @return true if a primary target was set
	 */
	protected abstract boolean setTarget();

	public LiveEntity getPrimaryTarget() {
		return primaryTarget;
	}

	public boolean hasTarget() {
		return primaryTarget != null;
	}

	protected void setPrimaryTarget(LiveEntity primaryTarget) {
		this.primaryTarget = primaryTarget;
	}

	public void addAttacker(Damage damage) {
		LiveEntity attacker = damage.getAttacker();
		if (attacker == null) {
			return;
		}
		if (!attackers.containsKey(attacker)) {
			attackers.put(attacker, new List<>());
		}
		attackers.get(attacker).add(damage);
		if (primaryTarget == null && doesAutoRetaliate())
			setPrimaryTarget(attacker);
	}

	public void removeAttacker(LiveEntity attacker) {
		if (attacker == null) {
			return;
		}
		if (!attackers.containsKey(attacker)) {
			return;
		}
		attackers.remove(attacker);
	}

	protected void clearAttackers() {
		attackers.clear();
	}

	public boolean restoreHealth(int amount) {
		if (currentHealth == healthSkill.getLevel()) {
			return false;
		}
		currentHealth += amount;
		if (currentHealth > healthSkill.getLevel()) {
			currentHealth = healthSkill.getLevel();
		}
		return true;
	}

	public void addWeakness(DamageType type, double multiplier) {
		weaknesses.put(type, multiplier);
	}

	public void removeWeakness(DamageType type) {
		weaknesses.remove(type);
	}

	public boolean hasWeakness(DamageType type) {
		return weaknesses.containsKey(type);
	}

	public double getWeaknessMultiplier(DamageType type) {
		if (hasWeakness(type)) {
			return weaknesses.get(type);
		}
		return 1;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public abstract int getAttackRange();

	public abstract int getAttackSpeed();

	public boolean doesAutoRetaliate() {
		return autoRetaliate;
	}

	/**
	 * @return true if the entity died from the damage, false otherwise
	 */
	public boolean damage(Damage dmg) {
		double damageMultiplier = getWeaknessMultiplier(dmg.getType());
		dmg.setAmount((int) (dmg.getAmount() * damageMultiplier));
		addAttacker(dmg);
		currentHealth -= dmg.getAmount();
		if (currentHealth <= 0) {
			currentHealth = 0;
			handleDeath();
			return true;
		}
		return false;
	}

	public boolean isDead() {
		return currentHealth <= 0;
	}

	public void setEntity(LiveEntity entity) {
		this.entity = entity;
		SkillManager skillManager = this.entity.getSkillManager();
		this.skillManager = skillManager;
		this.healthSkill = skillManager.getSkill("Health");
		if (healthSkill == null) {
			throw new RuntimeException("Health skill not found for entity: " + entity);
		}
		this.currentHealth = healthSkill.getLevel();
		// this.equipment = entity.getEquipment();
		// this.inventory = entity.getInventory();
	}

	public int getPassiveHealRate() {
		return passiveHealTickRate;
	}

}
