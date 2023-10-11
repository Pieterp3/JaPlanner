package game.entity.player;

import game.GameManager;
import game.combat.impl.PlayerCSM;
import game.entity.LiveEntity;
import game.maps.location.Location;
import game.maps.movement.PlayerMovement;

public class Player extends LiveEntity {

	private String username;
	private String password;

	public Player(GameManager gameManager, Location location, String username, String password) {
		super(gameManager, null, location, new PlayerCSM(), new PlayerMovement());
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	@Override
	public void tock() {
	}
	
}