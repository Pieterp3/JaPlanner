package spritemaker;

import game.sprites.Sprite;
import game.sprites.SpriteManager;
import ui.Panel;
import ui.components.containers.impl.ComponentList;
import ui.components.impl.Button;
import ui.components.impl.ColorPicker;
import ui.components.impl.Label;
import ui.components.impl.SpriteDisplay;
import ui.graphics.Color;
import ui.components.impl.UserInput;
import ui.frames.Frame;
import util.structures.Map;
import util.structures.lists.List;

public class SpriteMaker {

	public static void main(String[] args) {
		new SpriteMaker();
	}

	private Frame frame;

	public SpriteMaker() {
		SpriteManager.init();
		frame = new Frame("Sprite Maker", 1000, 1000);
		frame.setActivePanel(new SpriteMakerPanel(frame, "spritemaker"));
		frame.setVisible(true);
	}

	class SpriteMakerPanel extends Panel {

		public SpriteMakerPanel(Frame frame, String name) {
			super(frame, name);
		}

		private SpriteDisplay spriteDisplay;
		private ComponentList spriteList;
		private UserInput spriteStageInput;
		private UserInput spriteWidthInput;// Lock at 32
		private UserInput spriteHeightInput;// lock at 32
		private UserInput selectedCellColorInput;
		private Label selectedCellX;
		private Label selectedCellY;
		private Button addStageButton;
		private Button removeStageButton;
		private Button saveButton;
		private Button loadButton;
		private Button newButton;
		private Button deleteButton;
		private Button animateButton;
		private Label savedIndicator, displayedSpriteName;
		private boolean isSaved = true;
		private ColorPicker colorPicker;

		private Label currentStageLabel;
		private Button nextStageButton, previousStageButton;

		@Override
		public void update() {
			if (spriteDisplay.getSprite() != null) {
				displayedSpriteName.setAttribute("text", spriteDisplay.getSprite().getName());
				// spriteStageInput.setText(spriteDisplay.getSprite().getStage() + "");
				spriteWidthInput.setText(spriteDisplay.getSprite().getDrawnWidth() + "");
				spriteHeightInput.setText(spriteDisplay.getSprite().getDrawnHeight() + "");
				selectedCellColorInput.setText(spriteDisplay.getSelectedColor());
				selectedCellX.setAttribute("text", "X " + spriteDisplay.getSelectedPoint().getIntX());
				selectedCellY.setAttribute("text", "Y " + spriteDisplay.getSelectedPoint().getIntY());

				currentStageLabel.setAttribute("text",
						"Stage " + (spriteDisplay.getSprite().getDisplayStage()) + " of "
								+ spriteDisplay.getSprite().getStageCount());
			}
			setSavedDisplay(isSaved);
		}

		@Override
		protected void init() {
			int x = 10;
			int y = 10;
			int width = 256;

			// Add the sprite list column
			Label spriteListLabel = new Label(frame, "Sprite List", x, y, width, 20);
			spriteListLabel.setAttribute("alignment", "center");
			spriteList = new ComponentList(frame, x, y += 30, width, 512);
			rebuildSpriteList();
			spriteStageInput = new UserInput(frame, "", x, y += 522, width, 20, "Stage");
			spriteWidthInput = new UserInput(frame, "", x, y += 30, width, 20, "Width");
			spriteHeightInput = new UserInput(frame, "", x, y += 30, width, 20, "Height");
			selectedCellColorInput = new UserInput(frame, "", x, y += 30, width, 20, "Color");
			selectedCellX = new Label(frame, "X", x, y += 30, width, 20);
			selectedCellY = new Label(frame, "Y", x, y += 30, width, 20);
			addStageButton = new Button(frame, "Add Stage", x, y += 30, width, 25);
			removeStageButton = new Button(frame, "Remove Stage", x, y += 30, width, 25);
			saveButton = new Button(frame, "Save", x, y += 30, width, 25);
			loadButton = new Button(frame, "Load", x, y += 30, width, 25);
			newButton = new Button(frame, "New", x, y += 30, width, 25);
			deleteButton = new Button(frame, "Delete", x, y += 30, width, 25);

			// Add the sprite display column
			displayedSpriteName = new Label(frame, "Name", x += (width + 10), y = 10, width * 2, 20);
			displayedSpriteName.setAttribute("alignment", "center");
			width *= 2;// This is below delayedSpriteName because the entire column is shifted there
			spriteDisplay = new SpriteDisplay(frame, 32, x, y += 30, width, 512);

			currentStageLabel = new Label(frame, "Stage", x, y += 522, width, 25);
			currentStageLabel.setAttribute("fontSize", 3.5);
			currentStageLabel.setAttribute("alignment", "center");

			int halfColWidth = (int) (width * .45);
			previousStageButton = new Button(frame, "Previous Stage", x, y += 30, halfColWidth, 30);
			nextStageButton = new Button(frame, "Next Stage", x + width - halfColWidth, y, halfColWidth, 30);
			animateButton = new Button(frame, "Animate", x, y += 40, width, 25);

			// Add the save indicator
			savedIndicator = new Label(frame, "Saved", x + width - 90, 10, 90, 20);
			savedIndicator.setAttribute("opaque", true);
			savedIndicator.setAttribute("alignment", "center");

			// Add a color picker for modification
			colorPicker = new ColorPicker(frame, x, y += 40, width, 200);
			Button setPixelColor = new Button(frame, "Set Pixel Color", x, y += 210, width, 25);
			setPixelColor.setAttribute("action", "setpixelcolor");

			// Add everything to the panel
			addcomponents(spriteList, displayedSpriteName, spriteStageInput, spriteWidthInput, spriteHeightInput,
					selectedCellColorInput, selectedCellX, selectedCellY, addStageButton, removeStageButton,
					saveButton, loadButton, newButton, deleteButton, spriteDisplay, savedIndicator, spriteListLabel,
					previousStageButton, currentStageLabel, nextStageButton, colorPicker, setPixelColor, animateButton);

		}

		private void rebuildSpriteList() {
			for (int i = 0; i < SpriteManager.getSprites().size(); i++) {
				Button setSpriteButton = new Button(frame, SpriteManager.getSprites().get(i).getName(), 0, 0, 190, 25);
				setSpriteButton.setAttribute("action", "setsprite=" + SpriteManager.getSprites().get(i).getName());
				spriteList.addComponent(setSpriteButton);
			}
		}

		private void setSavedDisplay(boolean saved) {
			if (saved) {
				savedIndicator.setAttribute("text", "Saved");
				savedIndicator.getStyle().setColorAttribute("backgroundColor", Color.green);
				savedIndicator.getStyle().setColorAttribute("backgroundHoverColor", Color.green);
				savedIndicator.getStyle().setColorAttribute("backgroundPressColor", Color.green);
			} else {
				savedIndicator.setAttribute("text", "Unsaved");
				savedIndicator.getStyle().setColorAttribute("backgroundColor", Color.red);
				savedIndicator.getStyle().setColorAttribute("backgroundHoverColor", Color.red);
				savedIndicator.getStyle().setColorAttribute("backgroundPressColor", Color.red);
			}
		}

		@Override
		public void handleAction(String command) {
			switch (command) {
				case "Next Stage":
					spriteDisplay.getSprite().advance();
					break;
				case "Previous Stage":
					spriteDisplay.getSprite().rewind();
					// spriteDisplay.getSprite().setStage(spriteDisplay.getSprite().getStage() - 1);
					break;
				case "Add Stage":
					Sprite sprite = spriteDisplay.getSprite();
					if (sprite == null) {
						return;
					}
					sprite.createBlankStage(32);
					isSaved = false;
					break;
				case "Remove Stage":
					sprite = spriteDisplay.getSprite();
					if (sprite == null) {
						return;
					}
					sprite.removeCurrentStage();
					isSaved = false;
					break;
				case "Animate":
					spriteDisplay.getSprite().play();
					animateButton.setAttribute("text", "Stop");
					animateButton.setAttribute("action", "Stop");
					break;
				case "Stop":
					spriteDisplay.getSprite().stop();
					animateButton.setAttribute("text", "Animate");
					animateButton.setAttribute("action", "Animate");
					break;
				case "Save":
					SpriteManager.saveSprites();
					isSaved = true;
					break;
				case "Load":
					isSaved = false;
					SpriteManager.reloadSprites();
					spriteList.removeAllComponents();
					rebuildSpriteList();
					isSaved = true;
					break;
				case "New":
					sprite = new Sprite(displayedSpriteName.getText());
					sprite.createBlankStage(32);
					SpriteManager.getSprites().add(sprite);
					spriteDisplay.setSprite(sprite);
					Button setSpriteButton = new Button(frame, sprite.getName(), 0, 0, 190, 25);
					setSpriteButton.setAttribute("action", "setsprite=" + sprite.getName());
					spriteList.addComponent(setSpriteButton);
					isSaved = false;
					break;
				case "Delete":
					// TODO: add popup before allowing this
					// sprite = spriteDisplay.getSprite();
					// if (sprite == null) {
					// return;
					// }
					// SpriteManager.getSprites().remove(sprite);
					// spriteList.removeComponent(spriteList.getComponentByText(sprite.getName()));
					// spriteDisplay.setSprite(null);
					// isSaved = false;
					break;
				case "setpixelcolor":
					if (spriteDisplay.getSprite() == null) {
						return;
					}
					spriteDisplay.getSprite().setColor(spriteDisplay.getSelectedPoint().getIntX(),
							spriteDisplay.getSelectedPoint().getIntY(), colorPicker.getSelectedColor());
					isSaved = false;
					break;
			}
			if (command.startsWith("setsprite")) {
				String spriteName = command.split("=")[1];
				Sprite sprite = SpriteManager.getSprite(spriteName);
				spriteDisplay.setSprite(sprite);
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
