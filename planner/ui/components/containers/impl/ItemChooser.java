package ui.components.containers.impl;

import ui.components.DrawnComponent;
import ui.components.containers.ContainerComponent;
import ui.components.style.Style;
import ui.frames.Frame;
import ui.graphics.Color;
import ui.graphics.Graphics;
import util.structures.List;
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
				put("color", Color.white.toAttributeString());
                put("backgroundColor", Color.gray.toAttributeString());
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
		list = new ComponentList(frame, x, y + height, width, (int) (height * 4));
		list.setAttribute("color", Color.white.toAttributeString());
		list.setAttribute("backgroundColor", Color.gray.toAttributeString());
		list.setAttribute("backgroundHoverColor", Color.gray.toAttributeString());
		list.setAttribute("backgroundPressColor", Color.gray.toAttributeString());
		list.setAttribute("disabled", true);
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
		if (list.getComponentCount() > 2) {
			List<DrawnComponent> components = list.getComponents();
			if (selectedIndex >= 0 && selectedIndex < components.size()-2) {
				DrawnComponent component = components.get(selectedIndex+2);
				g.drawText(getX(), getY(), getWidth(), getHeight(), component.getAttribute("text"));
			}
		}
		if (!list.getStyle().getBooleanAttribute("disabled")) {
			list.updateGraphicsStyle(g);
		}
	}

	@Override
	public void addComponent(DrawnComponent component) {
		component.setAttribute("alignment", getAttribute("alignment"));
		component.setAttribute("width", getWidth());
		component.setAttribute("height", getHeight());
		component.setAttribute("color", Color.white.toAttributeString());
		component.setAttribute("backgroundColor", Color.gray.toAttributeString());
		component.setAttribute("backgroundHoverColor", Color.darkGray.toAttributeString());
		component.setAttribute("padding", 4);
		list.addComponent(component);
	}

	@Override
	public void removeComponent(DrawnComponent component) {
		list.removeComponent(component);
	}

	@Override
	public void click(int x, int y) {
		open = !open;
		list.setAttribute("disabled", open);
	}

}