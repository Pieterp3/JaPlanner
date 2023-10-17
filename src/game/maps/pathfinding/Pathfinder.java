package game.maps.pathfinding;

import game.maps.tilemap.Tile;
import game.maps.tilemap.TileMap;
import util.structures.Map;
import util.structures.lists.List;

/**
 * A* pathfinding
 * 
 */
public class Pathfinder {

	private TileMap map;
	private List<Tile> openList;
	private List<Tile> closedList;
	private Map<Tile, Tile> cameFrom;
	private Map<Tile, Double> gScore;
	private Map<Tile, Double> fScore;
	private Tile start;
	private Tile goal;
	private boolean repeats;

	public Pathfinder(TileMap map) {
		this.map = map;
		this.openList = new List<Tile>();
		this.closedList = new List<Tile>();
		this.cameFrom = new Map<Tile, Tile>();
		this.gScore = new Map<Tile, Double>();
		this.fScore = new Map<Tile, Double>();
		this.repeats = false;
	}

	public Pathfinder(TileMap map, boolean repeats) {
		this.map = map;
		this.openList = new List<Tile>();
		this.closedList = new List<Tile>();
		this.cameFrom = new Map<Tile, Tile>();
		this.gScore = new Map<Tile, Double>();
		this.fScore = new Map<Tile, Double>();
		this.repeats = repeats;
	}

	public Path findPath(Tile start, Tile goal) {
		this.start = start;
		this.goal = goal;
		openList.add(start);
		gScore.put(start, 0.0);
		fScore.put(start, heuristicCostEstimate(start, goal));
		while (!openList.isEmpty()) {
			Tile current = getLowestFScore();
			if (current == goal) {
				return reconstructPath(current);
			}
			openList.remove(current);
			closedList.add(current);
			for (Tile neighbor : map.getNeighbors(current)) {
				if (closedList.contains(neighbor)) {
					continue;
				}
				double tentativeGScore = gScore.get(current) + distanceBetween(current, neighbor);
				if (!openList.contains(neighbor) || tentativeGScore < gScore.get(neighbor)) {
					cameFrom.put(neighbor, current);
					gScore.put(neighbor, tentativeGScore);
					fScore.put(neighbor, tentativeGScore + heuristicCostEstimate(neighbor, goal));
					if (!openList.contains(neighbor)) {
						openList.add(neighbor);
					}
				}
			}
		}
		return null;
	}

	private Tile getLowestFScore() {
		Tile lowest = null;
		for (Tile tile : openList) {
			if (lowest == null || fScore.get(tile) < fScore.get(lowest)) {
				lowest = tile;
			}
		}
		return lowest;
	}

	private double heuristicCostEstimate(Tile start, Tile goal) {
		return distanceBetween(start, goal);
	}

	private double distanceBetween(Tile start, Tile goal) {
		return Math.sqrt(Math.pow(start.getX() - goal.getX(), 2) + Math.pow(start.getY() - goal.getY(), 2));
	}

	private Path reconstructPath(Tile current) {
		List<Tile> totalPath = new List<Tile>();
		totalPath.add(current);
		while (cameFrom.containsKey(current)) {
			current = cameFrom.get(current);
			totalPath.add(current);
		}
		totalPath.reverse();
		return new Path(totalPath, repeats);
	}

	public TileMap getMap() {
		return map;
	}

	public void setMap(TileMap map) {
		this.map = map;
	}

	public Tile getStart() {
		return start;
	}

	public void setStart(Tile start) {
		this.start = start;
	}

	public Tile getGoal() {
		return goal;
	}

	public void setGoal(Tile goal) {
		this.goal = goal;
	}

	public boolean isRepeats() {
		return repeats;
	}

	public void setRepeats(boolean repeats) {
		this.repeats = repeats;
	}

}
