package game.combat.impl;

import game.combat.CombatSystemManager;
import game.entity.LiveEntity;

public class PlayerCSM extends CombatSystemManager {


	public PlayerCSM() {
		super(15, false);
	}

	@Override
	protected boolean withinAttackRange() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'withinAttackRange'");
	}

	@Override
	protected boolean canAttack() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'canAttack'");
	}

	@Override
	protected void attack(LiveEntity target) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'attack'");
	}

	@Override
	protected boolean setTarget() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setTarget'");
	}

	@Override
	public void handleDeath() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'handleDeath'");
	}

	@Override
	public int getAttackRange() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAttackRange'");
	}

	@Override
	public int getAttackSpeed() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAttackSpeed'");
	}

	@Override
	public void processKill(LiveEntity killed) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'processKill'");
	}

	@Override
	public boolean isAggressive() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isAggressive'");
	}

	@Override
	protected boolean findAggressionTarget() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findAggressionTarget'");
	}
	
}
