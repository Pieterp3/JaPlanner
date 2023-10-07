package floorplanning.display.panels;

import floorplanning.FloorplanningManager;
import ui.Panel;
import ui.components.impl.Button;
import ui.components.impl.Label;
import ui.frames.Frame;

/**
 * The user logged in successfully
 * 
 * Display buttons to ask the user what they want to do
 * 
 * options are
 *  - View customers
 * 	- View companies
 *  - View Price sheets
 *  - View Estimates
 *  - View Floorplans
 *  - Logout
 *  - Change settings
 * 
 * 
 */
public class HomePanel extends Panel {

	private FloorplanningManager manager;
	private WelcomePanel welcomePanel;
	private CustomerPanel customerPanel;

	private Label directingLabel;
	private Button customersButton;
	private Button companiesButton;
	private Button priceSheetsButton;
	private Button estimatesButton;
	private Button floorplansButton;
	private Button logoutButton;
	private Button settingsButton;

	public HomePanel(Frame frame, WelcomePanel welcomePanel) {
		super(frame, "floorplan welcome");
		manager = new FloorplanningManager();
		this.welcomePanel = welcomePanel;
		this.customerPanel = new CustomerPanel(frame, this);
	}

	@Override
	public void update() {
		customersButton.setAttribute("width", getWidth() - 20);
		companiesButton.setAttribute("width", getWidth() - 20);
		priceSheetsButton.setAttribute("width", getWidth() - 20);
		estimatesButton.setAttribute("width", getWidth() - 20);
		floorplansButton.setAttribute("width", getWidth() - 20);
		logoutButton.setAttribute("width", getWidth() - 20);
		settingsButton.setAttribute("width", getWidth() - 20);
	}

	@Override
	protected void init() {
		directingLabel = new Label(getFrame(), "What would you like to do?", 0, 10, getWidth(), 25);
		directingLabel.setAttribute("alignment", "center");
		addComponent(directingLabel);
		customersButton = new Button(getFrame(), "View Customers", 10, 60, getWidth() - 20, 40);
		customersButton.setAttribute("alignment", "center");
		customersButton.setAttribute("action", "viewcustomers");
		addComponent(customersButton);
		companiesButton = new Button(getFrame(), "View Companies", 10, 110, getWidth() - 20, 40);
		companiesButton.setAttribute("alignment", "center");
		companiesButton.setAttribute("action", "viewcompanies");
		addComponent(companiesButton);
		priceSheetsButton = new Button(getFrame(), "View Price Sheets", 10, 160, getWidth() - 20, 40);
		priceSheetsButton.setAttribute("alignment", "center");
		priceSheetsButton.setAttribute("action", "viewpricesheets");
		addComponent(priceSheetsButton);
		estimatesButton = new Button(getFrame(), "View Estimates", 10, 210, getWidth() - 20, 40);
		estimatesButton.setAttribute("alignment", "center");
		estimatesButton.setAttribute("action", "viewestimates");
		addComponent(estimatesButton);
		floorplansButton = new Button(getFrame(), "View Floorplans", 10, 260, getWidth() - 20, 40);
		floorplansButton.setAttribute("alignment", "center");
		floorplansButton.setAttribute("action", "viewfloorplans");
		addComponent(floorplansButton);
		logoutButton = new Button(getFrame(), "Logout", 10, 310, getWidth() - 20, 40);
		logoutButton.setAttribute("alignment", "center");
		logoutButton.setAttribute("action", "logout");
		addComponent(logoutButton);
		settingsButton = new Button(getFrame(), "Change Settings", 10, 360, getWidth() - 20, 40);
		settingsButton.setAttribute("alignment", "center");
		settingsButton.setAttribute("action", "changesettings");
		addComponent(settingsButton);
	}

	public FloorplanningManager getManager() {
		return manager;
	}

	@Override
	public void handleAction(String command) {
		switch (command) {
			case "viewcustomers":
				getFrame().setActivePanel(customerPanel);
				break;
			case "viewcompanies":
				//getFrame().setActivePanel(new CompanyPanel(getFrame()));
				break;
			case "viewpricesheets":
				//getFrame().setActivePanel(new PriceSheetPanel(getFrame()));
				break;
			case "viewestimates":
				//getFrame().setActivePanel(new EstimatePanel(getFrame()));
				break;
			case "viewfloorplans":
				//getFrame().setActivePanel(new FloorplanPanel(getFrame()));
				break;
			case "logout":
				getFrame().setActivePanel(new WelcomePanel(getFrame()));
				break;
			case "changesettings":
				//getFrame().setActivePanel(new SettingsPanel(getFrame()));
				break;
			default:
				System.out.println("Unknown command: " + command);
				break;
		}
		getFrame().getActivePanel().update();
	}

	@Override
	public void keyTyped(int c) {}

	@Override
	public void preComponentDrawing() {}

	@Override
	public void finishPanelDrawing() {}

}
