package game.entity.npc;

import game.GameManager;
import game.combat.impl.NpcCSM;
import game.definitions.impl.NpcDefinition;
import game.entity.LiveEntity;
import game.maps.location.Location;
import game.maps.movement.StationaryMovement;

public class Npc extends LiveEntity {

	public Npc(GameManager gameManager, NpcDefinition definition, Location location) {
		super(gameManager, definition, location, new NpcCSM(), new StationaryMovement());
	}

	@Override
	public void tock() {
	}

    public NpcManager getNpcManager() {
        return null;
    }
	
}
