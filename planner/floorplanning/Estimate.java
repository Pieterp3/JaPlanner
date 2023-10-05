package floorplanning;

import floorplanning.entity.*;
import floorplanning.room.Room;
import util.structures.List;

/**
 * Stores all of the estimate information
 * 		- Customer
 * 		- Company
 * 		- Rooms
 * 		- Notes
 * 		- Method to get total cost
 * 		- Address
 * 		- Floorplan reference
 * 
 */
public class Estimate {
	
	private List<Room> rooms;
	private Customer customer;
	private Company company;
	private Floorplan floorplan;
	private String notes;

	public Estimate(Customer customer, Company company, Floorplan floorplan, String notes) {
		this.customer = customer;
		this.company = company;
		this.floorplan = floorplan;
		this.notes = notes;
	}

}
