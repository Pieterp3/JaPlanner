package game.entity.live.npc;

import game.entity.Entity;
import game.entity.EntityManager;
import game.maps.location.Location;
import util.structures.lists.List;

public class NpcManager extends EntityManager {

	public void addNpc(Npc npc) {
		super.addEntity(npc);
	}

	public void removeNpc(Npc npc) {
		super.removeEntity(npc);
	}

	public List<Npc> getNpcs() {
		List<Npc> npcs = new List<Npc>();
		for (Entity npc : super.getEntities()) {
			npcs.add((Npc) npc);
		}
		return npcs;
	}

	@Override
	public void preTick() {}

	@Override
	public void postTick() {

	}

	public List<Npc> getNpcsWithinRange(Location location, int distance) {
		List<Npc> npcs = new List<Npc>();
		for (Npc npc : getNpcs()) {
			if (npc.getLocation().getDistance(location) <= distance) {
				npcs.add(npc);
			}
		}
		return npcs;
	}

	@Override
	public boolean isLiveEntityManager() {
		return true;
	}

}
