package floorplanning.room;

import util.structures.List;

/**
 * Stores information about a room
 */
public class Room {
	
	//Most rooms are one section unlesss they have multiple floorings installed
	private List<RoomSection> sections;
	private String name;
	
	public Room(String name) {
		this.name = name;
		sections = new List<>();
	}

	public void addSection(RoomSection section) {
		sections.add(section);
	}

	public List<RoomSection> getSections() {
		return sections;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		double cost = 0;
		for (RoomSection section : sections) {
			cost += section.getCost();
		}
		return cost;
	}

}
