package floorplanning.display.panels;

import floorplanning.entity.Customer;
import ui.Panel;
import ui.components.DrawnComponent;
import ui.components.containers.impl.ComponentList;
import ui.components.impl.Button;
import ui.components.impl.Label;
import ui.components.impl.UserInput;
import ui.frames.Frame;
import util.events.Event;
import util.events.EventTimer;
import util.structures.List;

/**
 * Displays all customers in a list and lets the user select one
 * 
 * The user can then view the customer's information
 * 
 * The user can also create a new customer
 * 
 * The user can modify the selected customers information
 * 
 * The user can delete the selected customer
 * - deleted users are moved to a 'deleted' list not shown to the user
 * 
 * The user can also search for a customer
 * 
 * The customers estimates are also shown in a list
 * - The user can select an estimate to view it
 * - This opens the estimate viewer
 * 
 * Shows which companies the customer has worked with
 * - The user can select a company to view it
 * - This opens the company viewer
 * 
 * 
 */
public class CustomerPanel extends Panel {

	private Label customerViewLabel;
	private ComponentList customerList;
	private ComponentList estimateList;
	private ComponentList companyList;
	private Label[] informationLabels;
	private Button[] modButtons;
	private Button backButton;

	private String searchText;
	private UserInput searchInput;
	private Button searchButton;

	private HomePanel homePanel;

	public CustomerPanel(Frame frame, HomePanel homePanel) {
		super(frame, "customer panel");
		this.homePanel = homePanel;
	}

	@Override
	public void update() {
		buildCustomerList();
		modifyPositions();
	}

	private void modifyPositions() {
		customerViewLabel.setAttribute("width", getWidth());
		customerList.setAttribute("height", getHeight() - 10 - customerList.getY());
		estimateList.setAttribute("height", getHeight() * .7);
		companyList.setAttribute("y", estimateList.getY() + estimateList.getHeight() + 10);
		companyList.setAttribute("height", getHeight() - 10 - companyList.getY());
		for (int i = 0; i < informationLabels.length; i++) {
			informationLabels[i].setAttribute("width", getWidth() - 10 - informationLabels[i].getX());
		}
		for (int i = 0; i < modButtons.length; i++) {
			modButtons[i].setAttribute("width", getWidth() - 10 - modButtons[i].getX());
		}
		searchInput.setAttribute("width", getWidth() - 10 - searchInput.getX());
		searchButton.setAttribute("width", getWidth() - 10 - searchButton.getX());
		backButton.setAttribute("width", getWidth() - 10 - backButton.getX());
	}

	private void buildCustomerList() {
		List<String> customerNames = new List<String>();
		for (Customer c : homePanel.getManager().getCustomers()) {
			if (searchText == null || searchText.isEmpty()) {
				customerNames.add(c.getName());
			} else if (c.getName().toLowerCase().contains(searchText.toLowerCase())) {
				customerNames.add(c.getName());
			}
		}
		for (int i = 2; i < customerList.getComponentCount(); i++) {
			DrawnComponent c = customerList.getComponent(i);
			if (!customerNames.contains(c.getAttribute("text"))) {
				customerList.removeComponent(c);
			}
		}
		for (String name : customerNames) {
			boolean contains = false;
			for (DrawnComponent c : customerList.getComponents()) {
				if (c.getAttribute("text").equals(name)) {
					contains = true;
					break;
				}
			}
			if (!contains) {
				Button b = new Button(getFrame(), name, 0, 0, 0, 40);
				b.setAttribute("alignment", "center");
				b.setAttribute("action", "select=" + name);
				customerList.addComponent(b);
			}
		}
	}

	@Override
	protected void init() {
		customerViewLabel = new Label(getFrame(), "Customer Viewer", 0, 10, getWidth(), 25);
		customerViewLabel.setAttribute("alignment", "center");
		addComponent(customerViewLabel);
		customerList = new ComponentList(getFrame(), 10, 60, 225, getHeight() - 70);
		customerList.setAttribute("alignment", "center");
		addComponent(customerList);
		estimateList = new ComponentList(getFrame(), 245, 60, 225, getHeight() - 510);
		estimateList.setAttribute("alignment", "center");
		addComponent(estimateList);
		companyList = new ComponentList(getFrame(), 245, getHeight() - 440, 225, 430);
		companyList.setAttribute("alignment", "center");
		addComponent(companyList);
		modButtons = new Button[4];
		for (int i = 0; i < modButtons.length; i++) {
			modButtons[i] = new Button(getFrame(), "Button " + i, 480, 60 + (i * 40), getWidth() - 490, 40);
			modButtons[i].setAttribute("alignment", "center");
			modButtons[i].setAttribute("action", "modbutton" + i);
			addComponent(modButtons[i]);
		}
		informationLabels = new Label[14];
		for (int i = 0; i < informationLabels.length; i++) {
			int x = 480;
			int y = 230;
			String alignment = "left";
			if (i >= (informationLabels.length / 2)) {
				y += 30 * (i - (informationLabels.length / 2));
				alignment = "right";
			} else {
				y += 30 * i;
			}
			informationLabels[i] = new Label(getFrame(), "Label " + i, x, y, getWidth() - 505, 30);
			informationLabels[i].setAttribute("alignment", alignment);
			addComponent(informationLabels[i]);
		}
		searchInput = new UserInput(getFrame(), "", 480, getHeight() - 370, getWidth() - 490, 40, "Search");
		searchInput.setAttribute("alignment", "center");
		addComponent(searchInput);
		searchButton = new Button(getFrame(), "Search", 480, getHeight() - 320, getWidth() - 490, 40);
		searchButton.setAttribute("alignment", "center");
		searchButton.setAttribute("action", "search");
		addComponent(searchButton);
		backButton = new Button(getFrame(), "Back", 480, getHeight() - 270, getWidth() - 490, 40);
		backButton.setAttribute("alignment", "center");
		backButton.setAttribute("action", "back");
		addComponent(backButton);
	}

	@Override
	public void handleAction(String command) {
		switch (command) {
			case "search":
				searchText = searchInput.getText();
				searchInput.setText("");
				buildCustomerList();
				break;
			case "back":
				getFrame().setActivePanel(homePanel);
				break;
			default:
				if (command.startsWith("modbutton")) {
					// Modify the selected customer
					break;
				}
				// Select a customer
				break;
		}
	}

	@Override
	public void keyTyped(int c) {}

	@Override
	public void preComponentDrawing() {}

	@Override
	public void finishPanelDrawing() {}

}
