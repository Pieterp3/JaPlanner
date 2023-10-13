package game.maps.location;

public class Area {
	
	private int x;
	private int y;
	private int z;
	private int x2;
	private int y2;

	public Area(int x, int y, int z, int x2, int y2) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.x2 = x2;
		this.y2 = y2;
	}

	public Area(int x, int y, int z) {
		this(x, y, z, x, y);
	}

	public Area(Area area) {
		this(area.getX(), area.getY(), area.getZ(), area.getX2(), area.getY2());
	}

	public Area() {
		this(0, 0, 0, 0, 0);
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

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y= y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public boolean contains(int x, int y) {
		return x >= this.x && x <= this.x2 && y >= this.y && y <= this.y2;
	}

	public boolean contains(Location location) {
		return contains(location.getX(), location.getY());
	}

	public boolean contains(Area area) {
		if (area.getZ() != getZ()) return false;
		return contains(area.getX(), area.getY()) && contains(area.getX2(), area.getY2());
	}

	public boolean intersects(Area area) {
		if (area.getZ() != getZ()) return false;
		return contains(area.getX(), area.getY()) || contains(area.getX2(), area.getY2());
	}

}
