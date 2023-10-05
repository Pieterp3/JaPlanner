package floorplanning.display;

import ui.Panel;
import ui.components.impl.Button;
import ui.components.impl.Label;
import ui.components.impl.UserInput;
import ui.frames.Frame;

public class ModifyPopup extends Popup {

	public ModifyPopup(PlannerTestPanel panel) {
		super(panel);
	}

	@Override
	protected void initPanel() {
		setTitle("Modify "+getPanel().getListInfo().getInfoType());
			addUserInput("Placeholder");
		addFinalButtons();
	}

	@Override
	protected void updatePanel() {
		setTitle("Modify "+getPanel().getListInfo().getInfoType());
		getInput(0).setPlaceholder(getPanel().getModPlaceholder(getModIndex()-2));
	}

	@Override
	protected void confirm() {
		System.out.println("Confirm modification");
	}

	// public void display(int modIndex) {
	// 	this.modificationIndex = modIndex;
	// 	// Display a popup frame that lets the user create a new customer, company, or
	// 	// item
	// 	if (modificationPopup != null) {
	// 		popupPanel.update();
	// 		modificationPopup.setVisible(true);
	// 		return;
	// 	}
	// 	modificationPopup = new Frame("title", 400, 180);
	// 	modificationPopup.removeCloseButton();
	// 	modificationPopup.setAlwaysOnTop(true);
	// 	int displayIndex = panel.getSelectedListIndex();
	// 	popupPanel = new Panel(modificationPopup, "popuppanel") {

	// 		private UserInput modificationInput;
	// 		private Button create;
	// 		private Button cancel;
	// 		private Label modLabel;

	// 		@Override
	// 		public void update() {
	// 			int displayIndex = panel.getSelectedListIndex();
	// 			modificationInput.setAttribute("width", getWidth() - 20);
	// 			modificationInput.setPlaceholder(panel.getModPlaceholder(modificationIndex));
	// 			create.setAttribute("width", (getWidth() / 2) - 10);
	// 			cancel.setAttribute("width", (getWidth() / 2) - 10);
	// 			cancel.setAttribute("x", (getWidth() / 2));
	// 			int buttonY = Integer.valueOf(create.getAttribute("y"));
	// 			create.setAttribute("height", getHeight() - buttonY - 10);
	// 			cancel.setAttribute("height", getHeight() - buttonY - 10);
	// 			modLabel.setAttribute("width", getWidth() - 20);
	// 			modLabel.setAttribute("text", getTitle(displayIndex));
	// 		}

	// 		private String getPlaceholder() {
	// 			return panel.getListInfo().getLabelInfo()[modificationIndex-1];
	// 		}

	// 		private String getTitle(int displayIndex) {
	// 			String title = "Modify ";
	// 			title += panel.getListInfo().getInfoType();
	// 			title += " " + getPlaceholder();
	// 			return title;
	// 		}

	// 		@Override
	// 		protected void init() {
	// 			int y = 10;
	// 			int compHeight = 30;
	// 			modLabel = new Label(modificationPopup, getTitle(displayIndex), 0, y, getWidth(), compHeight);
	// 			modLabel.setAttribute("alignment", "center");
	// 			addComponent(modLabel);
	// 			modificationInput = new UserInput(modificationPopup, "", 10, y += compHeight, getWidth() - 20,
	// 					compHeight, "");
	// 			create = new Button(modificationPopup, "Submit Modification", 10, y += compHeight, (getWidth() / 2) - 20,
	// 					getHeight() - y - 10);
	// 			cancel = new Button(modificationPopup, "Cancel", 10, y, (getWidth() / 2) - 20, getHeight() - y - 10);
	// 			create.setAttribute("action", "modify");
	// 			cancel.setAttribute("action", "cancel");
	// 			addComponent(modificationInput);
	// 			addComponent(create);
	// 			addComponent(cancel);
	// 		}

	// 		@Override
	// 		public void handleAction(String command) {
	// 			switch (command) {
	// 				case "modify":
	// 					// Get info from panels, create nessessary objects, and add them to the manager
	// 					// Then close the frame, and save to file
	// 					break;
	// 				case "cancel":
	// 					modificationPopup.dispose();
	// 					break;
	// 			}
	// 		}

	// 		@Override
	// 		public void keyTyped(int c) {}

	// 		@Override
	// 		public void preComponentDrawing() {}

	// 		@Override
	// 		public void finishPanelDrawing() {}
	// 	};
	// 	modificationPopup.setActivePanel(popupPanel);
	// 	modificationPopup.setVisible(true);
	// }

}
