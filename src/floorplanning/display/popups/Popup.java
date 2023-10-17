package floorplanning.display.popups;

import floorplanning.display.PlannerTestPanel;
import ui.Panel;
import ui.components.impl.Button;
import ui.components.impl.Label;
import ui.components.impl.UserInput;
import ui.frames.Frame;
import util.structures.lists.List;

public abstract class Popup {

	private Frame frame;
	private Panel popupPanel;
	private PlannerTestPanel plannerTestPanel;

	private Label title;
	private List<UserInput> otherStuff = new List<>();
	private Button create, cancel;

	private int currentY = 10;
	private static final int componentHeight = 30;
	private int panelDefaultWidth = 400;
	private int panelDefaultHeight = 250;
	private int modificationIndex = 0;

	public Popup(PlannerTestPanel panel) {
		this.plannerTestPanel = panel;
	}

	protected Frame getFrame() {
		return frame;
	}

	protected Panel getPopupPanel() {
		return popupPanel;
	}

	protected PlannerTestPanel getPanel() {
		return plannerTestPanel;
	}

	public void setTitle(String titleText) {
		if (title == null) {
			title = new Label(frame, "", 0, currentY, panelDefaultWidth, componentHeight);
			title.setAttribute("alignment", "center");
		}
		title.setAttribute("text", titleText);
	}

	public void addUserInput(String placeholder) {
		UserInput input = new UserInput(frame, "", 10, currentY += componentHeight, panelDefaultWidth - 20,
				componentHeight, placeholder);
		otherStuff.add(input);
	}

	protected int getModIndex() {
		return modificationIndex;
	}

	public void updateUserInput(int i, String placeholder) {
		getInput(i).setPlaceholder(placeholder);
	}

	public UserInput getInput(int index) {
		return otherStuff.get(index);
	}

	public void addFinalButtons() {
		create = new Button(frame, "Confirm", 10, currentY += componentHeight, (panelDefaultWidth / 2) - 20,
				panelDefaultHeight - currentY - 10);
		cancel = new Button(frame, "Cancel", 10, currentY, (panelDefaultWidth / 2) - 20,
				panelDefaultHeight - currentY - 10);
		create.setAttribute("action", "create");
		cancel.setAttribute("action", "cancel");
	}

	public void display(int modIndex) {
		this.modificationIndex = modIndex;
		if (popupPanel != null) {
			popupPanel.update();
			frame.setVisible(true);
			return;
		}
		frame = new Frame("title", 400, 250);
		frame.removeCloseButton();
		frame.setAlwaysOnTop(true);
		popupPanel = new Panel(frame, "popuppanel") {

			@Override
			public void update() {
				if (title != null) {
					title.setAttribute("width", getWidth() - 20);
				}
				for (int i = 0; i < otherStuff.size(); i++) {
					otherStuff.get(i).setAttribute("width", getWidth() - 20);
					otherStuff.get(i).setPlaceholder(plannerTestPanel.getListInfo().getLabelInfo()[i]);
				}
				int buttonY = Integer.valueOf(create.getAttribute("y"));
				create.setAttribute("width", (getWidth() / 2) - 10);
				cancel.setAttribute("width", (getWidth() / 2) - 10);
				cancel.setAttribute("x", (getWidth() / 2));
				create.setAttribute("height", getHeight() - buttonY - 10);
				cancel.setAttribute("height", getHeight() - buttonY - 10);
				updatePanel();
			}

			@Override
			protected void init() {
			}

			@Override
			public void keyTyped(int c) {}

			@Override
			public void preComponentDrawing() {}

			@Override
			public void finishPanelDrawing() {}

			@Override
			public void handleAction(String command) {
				switch (command) {
					case "create":
						confirm();
						break;
					case "cancel":
						frame.dispose();
						break;
				}
			}
		};
		initPanel();
		if (title != null)
		popupPanel.addComponent(title);
		for (int i = 0; i < otherStuff.size(); i++) {
			popupPanel.addComponent(otherStuff.get(i));
		}
		popupPanel.addComponent(create);
		popupPanel.addComponent(cancel);
		frame.setActivePanel(popupPanel);
		frame.setVisible(true);
	}

	protected abstract void initPanel();

	protected abstract void updatePanel();

	protected abstract void confirm();
}
