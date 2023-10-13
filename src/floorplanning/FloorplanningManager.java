package floorplanning;

import floorplanning.entity.Company;
import floorplanning.entity.Customer;
import floorplanning.items.FlooringItemManager;
import floorplanning.room.Room;
import util.io.Load;
import util.io.Save;
import util.structures.List;

/**
 * Manages walking the user through the floorplanning process
 * 
 * 1. Select or create the company doing the estimate
 * 2. Select or create the customer
 * 3. Determine whether estimate will have plans made
 * 4. Create Floorplan if selected
 * 5. Build estimate
 * 6. Save estimate
 * 
 */
public class FloorplanningManager {

	private List<Company> companies;
	private List<Customer> customers;
	private List<Estimate> estimates;
	private List<Floorplan> floorplans;
	private List<String> companyNames, customerNames;

	public FloorplanningManager() {
		companies = new List<>();
		customers = new List<>();
		estimates = new List<>();
		floorplans = new List<>();
		companyNames = new List<>();
		customerNames = new List<>();
		FlooringItemManager.init();
		companies = loadCompanies();
		customers = loadCustomers();
		estimates = loadEstimates();
		// floorplans = loadFloorplans();
	}

	public void save() {
		saveCompanies();
		saveCustomers();
		saveEstimates();
		saveFloorplans();
	}

	private void saveFloorplans() {
		List<String> data = new List<>();
		for (Floorplan floorplan : floorplans) {
			String line = floorplan.getId();
			data.add(line);
		}
		Save.saveDataFile("FloorplanData", data);
	}

	private void saveEstimates() {
		List<String> data = new List<>();
		for (Estimate estimate : estimates) {
			String line = estimate.getId() + "=" + estimate.getCompany().getName() + "="
					+ estimate.getCustomer().getName() + "=" + estimate.getNotes();
			data.add(line);
		}
		Save.saveDataFile("EstimateData", data);
	}

	private void saveCustomers() {
		List<String> data = new List<>();
		for (Customer customer : customers) {
			String line = customer.getName() + "=" + customer.getAddress() + "=" + customer.getPhoneNumber() + "="
					+ customer.getEmail() + "=" + customer.getNotes();
			data.add(line);
		}
		Save.saveDataFile("CustomerData", data);
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
		customerNames.add(customer.getName());
	}

	public void addEstimate(Estimate estimate) {
		estimates.add(estimate);
	}

	private void saveCompanies() {
		List<String> data = new List<>();
		for (Company company : companies) {
			String line = company.getName() + "=" + company.getAddress() + "=" + company.getPhoneNumber() + "="
					+ company.getEmail() + "=" + company.getNotes();
			data.add(line);
		}
		Save.saveDataFile("CompanyData", data);
	}

	public void addCompany(Company company) {
		companies.add(company);
		companyNames.add(company.getName());
	}

	private List<Estimate> loadEstimates() {
		List<Estimate> estimates = new List<>();
		List<String> data = Load.loadData("EstimateData");
		for (String line : data) {
			String[] values = line.split("=");
			String id = values[0];
			Company company = getCompany(values[1]);
			Customer customer = getCustomer(values[2]);
			String notes = values[3];
			String jobAddress = values[4];
			List<Room> rooms = new List<>();
			String[] roomStrings = values[5].split(",");
			for (String roomString : roomStrings) {
				rooms.add(new Room(roomString));
			}
			Floorplan floorplan = null;
			if (values.length > 6) {
				floorplan = getFloorplan(values[6]);
			}
			Estimate estimate = new Estimate(id, customer, company, notes, jobAddress, rooms, floorplan);
			estimates.add(estimate);
		}
		return estimates;
	}

	private Floorplan getFloorplan(String string) {
		for (Floorplan floorplan : floorplans) {
			if (floorplan.getId().equals(string)) {
				return floorplan;
			}
		}
		return null;
	}

	private List<Company> loadCompanies() {
		List<Company> companies = new List<>();
		List<String> data = Load.loadData("CompanyData");
		for (String line : data) {
			String[] values = line.split("=");
			String name = values[0];
			String address = values[1];
			String phoneNumber = values[2];
			String email = values[3];
			String notes = values[4];
			Company company = new Company(name, address, phoneNumber, email, notes);
			companies.add(company);
			companyNames.add(name);
		}
		return companies;
	}

	private List<Customer> loadCustomers() {
		List<Customer> customers = new List<>();
		List<String> data = Load.loadData("CustomerData");
		for (String line : data) {
			String[] values = line.split("=");
			String name = values[0];
			String address = values[1];
			String phoneNumber = values[2];
			String email = values[3];
			String notes = values[4];
			Customer customer = new Customer(name, address, phoneNumber, email, notes);
			customers.add(customer);
			customerNames.add(name);
		}
		return customers;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public Company getCompany(String name) {
		for (Company company : companies) {
			if (company.getName().equalsIgnoreCase(name)) {
				return company;
			}
		}
		return null;
	}

	public Customer getCustomer(String name) {
		for (Customer customer : customers) {
			if (customer.getName().equalsIgnoreCase(name)) {
				return customer;
			}
		}
		return null;
	}

	public Estimate getEstimate(String id) {
		for (Estimate estimate : estimates) {
			if (estimate.getId().equals(id)) {
				return estimate;
			}
		}
		return null;
	}

	public void addFloorplan(Floorplan floorplan) {
		floorplans.add(floorplan);
	}

	public List<Floorplan> getFloorplans() {
		return floorplans;
	}

	public void removeCustomer(String name) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getName().equals(name)) {
				customers.remove(i);
				customerNames.remove(i);
				return;
			}
		}
	}

	public void removeCustomer(Customer customer) {
		removeCustomer(customer.getName());
	}

	public void removeCompany(String name) {
		for (int i = 0; i < companies.size(); i++) {
			if (companies.get(i).getName().equals(name)) {
				companies.remove(i);
				companyNames.remove(i);
				return;
			}
		}
	}

	public void removeCompany(Company company) {
		removeCompany(company.getName());
	}

	public void removeEstimate(String id) {
		for (int i = 0; i < estimates.size(); i++) {
			if (estimates.get(i).getId().equals(id)) {
				estimates.remove(i);
				return;
			}
		}
	}

	public void removeEstimate(Estimate estimate) {
		removeEstimate(estimate.getId());
	}

}
