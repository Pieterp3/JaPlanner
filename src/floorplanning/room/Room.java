package floorplanning.room;

import floorplanning.items.FlooringItem;
import util.structures.List;

/**
 * Stores info about a room
 * 
 * - Name
 * - Subfloor
 * - List of walls
 * - Existing flooring
 * - New flooring
 * - Other charges
 * 
 * 
 * 
 * 
 */
public class Room {

	private String name;
	private Subfloor subfloor;
	private List<Wall> walls;
	private FlooringItem existingFlooring;
	private FlooringItem newFlooring;
	private List<FlooringItem> otherCharges;

	public Room(String name, Subfloor subfloor, List<Wall> walls, FlooringItem existingFlooring,
			FlooringItem newFlooring, List<FlooringItem> otherCharges) {
		this.name = name;
		this.subfloor = subfloor;
		this.walls = walls;
		this.existingFlooring = existingFlooring;
		this.newFlooring = newFlooring;
		this.otherCharges = otherCharges;
	}

	public Room(String roomString) {
		String[] roomInfo = roomString.split(";");
		name = roomInfo[0];
		subfloor = Subfloor.getSubfloor(roomInfo[1]);
		if (!roomInfo[2].equals("")) {
			existingFlooring = new FlooringItem(roomInfo[2], 1);
		}
		if (!roomInfo[3].equals("")) {
			newFlooring = new FlooringItem(roomInfo[3], 1);
		}
		walls = new List<>();
		String[] wallStrings = roomInfo[4].split(",");
		for (String wallString : wallStrings) {
			walls.add(new Wall(wallString));
		}
		otherCharges = new List<>();
		for (int i = 5; i < roomInfo.length; i++) {
			otherCharges.add(new FlooringItem(roomInfo[i], 1));
		}
	}

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		double cost = 0;
		for (Wall wall : walls) {
			cost += wall.getCost();
		}
		if (existingFlooring != null) {
			cost += existingFlooring.getCost();
		}
		if (newFlooring != null) {
			cost += newFlooring.getCost();
		}
		for (FlooringItem otherCharge : otherCharges) {
			cost += otherCharge.getCost();
		}
		return cost;
	}

	public Subfloor getSubfloor() {
		return subfloor;
	}

	public void setSubfloor(Subfloor subfloor) {
		this.subfloor = subfloor;
	}

	public List<Wall> getWalls() {
		return walls;
	}

	public FlooringItem getExistingFlooring() {
		return existingFlooring;
	}

	public void setExistingFlooring(FlooringItem existingFlooring) {
		this.existingFlooring = existingFlooring;
	}

	public FlooringItem getNewFlooring() {
		return newFlooring;
	}

	public void setNewFlooring(FlooringItem newFlooring) {
		this.newFlooring = newFlooring;
	}

	public List<FlooringItem> getOtherCharges() {
		return otherCharges;
	}

	public String getRoomString() {
		String roomString = name + ";";
		roomString += subfloor.getName() + ";";
		if (existingFlooring != null) {
			roomString += existingFlooring.getName();
		}
		roomString += ";";
		if (newFlooring != null) {
			roomString += newFlooring.getName();
		}
		roomString += ";";
		for (Wall wall : walls) {
			roomString += wall.getWallString() + ",";
		}
		roomString = roomString.substring(0, roomString.length() - 1) + ";";
		return roomString;
	}

	public void addWall(Wall wall) {
		walls.add(wall);
	}

	public void removeWall(Wall wall) {
		walls.remove(wall);
	}

	public void addOtherCharge(FlooringItem otherCharge) {
		otherCharges.add(otherCharge);
	}

	public void removeOtherCharge(FlooringItem otherCharge) {
		otherCharges.remove(otherCharge);
	}

}
