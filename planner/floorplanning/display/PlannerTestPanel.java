package floorplanning.display;

import floorplanning.FloorplanningManager;
import floorplanning.entity.Company;
import floorplanning.entity.Customer;
import floorplanning.entity.Entity;
import floorplanning.items.FlooringItemManager;
import floorplanning.items.Pricing;
import ui.Panel;
import ui.components.containers.impl.ComponentList;
import ui.components.impl.Button;
import ui.components.impl.Label;
import ui.frames.Frame;
import util.math.Misc;

public class PlannerTestPanel extends Panel {

	private DeletionPopup deletionPopup;
	private CreationPopup creationPopup;
	private ModifyPopup modifyPopup;

	private FloorplanningManager manager;
	private ComponentList[] lists;
	public static final int SPACING = 9;
	public static final int COMPLISTINDEX = 0;
	public static final int CUSTLISTINDEX = 1;
	public static final int PRICELISTINDEX = 2;
	private static final String hoveredColor = "222222";
	private static String defaultColor;

	private Company selectedCompany;
	private Customer selectedCustomer;
	private Pricing selectedItem;
	private int selectedListIndex = 0;

	private Label[] listDisplayedLabels;
	private Button[] modButtons;

	public PlannerTestPanel(Frame frame) {
		super(frame, "plannertestpanel");
	}

	@Override
	protected void init() {
		Label label = new Label(getFrame(), "Floorplanning Test Project.", 0, 10, getWidth(), 40);
		label.setAttribute("alignment", "center");
		addComponent(label);
		manager = new FloorplanningManager();
		addLists();
		addLabels();
		addButtons();
	}

	private void addLists() {
		lists = new ComponentList[3];
		String[][] names = new String[lists.length][];
		names[0] = new String[manager.getCompanies().size()];
		names[1] = new String[manager.getCustomers().size()];
		names[2] = new String[FlooringItemManager.getFlooringItemNames().size()];
		for (int i = 0; i < manager.getCompanies().size(); i++) {
			names[0][i] = manager.getCompanies().get(i).getName();
		}
		for (int i = 0; i < manager.getCustomers().size(); i++) {
			names[1][i] = manager.getCustomers().get(i).getName();
		}
		for (int i = 0; i < FlooringItemManager.getFlooringItemNames().size(); i++) {
			names[2][i] = FlooringItemManager.getFlooringItemNames().get(i);
		}

		for (int i = 0; i < names.length; i++) {
			ComponentList list = new ComponentList(getFrame(), 0, 50, 0, 300);
			for (int j = 0; j < names[i].length; j++) {
				Button b = new Button(getFrame(), names[i][j], 0, 0, 0, 40);
				b.setAttribute("alignment", "center");
				list.addComponent(b);
				b.setAttribute("action", "display" + i + "=" + b.getAttribute("text"));
			}
			addComponent(list);
			lists[i] = list;
		}
		defaultColor = lists[selectedListIndex].getAttribute("backgroundColor");
		lists[selectedListIndex].setAttribute("backgroundColor", hoveredColor);
		lists[selectedListIndex].setAttribute("backgroundHoverColor", hoveredColor);
	}

	private void addLabels() {
		listDisplayedLabels = new Label[10];
		for (int i = 0; i < 5; i++) {
			Label l = new Label(getFrame(), "LEFT", 0, 360 + (i * 30) + (i * SPACING), 0, 0);
			l.setAttribute("alignment", "center");
			addComponent(l);
			listDisplayedLabels[i] = l;
		}
		for (int i = 0; i < 5; i++) {
			Label l = new Label(getFrame(), "RIGHT", 0, 360 + (i * 30) + (i * SPACING),
					0, 0);
			l.setAttribute("alignment", "center");
			addComponent(l);
			listDisplayedLabels[i + 5] = l;
		}
	}

	private void addButtons() {
		String[] phText = new String[] { "CREATE", "DELETE", "11", "12", "13", "14" };
		modButtons = new Button[phText.length];
		for (int i = 0; i < phText.length; i++) {
			Button b = new Button(getFrame(), phText[i], 0, 360 + (i * (SPACING + 25)), 1, 25);
			b.setAttribute("alignment", "center");
			addComponent(b);
			b.setAttribute("action", "MOD=" + i);
			modButtons[i] = b;
		}
	}

	@Override
	public void update() {
		double width = ((double) getWidth()) / lists.length;
		int listWidth = (int) width - SPACING * 2;
		for (int i = 0; i < lists.length; i++) {
			ComponentList list = lists[i];
			list.setAttribute("width", listWidth);
			list.setAttribute("x", (listWidth * i) + ((i + 1) * SPACING) + SPACING);
			list.repositionComponents();
		}
		for (int i = 0; i < modButtons.length; i++) {
			Button b = modButtons[i];
			b.setAttribute("x", (listWidth * 2) + (4 * SPACING));
			b.setAttribute("width", listWidth);
		}
		for (int i = 0; i < 5; i++) {
			listDisplayedLabels[i].setAttribute("x", SPACING * 2);
			listDisplayedLabels[i].setAttribute("width", listWidth);
			listDisplayedLabels[i + 5].setAttribute("x", listWidth + SPACING * 3);
			listDisplayedLabels[i + 5].setAttribute("width", listWidth);
		}
		for (int i = 0;i<lists.length;i++) {
			if (i==selectedListIndex) {
				lists[i].setAttribute("backgroundColor", hoveredColor);
				lists[i].setAttribute("backgroundHoverColor", hoveredColor);
			} else {
				lists[i].setAttribute("backgroundColor", defaultColor);
				lists[i].setAttribute("backgroundHoverColor", defaultColor);
			}
		}
		listDisplayedLabels[0].setAttribute("text", "NAME");
		String createNewText = "CREATE NEW ";
		String deleteText = "DELETE ";
		switch (selectedListIndex) {
			case COMPLISTINDEX:
			case CUSTLISTINDEX:
				listDisplayedLabels[1].setAttribute("text", "ADDRESS");
				listDisplayedLabels[2].setAttribute("text", "PHONE NUMBER");
				listDisplayedLabels[3].setAttribute("text", "EMAIL");
				listDisplayedLabels[4].setAttribute("text", "NOTES");
				createNewText += selectedListIndex == COMPLISTINDEX ? "COMPANY" : "CUSTOMER";
				deleteText += selectedListIndex == COMPLISTINDEX ? "COMPANY" : "CUSTOMER";
				modButtons[2].setAttribute("text", "MODIFY ADDRESS");
				modButtons[3].setAttribute("text", "MODIFY PHONE NUMBER");
				modButtons[4].setAttribute("text", "MODIFY EMAIL");
				modButtons[5].setAttribute("text", "MODIFY NOTES");
				Entity selected = selectedListIndex == COMPLISTINDEX ? selectedCompany : selectedCustomer;
				if (selected != null) {
					listDisplayedLabels[5].setAttribute("text", selected.getName());
					listDisplayedLabels[6].setAttribute("text", selected.getAddress());
					listDisplayedLabels[7].setAttribute("text", selected.getPhoneNumber());
					listDisplayedLabels[8].setAttribute("text", selected.getEmail());
					listDisplayedLabels[9].setAttribute("text", selected.getNotes());
				}
				break;
			case PRICELISTINDEX:
				listDisplayedLabels[1].setAttribute("text", "INSTALL PRICE");
				listDisplayedLabels[2].setAttribute("text", "MATERIAL PRICE");
				listDisplayedLabels[3].setAttribute("text", "REMOVAL PRICE");
				listDisplayedLabels[4].setAttribute("text", "MOVE AND REPLACE PRICE");
				listDisplayedLabels[5].setAttribute("text", selectedItem.getName());
				listDisplayedLabels[6].setAttribute("text", Misc.formatCurrency(selectedItem.getInstallPrice()));
				listDisplayedLabels[7].setAttribute("text", Misc.formatCurrency(selectedItem.getMaterialPrice()));
				listDisplayedLabels[8].setAttribute("text", Misc.formatCurrency(selectedItem.getRemovalPrice()));
				listDisplayedLabels[9].setAttribute("text", Misc.formatCurrency(selectedItem.getMoveAndReplacePrice()));
				createNewText += "ITEM";
				deleteText += "ITEM";
				modButtons[2].setAttribute("text", "MODIFY INSTALL PRICE");
				modButtons[3].setAttribute("text", "MODIFY MATERIAL PRICE");
				modButtons[4].setAttribute("text", "MODIFY REMOVAL PRICE");
				modButtons[5].setAttribute("text", "MODIFY MOVE AND REPLACE PRICE");
				break;
		}
		modButtons[0].setAttribute("text", createNewText);
		modButtons[1].setAttribute("text", deleteText);
	}

	@Override
	public void handleAction(String command) {
		System.out.println("Processing PlannerTestPanel action: " + command);
		if (command.startsWith("display")) {
			command = command.substring(7);
			String[] parts = command.split("=");
			display(parts[0], parts[1]);
		} else if (command.startsWith("MOD=")) {
			processModButton(Integer.valueOf(command.substring(4)));
		}
	}

	private void display(String displayType, String displayName) {
		int displayTypeInt = Integer.parseInt(displayType);
		switch (displayTypeInt) {
			case COMPLISTINDEX:
				selectedCompany = manager.getCompany(displayName);
				break;
			case CUSTLISTINDEX:
				selectedCustomer = manager.getCustomer(displayName);
				break;
			case PRICELISTINDEX:
				selectedItem = FlooringItemManager.getPricing(displayName);
				break;
		}
		selectedListIndex = displayTypeInt;
	}

	private void processModButton(int button) {
		switch (button) {
			case 0:
				if (creationPopup == null) {
					creationPopup = new CreationPopup(this);
				}
				creationPopup.display();
				break;
			case 1:
				if (deletionPopup == null) {
					deletionPopup = new DeletionPopup(this);
				}
				deletionPopup.display();
				break;
			case 2:
			case 3:
			case 4:
			case 5:
				if (modifyPopup == null) {
					modifyPopup = new ModifyPopup(this);
				}
				modifyPopup.display(button);
				break;
			default:
				// displayModifyFrame(button);
				System.out.println("Unhandeled mod button: " + button);
				break;
		}
	}

	@Override
	public void keyTyped(int c) {}

	@Override
	public void preComponentDrawing() {}

	@Override
	public void finishPanelDrawing() {}

	public int getSelectedListIndex() {
		return selectedListIndex;
	}

    public String getModPlaceholder(int modificationIndex) {
		modificationIndex -= 2;
        switch (selectedListIndex) {
			case COMPLISTINDEX:
				getCompanyData(modificationIndex);
				break;
			case CUSTLISTINDEX:
				getCustomerData(modificationIndex);
				break;
			case PRICELISTINDEX:
				getPricingData(modificationIndex);
				break;
		}
		return "";
    }

	private String getCompanyData(int modificationIndex) {
		if (selectedCompany == null) return "";
		switch (modificationIndex) {
			case 0:
				return selectedCompany.getAddress();
			case 1:
				return selectedCompany.getPhoneNumber();
			case 2:
				return selectedCompany.getEmail();
			case 3:
				return selectedCompany.getNotes();
		}
		return "";
	}

	private String getCustomerData(int modificationIndex) {
		if (selectedCustomer == null) return "";
		switch (modificationIndex) {
			case 0:
				return selectedCustomer.getAddress();
			case 1:
				return selectedCustomer.getPhoneNumber();
			case 2:
				return selectedCustomer.getEmail();
			case 3:
				return selectedCustomer.getNotes();
		}
		return "";
	}

	private String getPricingData(int modificationIndex) {
		if (selectedItem == null) return "";
		switch (modificationIndex) {
			case 0:
				return Misc.formatCurrency(selectedItem.getInstallPrice());
			case 1:
				return Misc.formatCurrency(selectedItem.getMaterialPrice());
			case 2:
				return Misc.formatCurrency(selectedItem.getRemovalPrice());
			case 3:
				return Misc.formatCurrency(selectedItem.getMoveAndReplacePrice());
		}
		return "";
	}

}
