package game.tools.editors;

import game.definitions.impl.ItemDefinition;
import game.items.Item;
import game.items.ItemManager;
import ui.Panel;
import ui.components.containers.impl.ComponentList;
import ui.components.impl.Button;
import ui.components.impl.Label;
import ui.components.impl.UserInput;
import ui.frames.Frame;

public class ItemEditor {

	public static void main(String[] args) {
		new ItemEditor();
	}

	public ItemEditor() {
		ItemManager.loadDefinitions();
		Frame frame = new Frame("Game Item Editor", 800, 600);
		frame.setActivePanel(new ItemEditorPanel(frame, "itemeditor"));
		frame.setVisible(true);
	}

	class ItemEditorPanel extends Panel {

		private ComponentList itemList;
		private UserInput nameInput, valueInput, descriptionInput, stackableInput;
		private ItemDefinition currentItem;
		private Label idInput;
		private UserInput itemSearch;

		public ItemEditorPanel(Frame frame, String name) {
			super(frame, name);
		}

		@Override
		public void update() {}

		@Override
		protected void init() {
			itemList = new ComponentList(getFrame(), 10, 10, 200, 480);
			for (int i = 0; i < ItemManager.getDefinitions().size(); i++) {
				Item item = new Item(i);
				Button setItemButton = new Button(getFrame(), item.getName(), 0, 0, 190, 25);
				setItemButton.setAttribute("action", "setitem " + item.getId());
				itemList.addComponent(setItemButton);
			}
			addComponent(itemList);
			itemSearch = new UserInput(getFrame(), "", 10, 500, 200, 20, "Search");
			addComponent(itemSearch);
			Button searchButton = new Button(getFrame(), "Search", 10, 530, 200, 25);
			addComponent(searchButton);
			idInput = new Label(getFrame(), "ID", 220, 10, 200, 20);
			addComponent(idInput);
			nameInput = new UserInput(getFrame(), "", 220, 40, 200, 20, "Name");
			addComponent(nameInput);
			valueInput = new UserInput(getFrame(), "", 220, 70, 200, 20, "Value");
			addComponent(valueInput);
			stackableInput = new UserInput(getFrame(), "", 220, 100, 200, 20, "Stackable");
			addComponent(stackableInput);
			descriptionInput = new UserInput(getFrame(), "", 220, 130, 200, 20, "Description");
			addComponent(descriptionInput);
			Button saveButton = new Button(getFrame(), "Save", 220, 260, 200, 25);
			addComponent(saveButton);
		}

		@Override
		public void handleAction(String command) {
			if (command.startsWith("setitem")) {
				int id = Integer.parseInt(command.split(" ")[1]);
				currentItem = ItemManager.getDefinition(id);
				idInput.setAttribute("text","ID "+currentItem.getId());
				nameInput.setText(currentItem.getName());
				nameInput.updateCursorPosition();
				valueInput.setText(currentItem.getValue() + "");
				valueInput.updateCursorPosition();
				stackableInput.setText(currentItem.isStackable() + "");
				stackableInput.updateCursorPosition();
				descriptionInput.setText(currentItem.getDescription());
				descriptionInput.updateCursorPosition();
			}
			if (command.equalsIgnoreCase("save")) {
				ItemDefinition definition = ItemManager.getDefinition(Integer.parseInt(idInput.getText()));
				if (definition == null) {
					definition = new ItemDefinition(ItemManager.getNextAvailableId(), nameInput.getText(),
							Integer.parseInt(valueInput.getText()), Boolean.parseBoolean(stackableInput.getText()),
							descriptionInput.getText());
					ItemManager.addDefinition(definition);
				} else {
					definition.setName(nameInput.getText());
					definition.setValue(Integer.parseInt(valueInput.getText()));
					definition.setStackable(Boolean.parseBoolean(stackableInput.getText()));
					definition.setDescription(descriptionInput.getText());
				}
				ItemManager.saveDefinitions();
				nameInput.setText("");
				valueInput.setText("");
				stackableInput.setText("");
				descriptionInput.setText("");
				idInput.setAttribute("text", "ID");
				Button setItemButton = new Button(getFrame(), definition.getName(), 0, 0, 190, 25);
				setItemButton.setAttribute("action", "setitem " + definition.getId());
				itemList.addComponent(setItemButton);
			}
			if (command.equalsIgnoreCase("search")) {
				itemList.removeAllComponents();
				for (int i = 0; i < ItemManager.getDefinitions().size(); i++) {
					Item item = new Item(i);
					if (item.getName().toLowerCase().contains(itemSearch.getText().toLowerCase())) {
						Button setItemButton = new Button(getFrame(), item.getName(), 0, 0, 190, 25);
						setItemButton.setAttribute("action", "setitem " + item.getId());
						itemList.addComponent(setItemButton);
					}
				}
			}
		}

		@Override
		public void keyTyped(int c) {}

		@Override
		public void preComponentDrawing() {}

		@Override
		public void finishPanelDrawing() {}

	}

}
