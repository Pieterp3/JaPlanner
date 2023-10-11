package game.combat.impl;

import game.combat.CombatSystemManager;
import game.combat.Damage;
import game.combat.DamageType;
import game.definitions.impl.NpcDefinition;
import game.entity.LiveEntity;
import game.entity.npc.Npc;
import util.math.Misc;
import util.structures.List;

public class NpcCSM extends CombatSystemManager {

	public NpcCSM() {
		super(20, true);
	}

	// can be moved to csm?
	@Override
	protected void attack(LiveEntity target) {
		CombatSystemManager targetCSM = target.getCombatSystem();
		int baseDamage = rollDamage(targetCSM);
		if (baseDamage == 0) {
			targetCSM.addAttacker(new Damage(getEntity(), 0, DamageType.NONE));
			return;
		}
		targetCSM.damage(new Damage(getEntity(), baseDamage, getDamageType()));
	}

	private int rollDamage(CombatSystemManager enemyCombatSystem) {
		if (Misc.randomBoolean())
			return 0;
		if (Misc.randomBoolean())
			return Misc.getRandomNumber(5, 25);
		return Misc.getRandomNumber(0, 10);
	}

	@Override
	protected boolean canAttack() {
		return true;
	}

	@Override
	public boolean isAggressive() {
		// TODO Check definition for aggression
		return true;
	}

	@Override
	protected boolean findAggressionTarget() {
		List<LiveEntity> attackers = getAttackers();
		if (!attackers.isEmpty())
			return setTarget();
		List<LiveEntity> targets = getNpc().getGameManager().getLiveEntitiesWithinRange(getEntity().getLocation(),
				getAttackRange() * 2);
		if (targets.isEmpty())
			return false;
		setPrimaryTarget(targets.get(Misc.getRandomNumber(0, targets.size() - 1)));
		return true;
	}

	@Override
	protected boolean setTarget() {
		List<LiveEntity> entities = getAttackers();
		if (entities.isEmpty())
			return false;
		LiveEntity target = entities.get(Misc.getRandomNumber(0, entities.size() - 1));
		setPrimaryTarget(target);
		return true;
	}

	@Override
	public void handleDeath() {
		LiveEntity killer = getHighestDamageAttacker();
		killer.getCombatSystem().processKill(getEntity());
	}

	@Override
	public void processKill(LiveEntity killed) {
		// TODO Add kill to npc's kill log
		// Give experience
		// Drop loot
		// etc..
		System.out.println(getEntity() + " " + "killed " + killed);
	}

	@Override
	public int getAttackRange() {
		return ((NpcDefinition) getEntity().getDefinition()).getAttackRange();
	}

	@Override
	public int getAttackSpeed() {
		return ((NpcDefinition) getEntity().getDefinition()).getAttackSpeed();
	}

	private Npc getNpc() {
		return (Npc) getEntity();
	}

}
