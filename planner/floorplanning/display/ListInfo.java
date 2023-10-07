package floorplanning.display;

import floorplanning.entity.Company;
import floorplanning.entity.Customer;
import floorplanning.items.Pricing;
import util.math.Misc;

public enum ListInfo {
	COMPANY(new String[] { "NAME", "ADDRESS", "PHONE NUMBER", "EMAIL", "NOTES" }), CUSTOMER(
			new String[] { "NAME", "ADDRESS", "PHONE NUMBER", "EMAIL", "NOTES" }), PRICING(new String[] { "NAME",
					"INSTALL PRICE", "MATERIAL PRICE", "REMOVAL PRICE", "MOVE AND REPLACE PRICE" });

	private String[] labelInfo;
	private String[] selectedDisplayValues;

	ListInfo(String[] labelInfo) {
		this.labelInfo = labelInfo;
	}

	public void setSelectedCompany(Company selectedCompany) {
		this.selectedDisplayValues = new String[] { selectedCompany.getName(), selectedCompany.getAddress(),
				selectedCompany.getPhoneNumber(), selectedCompany.getEmail(), selectedCompany.getNotes() };
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedDisplayValues = new String[] { selectedCustomer.getName(), selectedCustomer.getAddress(),
				selectedCustomer.getPhoneNumber(), selectedCustomer.getEmail(), selectedCustomer.getNotes() };
	}

	public void setSelectedItem(Pricing selectedItem) {
		this.selectedDisplayValues = new String[] { selectedItem.getName(),
				Misc.formatCurrency(selectedItem.getInstallPrice()),
				Misc.formatCurrency(selectedItem.getMaterialPrice()),
				Misc.formatCurrency(selectedItem.getRemovalPrice()),
				Misc.formatCurrency(selectedItem.getMoveAndReplacePrice()) };
	}

	public String[] getLabelInfo() {
		return labelInfo;
	}

	public String getInfoType() {
		return this == COMPANY ? "Company" : this == CUSTOMER ? "Customer" : "Item";
	}

	public String[] getSelectedDisplayValues() {
		return selectedDisplayValues;
	}
}