package ui.components.containers.impl;

import ui.Frame;
import ui.components.DrawnComponent;
import ui.components.containers.ContainerComponent;
import ui.components.style.Style;
import ui.graphics.Color;
import ui.graphics.Graphics;
import util.structures.Map;

public class ItemChooser extends ContainerComponent {

	private boolean open;
	private ComponentList list;
	private int selectedIndex;

	public ItemChooser(Frame frame, int x, int y, int width, int height) {
		super(frame);
		style.addDefaultBorder();
        style.setAttributes(new Map<String, Object>() {
            {
                put("x", x);
                put("y", y);
                put("width", width);
                put("height", height);
                put("padding", 2);
                put("backgroundColor", Color.darkGray.toAttributeString());
                put("backgroundHoverColor", Color.darkGray.toAttributeString());
                put("backgroundPressColor", Color.darkGray.toAttributeString());
                put("resizesHorizontally", false);
                put("resizesVertically", true);
                put("scrollMultiplier", 1);
                put("scrollbarColor", Color.gray.toAttributeString());
                put("scrollerBackgroundColor", Color.lightGray.toAttributeString());
                put("scrollbarSize", 15);
            }
        });
		list = new ComponentList(frame, x, y+height, width, 250);
		super.addComponent(list);
	}

	@Override
	public void repositionComponents() {
		if (list != null) {
			list.repositionComponents();
		}
	}

	@Override
	public void draw(Graphics g, Style style) {
        g.drawBackground(getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        g.attemptBorder(getX(), getY(), getWidth(), getHeight(), isHovered());
		g.setColor(style.getColorAttribute("color"));
		if (list.getComponentCount() > 0) {
			g.drawText(getX(), getY(), getWidth(), getHeight(), list.getComponents().get(selectedIndex).toString());
		}
		if (open) {
			list.draw(g, style);
		}
	}


	@Override
	public void addComponent(DrawnComponent component) {
		list.addComponent(component);
	}

	@Override
	public void removeComponent(DrawnComponent component) {
		list.removeComponent(component);
	}
}