package game.skills;

public class Skill {

	private int level;
	private double experience;
	private final int id;
	private final String name;
	private static final int maxLevel = 1000;

	public Skill(int id, String name, double experience) {
		this.id = id;
		this.name = name;
		this.experience = experience;
		this.level = getLevelForExperience();
	}

	public Skill(int id, String name) {
		this(id, name, 0);
	}

	public int getLevel() {
		return level;
	}

	public double getExperience() {
		return experience;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public int getLevelForExperience() {
		double exp = experience;
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= maxLevel; lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if ((output - 1) >= exp)
				return lvl;
		}
		return maxLevel;
	}

}
