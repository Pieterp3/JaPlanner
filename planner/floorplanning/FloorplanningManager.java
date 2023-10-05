package floorplanning;

import floorplanning.entity.Company;
import floorplanning.entity.Customer;
import floorplanning.items.FlooringItem;
import floorplanning.items.FlooringItemManager;
import util.io.Load;
import util.structures.List;
import util.structures.Map;

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
	private boolean updateNames;
	
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
		// floorplans = loadFloorplans();
		// estimates = loadEstimates();
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

}
