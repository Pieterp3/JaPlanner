package ui.components.impl;

import ui.components.DrawnComponent;
import ui.components.style.Style;
import ui.frames.Frame;
import ui.graphics.Color;
import ui.graphics.Graphics;

public class ColorPicker extends DrawnComponent {

	private int selectedLowerIndex;
	private int colorColumns = 32;
	private int selectedUpperIndex;

	private int pickerX, higherPickerY, totalWidth, totalHeight, lowerPickerY, lowerPickerWidth;
	private int lowerPickerHeight, higherPickerHeight, columnWidth;
	private Color selectedColor;

	public ColorPicker(Frame frame, int x, int y, int width, int height) {
		super(frame);
		style.setAttribute("x", x);
		style.setAttribute("y", y);
		style.setAttribute("width", width);
		style.setAttribute("height", height);
		style.setColorAttribute("backgroundColor", Color.white);
		style.addDefaultBorder();
	}

	@Override
	public void draw(Graphics g, Style style) {
		g.drawBackground(getX(), getY(), getWidth(), getHeight(), false, false);
		g.attemptBorder(getX(), getY(), getWidth(), getHeight(), false);

		pickerX = getX() + style.getIntAttribute("borderWidth") - 1;
		higherPickerY = getY() + style.getIntAttribute("borderWidth") - 1;
		totalWidth = getWidth() - 2 * style.getIntAttribute("borderWidth") + 2;
		totalHeight = getHeight() - 2 * style.getIntAttribute("borderWidth") + 2;
		higherPickerHeight = (int) (totalHeight * .8);

		lowerPickerY = higherPickerY + higherPickerHeight;
		lowerPickerHeight = totalHeight - higherPickerHeight;
		lowerPickerWidth = (int) (totalWidth * .85);
		columnWidth = lowerPickerWidth / colorColumns;

		Color lowerColor = Color.getGradientColor(selectedLowerIndex, colorColumns);
		for (int i = 0; i < colorColumns; i++) {
			int colorX = columnWidth * i;
			g.setColor(Color.getGradientColor(i, colorColumns));
			g.fillRect(pickerX + colorX, lowerPickerY, columnWidth, lowerPickerHeight);
		}
		// Draw a rectangle of contrasting color around the selected color
		g.setColor(Color.black);
		g.drawRect(pickerX + selectedLowerIndex * columnWidth, lowerPickerY,
				columnWidth, lowerPickerHeight);

		// Draw the higher picker
		for (int i = 0; i < colorColumns; i++) {
			int colorX = columnWidth * i;
			g.setColor(Color.getGradientColor(lowerColor, i, colorColumns));
			g.fillRect(pickerX + colorX, higherPickerY, columnWidth, higherPickerHeight);
		}
		// Draw a rectangle of contrasting color around the selected color
		g.setColor(Color.black);
		g.drawRect(pickerX + selectedUpperIndex * columnWidth, higherPickerY,
				columnWidth, higherPickerHeight);

		selectedColor = Color.getGradientColor(lowerColor, selectedUpperIndex, colorColumns);
		g.setColor(selectedColor);
		g.fillCircle(pickerX + lowerPickerWidth - columnWidth - 2, higherPickerY + 2,
				totalWidth - lowerPickerWidth + 12, totalHeight - 4);
	}

	public Color getSelectedColor() {
		return selectedColor;
	}

	@Override
	public void click(int x, int y) {
		x -= getX();
		if (x > lowerPickerWidth - columnWidth) {
			return;
		}
		if (y > lowerPickerY) {
			selectedLowerIndex = x / columnWidth;
		} else {
			selectedUpperIndex = x / columnWidth;
		}
	}

	@Override
	public void setHoveredCursor(int x, int y) {
		getFrame().setCursor(Frame.HAND_CURSOR);
	}

}
