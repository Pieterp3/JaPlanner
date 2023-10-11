package game.maps.tilemap;

import util.structures.List;

public class TileMap {
	
	private int width;
	private int height;
	private Tile[][] tiles;
	
	public TileMap(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new Tile[width][height];
	}
	
	public void setTile(int x, int y, Tile tile) {
		tiles[x][y] = tile;
	}
	
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public void generateMap(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new Tile[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; x++) {
				tiles[x][y] = new Tile(x, y, 0, 0);
			}
		}
	}

	public List<Tile> getNeighbors(Tile current) {
		List<Tile> neighbors = new List<Tile>();
		int x = current.getX();
		int y = current.getY();
		if (x > 0) {
			neighbors.add(tiles[x - 1][y]);
		}
		if (x < width - 1) {
			neighbors.add(tiles[x + 1][y]);
		}
		if (y > 0) {
			neighbors.add(tiles[x][y - 1]);
		}
		if (y < height - 1) {
			neighbors.add(tiles[x][y + 1]);
		}
		return neighbors;
	}

}
