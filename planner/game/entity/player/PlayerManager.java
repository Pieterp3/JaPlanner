package game.entity.player;

import game.entity.Entity;
import game.entity.EntityManager;
import util.structures.List;

public class PlayerManager extends EntityManager {

	public void addPlayer(Player player) {
		super.addEntity(player);
	}

	public void removePlayer(Player player) {
		super.removeEntity(player);
	}

	public Player getPlayer(String username) {
		for (Player player : getPlayers()) {
			if (player.getUsername().equalsIgnoreCase(username)) {
				return player;
			}
		}
		return null;
	}

	public List<Player> getPlayers() {
		List<Player> players = new List<Player>();
		for (Entity player : super.getEntities()) {
			players.add((Player) player);
		}
		return players;
	}

	@Override
	public void preTick() {
	}

	@Override
	public void postTick() {

	}

	@Override
	public boolean isLiveEntityManager() {
		return true;
	}
	
}
