package floorplanning.display;

import floorplanning.FloorplanningManager;
import floorplanning.display.popups.CreationPopup;
import floorplanning.display.popups.DeletionPopup;
import floorplanning.display.popups.ModifyPopup;
import floorplanning.items.FlooringItemManager;
import ui.Panel;
import ui.components.containers.impl.ComponentList;
import ui.components.impl.Button;
import ui.components.impl.Label;
import ui.frames.Frame;

public class PlannerTestPanel extends Panel {

	private DeletionPopup deletionPopup;
	private CreationPopup creationPopup;
	private ModifyPopup modifyPopup;
	
	private ComponentList[] lists;
	private ComponentList selectedList;

	private Label[] listDisplayedLabels;
	private Button[] modButtons;
	private Button[] firstListDisplayButtons;

	private FloorplanningManager manager;
	public static final int SPACING = 9;
	private static final String hoveredColor = "222222";
	private static String defaultColor;

	private ListInfo listInfo = ListInfo.COMPANY;

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
		selectedList = lists[0];
		defaultColor = selectedList.getAttribute("backgroundColor");
		selectedList.setAttribute("backgroundColor", hoveredColor);
		selectedList.setAttribute("backgroundHoverColor", hoveredColor);
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

		String[] displayFirstListButtons = new String[] { "Companies", "Customers",
				"Price lists" };
		firstListDisplayButtons = new Button[displayFirstListButtons.length];
		for (int i = 0; i < displayFirstListButtons.length; i++) {
			Button b = new Button(getFrame(), displayFirstListButtons[i], 0, 520 + (i * (SPACING + 25)), 1, 25);
			b.setAttribute("alignment", "center");
			addComponent(b);
			b.setAttribute("action", "firstlist" + i + "=" + b.getAttribute("text"));
			firstListDisplayButtons[i] = b;
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
		for (int i = 0;i<firstListDisplayButtons.length;i++) {
			
		}
		for (int i = 0; i < 5; i++) {
			listDisplayedLabels[i].setAttribute("x", SPACING * 2);
			listDisplayedLabels[i].setAttribute("width", listWidth);
			listDisplayedLabels[i + 5].setAttribute("x", listWidth + SPACING * 3);
			listDisplayedLabels[i + 5].setAttribute("width", listWidth);
		}
		for (int i = 0; i < lists.length; i++) {
			if (selectedList.equals(lists[i])) {
				lists[i].setAttribute("backgroundColor", hoveredColor);
				lists[i].setAttribute("backgroundHoverColor", hoveredColor);
			} else {
				lists[i].setAttribute("backgroundColor", defaultColor);
				lists[i].setAttribute("backgroundHoverColor", defaultColor);
			}
		}
		for (int i = 0; i < 5; i++) {
			listDisplayedLabels[i].setAttribute("text", listInfo.getLabelInfo()[i]);
			if (listInfo.getSelectedDisplayValues() != null)
				listDisplayedLabels[i + 5].setAttribute("text", listInfo.getSelectedDisplayValues()[i]);
		}
		for (int i = 2; i < 6; i++) {
			modButtons[i].setAttribute("text", "Modify " + listInfo.getLabelInfo()[i - 1]);
		}
		// listDisplayedLabels[0].setAttribute("text", "NAME");
		String createNewText = "CREATE NEW ";
		String deleteText = "DELETE ";
		modButtons[0].setAttribute("text", createNewText);
		modButtons[1].setAttribute("text", deleteText);
	}

	@Override
	public void handleAction(String command) {
		System.out.println("PlannerTestPanel: ()" + command + "()");
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
		selectedList = lists[displayTypeInt];
		switch (displayTypeInt) {
			case 0:
				listInfo = ListInfo.COMPANY;
				listInfo.setSelectedCompany(manager.getCompany(displayName));
				break;
			case 1:
				listInfo = ListInfo.CUSTOMER;
				listInfo.setSelectedCustomer(manager.getCustomer(displayName));
				break;
			case 2:
				listInfo = ListInfo.PRICING;
				listInfo.setSelectedItem(FlooringItemManager.getPricing(displayName));
				break;
		}
	}

	private void processModButton(int button) {
		switch (button) {
			case 0:
				if (creationPopup == null) {
					creationPopup = new CreationPopup(this);
				}
				creationPopup.display(button);
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

	public ListInfo getListInfo() {
		return listInfo;
	}

	public String getModPlaceholder(int modificationIndex) {
		return listInfo.getLabelInfo()[modificationIndex + 1];
	}

	public int getSelectedListIndex() {
		for (int i = 0; i < ListInfo.values().length; i++) {
			if (listInfo == ListInfo.values()[i]) {
				return i;
			}
		}
		return -1;
	}

}
