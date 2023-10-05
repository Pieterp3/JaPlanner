package floorplanning.display;

import ui.Panel;
import ui.components.impl.Button;
import ui.components.impl.Label;
import ui.components.impl.UserInput;
import ui.frames.Frame;

public class CreationPopup {

	private Frame creationPopup;
	private Panel popupPanel;
	private PlannerTestPanel panel;

	public CreationPopup(PlannerTestPanel panel) {
		this.panel = panel;
	}

	public void display() {
		// Display a popup frame that lets the user create a new customer, company, or
		// item
		if (creationPopup != null) {
			popupPanel.update();
			creationPopup.setVisible(true);
			return;
		}
		creationPopup = new Frame("title", 400, 250);
		creationPopup.removeCloseButton();
		creationPopup.setAlwaysOnTop(true);
		int displayIndex = panel.getSelectedListIndex();
		popupPanel = new Panel(creationPopup, "popuppanel") {

			private UserInput name;
			private UserInput[] otherStuff;
			private Button create;
			private Button cancel;
			private Label creationLabel;

			@Override
			public void update() {
				int displayIndex = panel.getSelectedListIndex();
				name.setAttribute("width", getWidth() - 20);
				for (UserInput input : otherStuff) {
					input.setAttribute("width", getWidth() - 20);
				}
				create.setAttribute("width", (getWidth() / 2) - 10);
				cancel.setAttribute("width", (getWidth() / 2) - 10);
				cancel.setAttribute("x", (getWidth() / 2));
				int buttonY = Integer.valueOf(create.getAttribute("y"));
				create.setAttribute("height", getHeight() - buttonY - 10);
				cancel.setAttribute("height", getHeight() - buttonY - 10);
				creationLabel.setAttribute("width", getWidth() - 20);
				creationLabel.setAttribute("text", getTitle(displayIndex));
				String[] placeholders = displayIndex == PlannerTestPanel.PRICELISTINDEX
						? new String[] { "Install Price", "Material Price", "Removal Price", "Move and Replace Price" }
						: new String[] { "Address", "Phone Number", "Email", "Notes" };
				for (int i = 0; i < otherStuff.length; i++) {
					otherStuff[i].setPlaceholder(placeholders[i]);
				}
			}

			private String getTitle(int displayIndex) {
				String title = "Create New ";
				title += displayIndex == PlannerTestPanel.COMPLISTINDEX ? "Company"
						: displayIndex == PlannerTestPanel.CUSTLISTINDEX ? "Customer" : "Item";
				return title;
			}

			@Override
			protected void init() {
				int y = 10;
				int compHeight = 30;
				creationLabel = new Label(creationPopup, getTitle(displayIndex), 0, y, getWidth(), compHeight);
				creationLabel.setAttribute("alignment", "center");
				addComponent(creationLabel);
				name = new UserInput(creationPopup, "", 10, y += compHeight, getWidth() - 20, compHeight, "Name");
				otherStuff = new UserInput[4];
				String[] placeholders = displayIndex == PlannerTestPanel.PRICELISTINDEX
						? new String[] { "Install Price", "Material Price", "Removal Price", "Move and Replace Price" }
						: new String[] { "Address", "Phone Number", "Email", "Notes" };
				for (int i = 0; i < otherStuff.length; i++) {
					otherStuff[i] = new UserInput(creationPopup, "", 10, y += compHeight, getWidth() - 20,
							compHeight, placeholders[i]);
				}
				create = new Button(creationPopup, "Create", 10, y, (getWidth() / 2) - 20, getHeight() - y - 10);
				cancel = new Button(creationPopup, "Cancel", 10, y, (getWidth() / 2) - 20, getHeight() - y - 10);
				create.setAttribute("action", "create");
				cancel.setAttribute("action", "cancel");
				addComponent(name);
				for (UserInput input : otherStuff) {
					addComponent(input);
				}
				addComponent(create);
				addComponent(cancel);
			}

			@Override
			public void handleAction(String command) {
				switch (command) {
					case "create":
						// Get info from panels, create nessessary objects, and add them to the manager
						// Then close the frame, and save to file
						break;
					case "cancel":
						creationPopup.dispose();
						break;
				}
			}

			@Override
			public void keyTyped(int c) {}

			@Override
			public void preComponentDrawing() {}

			@Override
			public void finishPanelDrawing() {}
		};
		creationPopup.setActivePanel(popupPanel);
		creationPopup.setVisible(true);
	}

}
