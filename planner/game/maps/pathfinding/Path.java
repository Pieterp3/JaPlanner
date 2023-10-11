package game.maps.pathfinding;

import game.maps.tilemap.Tile;
import util.structures.List;

/**
 * Stores a path found by the pathfinder
 */
public class Path {
	
	private List<Tile> path;
	private int stepIndex;
	private boolean repeats;
	private boolean goingBackwards;
	private int repitionCount;

	public Path(List<Tile> path, boolean repeats) {
		this.path = path;
		this.repeats = repeats;
		this.stepIndex = 0;
		this.goingBackwards = false;
		this.repitionCount = 0;
	}

	public int getRepitionCount() {
		return repitionCount;
	}

	public List<Tile> getPath() {
		return path;
	}

	public void setPath(List<Tile> path) {
		this.path = path;
	}

	public Tile getCurrentTile() {
		return path.get(stepIndex);
	}

	public Tile getNextTile() {
		return path.get(stepIndex + 1);
	}

	public Tile getPreviousTile() {
		return path.get(stepIndex - 1);
	}

	public void nextStep() {
		boolean movingBackwards = goingBackwards;
		if (isLastStep()) {
			if (repeats) {
				goingBackwards = true;
			}
		} else if (isFirstStep()) {
			goingBackwards = false;
		}
		if (stepIndex < path.size() - 1) {
			stepIndex++;
		}
		if (movingBackwards != goingBackwards) {
			repitionCount++;
		}
	}

	public void previousStep() {
		boolean movingBackwards = goingBackwards;
		if (isFirstStep()) {
			if (repeats) {
				goingBackwards = false;
			}
		} else if (isLastStep()) {
			goingBackwards = true;
		}
		if (stepIndex > 0) {
			stepIndex--;
		}
		if (movingBackwards != goingBackwards) {
			repitionCount++;
		}
	}

	public boolean isLastStep() {
		if (repeats) {
			return false;
		}
		return stepIndex == path.size() - 1;
	}

	public boolean isFirstStep() {
		return stepIndex == 0;
	}
	

}
