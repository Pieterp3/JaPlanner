package game.maps.tilemap;

import game.maps.location.Location;

public class Tile {

	private Location location;
	private int type;

	public Tile(int x, int y, int height, int type) {
		this.location = new Location(x, y, height);
		this.type = type;
	}

	public int getX() {
		return location.getX();
	}

	public int getY() {
		return location.getY();
	}

	public int getHeight() {
		return location.getHeight();
	}

	public int getType() {
		return type;
	}

	public void setHeight(int height) {
		location.setHeight(height);
	}

	public void setType(int type) {
		this.type = type;
	}

	public Location getLocation() {
		return location;
	}

}
