package game.entity.live;

import game.GameManager;
import game.combat.systems.CombatSystemManager;
import game.definitions.Definition;
import game.entity.Entity;
import game.maps.location.Location;
import game.maps.movement.MovementManager;

public abstract class LiveEntity extends Entity {

    private CombatSystemManager combatSystem;
	
	public LiveEntity(GameManager gameManager, Definition definition, Location location, CombatSystemManager combatSystem,
			MovementManager movement) {
		super(gameManager, definition, location, movement);
        this.combatSystem = combatSystem;
        combatSystem.setEntity(this);
	}

	@Override
	public void tick() {
		if (getMovement() != null) {
			getMovement().tick();
		}
		if (getCombatSystem() != null) {
			getCombatSystem().tick();
		}
		tock();
	}

    public CombatSystemManager getCombatSystem() {
        return this.combatSystem;
    }
	
	public abstract void tock();
}
