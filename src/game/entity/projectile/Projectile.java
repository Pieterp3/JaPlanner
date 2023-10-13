package game.entity.projectile;

import game.GameManager;
import game.definitions.impl.ProjectileDefinitions;
import game.entity.Entity;
import game.maps.location.Location;
import game.maps.movement.ProjectileMovement;

public class Projectile extends Entity {

	public Projectile(GameManager gameManager, ProjectileDefinitions definition, Location location) {
		super(gameManager, definition, location, new ProjectileMovement());
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'tick'");
	}
	
}
