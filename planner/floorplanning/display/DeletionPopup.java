package floorplanning.display;

import ui.Panel;
import ui.components.impl.Button;
import ui.components.impl.Label;
import ui.frames.Frame;

public class DeletionPopup {

	private Frame creationPopup;
	private Panel popupPanel;
	private PlannerTestPanel panel;

	public DeletionPopup(PlannerTestPanel panel) {
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
		creationPopup = new Frame("title", 400, 150);
		creationPopup.removeCloseButton();
		creationPopup.setAlwaysOnTop(true);
		int displayIndex = panel.getSelectedListIndex();
		popupPanel = new Panel(creationPopup, "popuppanel") {

			private Button create;
			private Button cancel;
			private Label creationLabel;

			@Override
			public void update() {
				int displayIndex = panel.getSelectedListIndex();
				create.setAttribute("width", (getWidth() / 2) - 10);
				cancel.setAttribute("width", (getWidth() / 2) - 10);
				cancel.setAttribute("x", (getWidth() / 2));
				int buttonY = Integer.valueOf(create.getAttribute("y"));
				create.setAttribute("height", getHeight() - buttonY - 10);
				cancel.setAttribute("height", getHeight() - buttonY - 10);
				creationLabel.setAttribute("width", getWidth() - 20);
				creationLabel.setAttribute("text", getTitle(displayIndex));
			}

			private String getTitle(int displayIndex) {
				String title = "Confirm Deletion of ";
				return title += panel.getListInfo().getInfoType() + "?";
			}

			@Override
			protected void init() {
				int y = 10;
				int compHeight = 30;
				creationLabel = new Label(creationPopup, getTitle(displayIndex), 0, y, getWidth(), compHeight);
				creationLabel.setAttribute("alignment", "center");
				addComponent(creationLabel);
				create = new Button(creationPopup, "Delete", 10, y += 30, (getWidth() / 2) - 20, getHeight() - y - 10);
				cancel = new Button(creationPopup, "Cancel", 10, y, (getWidth() / 2) - 20, getHeight() - y - 10);
				create.setAttribute("action", "delete");
				cancel.setAttribute("action", "cancel");
				addComponent(create);
				addComponent(cancel);
			}

			@Override
			public void handleAction(String command) {
				switch (command) {
					case "delete":
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
