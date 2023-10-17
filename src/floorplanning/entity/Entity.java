package floorplanning.entity;

import floorplanning.Estimate;
import util.structures.lists.List;

public class Entity {
	
	private String name, address, phoneNumber, email, notes;
	private List<Estimate> estimates;

	public Entity(String name, String address, String phoneNumber, String email, String notes) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.notes = notes;
		estimates = new List<>();
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getNotes() {
		return notes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		if (address != null) {
			this.address = address;
		}
	}

	public void setPhoneNumber(String phoneNumber) {
		if (phoneNumber != null) {
			this.phoneNumber = phoneNumber;
		}
	}

	public void setEmail(String email) {
		if (email != null) {
			this.email = email;
		}
	}

	public void setNotes(String notes) {
		if (notes != null) {
			this.notes = notes;
		}
	}

	public void addEstimate(Estimate estimate) {
		estimates.add(estimate);
	}

	public List<Estimate> getEstimates() {
		return estimates;
	}

}
