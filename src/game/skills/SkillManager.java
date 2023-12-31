package game.skills;

import game.entity.Entity;
import game.skills.impl.Health;
import util.structures.lists.List;

/**
 * Todo
 * Create list of default skills in cfg file
 *  - one for players
 *  - one for npcs
 *  - bots use player skills
 */
public class SkillManager {
	
	private Entity entity;
	private List<Skill> skills;

	public SkillManager(Entity entity) {
		this.entity = entity;
		skills = new List<>();
		skills.add(new Health());
	}

	public Entity getEntity() {
		return entity;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public Skill getSkill(String name) {
		for (Skill skill : skills) {
			if (skill.getName().equalsIgnoreCase(name)) {
				return skill;
			}
		}
		return null;
	}

}
