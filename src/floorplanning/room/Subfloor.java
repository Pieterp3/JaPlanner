package floorplanning.room;

public enum Subfloor {
	CONCRETE, PLYWOOD, OSB, PARTICLEBOARD, SLATS, FIBERGLASS, NONE;

	private String name;

	private Subfloor() {
		this.name = this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
	}

	public String getName() {
		return name;
	}

	public static Subfloor getSubfloor(String name) {
		for (Subfloor subfloor : Subfloor.values()) {
			if (subfloor.getName().equalsIgnoreCase(name)) {
				return subfloor;
			}
		}
		return null;
	}
}
