package game.definitions;

/**
 * A definition is a class that contains information about a game object, npc, item, projectile, or idle object.
 */
public class Definition {
	
	private String name;
	private int id;
	private String description;

	public Definition(String name, int id, String description) {
		this.name = name;
		this.id = id;
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
