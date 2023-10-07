package floorplanning.display.popups;

import floorplanning.display.PlannerTestPanel;

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

}
