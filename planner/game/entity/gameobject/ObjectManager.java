package game.entity.gameobject;

import game.entity.Entity;
import game.entity.EntityManager;
import util.structures.List;

public class ObjectManager extends EntityManager {

	public void addObject(GameObject object) {
		super.addEntity(object);
	}

	public void removeObject(GameObject object) {
		super.removeEntity(object);
	}

	public List<GameObject> getObjects() {
		List<GameObject> objects = new List<GameObject>();
		for (Entity object : super.getEntities()) {
			objects.add((GameObject) object);
		}
		return objects;
	}

	@Override
	public void preTick() {
	}

	@Override
	public void postTick() {

	}

	@Override
	public boolean isLiveEntityManager() {
		return false;
	}

}
