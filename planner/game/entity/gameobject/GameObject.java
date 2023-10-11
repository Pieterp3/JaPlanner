package game.entity.gameobject;

import game.GameManager;
import game.definitions.impl.ObjectDefinition;
import game.entity.Entity;
import game.maps.location.Location;

public class GameObject extends Entity {

	public GameObject(GameManager gameManager, ObjectDefinition definition, Location location) {
		super(gameManager, definition, location,  null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'tick'");
	}
	
}
