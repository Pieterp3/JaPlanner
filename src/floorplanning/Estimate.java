package floorplanning;

import floorplanning.entity.*;
import floorplanning.room.Room;
import util.structures.lists.List;

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
	private String jobAddress;
	private Floorplan floorplan;
	private String notes;
	private String id;

	public Estimate(Customer customer, Company company, String notes) {
		this(String.valueOf(System.currentTimeMillis()), customer, company, notes, "");
	}

	public Estimate(String id, Customer customer, Company company, String notes, String jobAddress) {
		this(id, customer, company, notes, jobAddress, new List<>(), null);
	}

	public Estimate(String id, Customer customer, Company company, String notes, String jobAddress, List<Room> rooms, Floorplan floorplan) {
		this.id = id;
		this.customer = customer;
		this.company = company;
		this.notes = notes;
		this.jobAddress = jobAddress;
		this.rooms = rooms;
		this.floorplan = floorplan;
	}

	public String getJobAddress() {
		return jobAddress;
	}

	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

	public void removeRoom(Room room) {
		rooms.remove(room);
	}

	public String getRoomString() {
		String roomString = "";
		for (Room room : rooms) {
			roomString += room.getRoomString() + ",";
		}
		return roomString;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Company getCompany() {
		return company;
	}

	public String getNotes() {
		return notes;
	}

	public String getId() {
		return id;
	}

	public void setFloorplan(Floorplan floorplan) {
		this.floorplan = floorplan;
	}

	public Floorplan getFloorplan() {
		return floorplan;
	}

	public double getTotalCost() {
		double total = 0;
		for (Room room : rooms) {
			total += room.getCost();
		}
		return total;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
