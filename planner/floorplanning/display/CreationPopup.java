package floorplanning.display;

public class CreationPopup extends Popup {

	public CreationPopup(PlannerTestPanel panel) {
		super(panel);
	}

	@Override
	protected void initPanel() {
		setTitle("Create New "+getPanel().getListInfo().getInfoType());
		for (int i = 0; i < getPanel().getListInfo().getLabelInfo().length; i++) {
			addUserInput(getPanel().getListInfo().getLabelInfo()[i]);
		}
		addFinalButtons();
	}

	@Override
	protected void updatePanel() {
		setTitle("Create New "+getPanel().getListInfo().getInfoType());
		for (int i = 0; i < getPanel().getListInfo().getLabelInfo().length; i++) {
			updateUserInput(i, getPanel().getListInfo().getLabelInfo()[i]);
		}
	}

	@Override
	protected void confirm() {
		System.out.println("Confirm creation");
	}

}
