package floorplanning.room;

import floorplanning.items.FlooringItem;
import util.structures.List;

public class RoomSection {

	private List<Wall> walls;
	private FlooringItem existingFlooring;
	private FlooringItem newFlooring;
	private Subfloor subfloor;
	private List<FlooringItem> otherCharges;

	public RoomSection(Subfloor subfloor) {
		walls = new List<>();
		otherCharges = new List<>();
		this.subfloor = subfloor;
	}

	public void addWall(Wall wall) {
		walls.add(wall);
	}

	public void setExistingFlooring(FlooringItem existingFlooring) {
		this.existingFlooring = existingFlooring;
	}

	public void setNewFlooring(FlooringItem newFlooring) {
		this.newFlooring = newFlooring;
	}

	public void setSubfloor(Subfloor subfloor) {
		this.subfloor = subfloor;
	}

	public void addOtherCharge(FlooringItem otherCharge) {
		otherCharges.add(otherCharge);
	}

	public List<Wall> getWalls() {
		return walls;
	}

	public FlooringItem getExistingFlooring() {
		return existingFlooring;
	}

	public FlooringItem getNewFlooring() {
		return newFlooring;
	}

	public Subfloor getSubfloor() {
		return subfloor;
	}

	public List<FlooringItem> getOtherCharges() {
		return otherCharges;
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

}
