package floorplanning.room;

import util.math.Point;
import util.structures.lists.List;
import floorplanning.items.FlooringItem;

public class Wall {
	
	private Point start;
	private Point end;
	private double arcRadius;
	private List<FlooringItem> existingTrim;
	private List<FlooringItem> newTrim;
	private WallType type;

	public Wall(Point start, Point end, double arcRadius, WallType type) {
		this.start = start;
		this.end = end;
		this.arcRadius = arcRadius;
		this.type = type;
		existingTrim = new List<>();
		newTrim = new List<>();
	}

	public Wall(String wallString) {
		String[] wallInfo = wallString.split(":");
		start = new Point(Double.parseDouble(wallInfo[0]), Double.parseDouble(wallInfo[1]));
		end = new Point(Double.parseDouble(wallInfo[2]), Double.parseDouble(wallInfo[3]));
		arcRadius = Double.parseDouble(wallInfo[4]);
		type = WallType.valueOf(wallInfo[5]);
		existingTrim = new List<>();
		newTrim = new List<>();
		String[] existingTrimStrings = wallInfo[6].split(",");
		for (String trimString : existingTrimStrings) {
			existingTrim.add(new FlooringItem(trimString, 1));
		}
		String[] newTrimStrings = wallInfo[7].split(",");
		for (String trimString : newTrimStrings) {
			newTrim.add(new FlooringItem(trimString, 1));
		}
	}

    public void addExistingTrim(FlooringItem trim) {
		existingTrim.add(trim);
	}

	public void addNewTrim(FlooringItem trim) {
		newTrim.add(trim);
	}

	public Point getStart() {
		return start;
	}

	public Point getEnd() {
		return end;
	}

	public double getArcRadius() {
		return arcRadius;
	}

	public List<FlooringItem> getExistingTrim() {
		return existingTrim;
	}

	public List<FlooringItem> getNewTrim() {
		return newTrim;
	}

	public WallType getType() {
		return type;
	}

	public double getLength() {
		if (arcRadius == 0) {
			return start.distanceTo(end);
		}
		double angle = Math.atan2(end.getY() - start.getY(), end.getX() - start.getX());
		return angle * arcRadius;
	}

	public double getCost() {
		double cost = 0;
		for (FlooringItem trim : existingTrim) {
			cost += trim.getCost();
		}
		for (FlooringItem trim : newTrim) {
			cost += trim.getCost();
		}
		return cost;
	}

    public String getWallString() {
        String wallString = "";
		wallString += start.getX() + ":" + start.getY() + ":" + end.getX() + ":" + end.getY() + ":" + arcRadius + ":" + type + ":";
		for (FlooringItem trim : existingTrim) {
			wallString += trim.getName() + ":";
		}
		wallString += ",";
		for (FlooringItem trim : newTrim) {
			wallString += trim.getName() + ":";
		}
		return wallString.substring(0, wallString.length() - 1);
    }

}
