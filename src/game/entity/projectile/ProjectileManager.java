package game.entity.projectile;

import game.entity.Entity;
import game.entity.EntityManager;
import util.structures.List;

public class ProjectileManager extends EntityManager {

	public void addProjectile(Projectile projectile) {
		super.addEntity(projectile);
	}

	public void removeProjectile(Projectile projectile) {
		super.removeEntity(projectile);
	}

	public List<Projectile> getProjectiles() {
		List<Projectile> projectiles = new List<Projectile>();
		for (Entity projectile : super.getEntities()) {
			projectiles.add((Projectile)projectile);
		}
		return projectiles;
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
