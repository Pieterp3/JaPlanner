package game.combat;

import game.entity.live.LiveEntity;
import util.structures.Map;

public class KillLog {
	
	private Map<Integer, Integer> kills = new Map<Integer, Integer>();
	private LiveEntity entity;

	public KillLog(LiveEntity entity) {
		this.entity = entity;
	}

	public void addKill(LiveEntity entity) {
		if (kills.containsKey(entity.getDefinition().getId())) {
			kills.put(entity.getDefinition().getId(), kills.get(entity.getDefinition().getId()) + 1);
		} else {
			kills.put(entity.getDefinition().getId(), 1);
		}
	}

	public int getKills(int id) {
		if (kills.containsKey(id)) {
			return kills.get(id);
		}
		return 0;
	}

	public int getTotalKills() {
		int total = 0;
		for (int i = 0; i < kills.size(); i++) {
			total += kills.get(i);
		}
		return total;
	}

	public LiveEntity getEntity() {
		return entity;
	}	

}
