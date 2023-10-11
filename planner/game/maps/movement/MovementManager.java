package game.maps.movement;

import game.entity.Entity;
import game.maps.location.Location;

public abstract class MovementManager {
	
	private boolean running;
	private boolean teleporting;
	private boolean frozen;
	private boolean stunned;
	private Entity entity;
	private Location target;

	public MovementManager() {
		this.running = false;
		this.teleporting = false;
		this.frozen = false;
		this.stunned = false;
		this.target = null;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
		this.target = entity.getLocation();
	}

	public Location getTarget() {
		return target;
	}

	public boolean isRunning() {
		return running;
	}

	public boolean isTeleporting() {
		return teleporting;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public boolean isStunned() {
		return stunned;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setTeleporting(boolean teleporting) {
		this.teleporting = teleporting;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}

	public Entity getEntity() {
		return entity;
	}

	public abstract void tick();

    public void setTarget(Location location) {
		this.target = location;
	}

}
