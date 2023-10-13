package game;

import game.entity.Entity;
import game.entity.EntityManager;
import game.entity.gameobject.ObjectManager;
import game.entity.live.LiveEntity;
import game.entity.live.npc.NpcManager;
import game.entity.live.player.PlayerManager;
import game.entity.projectile.ProjectileManager;
import game.items.market.MarketManager;
import game.items.shops.ShopManager;
import game.maps.location.Location;
import game.saving.SaveManager;
import util.structures.List;

public class GameManager {

	private GameEngine engine;
	private SaveManager saveManager;
	private MarketManager marketManager;
	private ShopManager shopManager;
	private EntityManager[] entityManagers;
	private long lastSave;

	public GameManager(GameEngine engine) {
		this.engine = engine;
		entityManagers = new EntityManager[4];
		entityManagers[0] = new NpcManager();
		entityManagers[1] = new PlayerManager();
		entityManagers[2] = new ObjectManager();
		entityManagers[3] = new ProjectileManager();
		saveManager = new SaveManager(this);
		marketManager = new MarketManager(this);
		shopManager = new ShopManager(this);
	}

	public void tick() {
		for (EntityManager entityManager : entityManagers) {
			entityManager.tick();
		}
		if (System.currentTimeMillis() - lastSave >= 60000) {
			// saveManager.savePlayers();
			// saveManager.saveNpcs();
			// saveManager.saveObjects();
			lastSave = System.currentTimeMillis();
			System.out.println("Autosave complete");
		}
	}

	public SaveManager getSaveManager() {
		return saveManager;
	}

	public MarketManager getMarketManager() {
		return marketManager;
	}

	public ShopManager getShopManager() {
		return shopManager;
	}

	public EntityManager[] getEntityManagers() {
		return entityManagers;
	}

	public NpcManager getNpcManager() {
		return (NpcManager) entityManagers[0];
	}

	public PlayerManager getPlayerManager() {
		return (PlayerManager) entityManagers[1];
	}

	public ObjectManager getObjectManager() {
		return (ObjectManager) entityManagers[2];
	}

	public ProjectileManager getProjectileManager() {
		return (ProjectileManager) entityManagers[3];
	}

	public GameEngine getEngine() {
		return engine;
	}

	public List<LiveEntity> getLiveEntitiesWithinRange(Location location, int distance) {
		List<LiveEntity> entities = new List<>();
		for (EntityManager entityManager : entityManagers) {
			if (!entityManager.isLiveEntityManager()) {
				continue;
			}
			for (Entity entity : entityManager.getEntitiesWithinRange(location, distance)) {
				entities.add((LiveEntity) entity);
			}
		}
		return entities;
	}

	public List<Entity> getAllEntitiesWithinRange(Location location, int distance) {
		List<Entity> entities = new List<>();
		for (EntityManager entityManager : entityManagers) {
			entities.add(entityManager.getEntitiesWithinRange(location, distance));
		}
		return entities;
	}

}
