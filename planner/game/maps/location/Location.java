package game.maps.location;

public class Location {

	private int x;
	private int y;
	private int z;
	private int direction;

	public Location(int x, int y, int z, int direction) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.direction = direction;
	}

	public Location(int x, int y, int z) {
		this(x, y, z, 0);
	}

	public Location(int x, int y) {
		this(x, y, 0, 0);
	}

	public Location(Location location) {
		this(location.getX(), location.getY(), location.getZ(), location.getDirection());
	}

	public Location() {
		this(0, 0, 0, 0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getDirection() {
		return direction;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void move(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	public void move(int x, int y) {
		move(x, y, 0);
	}

	public void move(Location location) {
		move(location.getX(), location.getY(), location.getZ());
	}

	public void teleport(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void teleport(int x, int y) {
		teleport(x, y, 0);
	}

	public void teleport(Location location) {
		teleport(location.getX(), location.getY(), location.getZ());
	}

	public boolean withinDistance(Location location, int distance) {
		if (location.getZ() != z) {
			return false;
		}
		int deltaX = location.getX() - x;
		int deltaY = location.getY() - y;
		return deltaX <= distance && deltaX >= -distance && deltaY <= distance && deltaY >= -distance;
	}

	public boolean withinArea(Area area) {
		return area.contains(this);
	}

	public boolean withinArea(Location location, int radialDistance) {
		return withinArea(new Area(location.getX() - radialDistance, location.getY() - radialDistance, location.getZ(),
				location.getX() + radialDistance, location.getY() + radialDistance));
	}

	public double getDistance(Location location) {
		return Math.sqrt(Math.pow(x - location.getX(), 2) + Math.pow(y - location.getY(), 2));
	}

	public double getDistance(int x, int y) {
		return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
	}

    public void setHeight(int height) {
		this.z = height;
	}

	public int getHeight() {
		return z;
	}

}
