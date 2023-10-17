package game.entity;

import game.maps.location.Location;
import util.structures.lists.List;

public abstract class EntityManager {
	
	private List<Entity> entities;

	public EntityManager() {
		entities = new List<Entity>();
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void tick() {
		preTick();
		for (Entity entity : entities) {
			entity.tick();
		}
		postTick();
	}

	public abstract void preTick();

	public abstract void postTick();

	public abstract boolean isLiveEntityManager();

	public List<Entity> getEntitiesWithinRange(Location location, int distance) {
		List<Entity> entities = new List<Entity>();
		for (Entity entity : this.entities) {
			if (entity.getLocation().getDistance(location) <= distance) {
				entities.add(entity);
			}
		}
		return entities;
	}

}
