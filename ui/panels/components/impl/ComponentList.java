package ui.panels.components.impl;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import ui.Frame;
import ui.panels.components.ArtAssistant;
import ui.panels.components.DrawnComponent;
import ui.panels.components.interfaces.Scrollable;
import ui.panels.components.style.Style;


public class ComponentList extends DrawnComponent implements Scrollable {
    
    private List<DrawnComponent> components;
    private int scrollIndex;

    public ComponentList(Frame frame, int x, int y, int width, int height) {
        super(frame);
        components = new ArrayList<DrawnComponent>();
        setResizesHorizontally(false);
        setResizesVertically(true);
        scrollIndex = 0;
        initStyle(getStyle(), x, y, width, height);
    }
    
    public ComponentList(Frame frame) {
        this(frame, 0, 0, 200, 100);
    }

    private void initStyle(Style style, int x, int y, int width, int height) {
        style.addDefaultBorder();
        style.setX(x);
        style.setY(y);
        style.setWidth(width);
        style.setHeight(height);
        style.setPadding(5);
    }

    @Override
    public void draw(Graphics2D g) {
        Style style = getStyle();
        if (style.isDisabled()) return;
        ArtAssistant.attemptBackground(g, style, getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        ArtAssistant.attemptBorder(g, style, getX(), getY(), getWidth(), getHeight(), isHovered());
        for (int i = scrollIndex; i < components.size(); i++) { components.get(i).draw(g); }
    }

    //TODO scroll will move up or down one list item at a time unless changed
    public void scroll(int amount) {
        scrollIndex += amount;
        if (scrollIndex < 0) scrollIndex = 0;
        if (scrollIndex > components.size()-1) scrollIndex = components.size()-1;
        repositionComponents();
    }

    public void addComponent(DrawnComponent component) {
        components.add(component);
        repositionComponents();
    }

    public void removeComponent(DrawnComponent component) {
        components.remove(component);
        repositionComponents();
    }

    //Prefers Vertical scrolling but defaults to horizontal
    //TODO add a display for scrollbar and arrow buttons
    //TODO add a way to change the scroll amount
    //Sets max width or height depending on scroll direction
        //Max is size of scroller - padding
    public void repositionComponents() {
        int padding = getStyle().getPadding();
        int compWidth = getWidth() - (padding * 2);
        int compHeight = getHeight() - (padding * 2);
        int topY = padding + getY();
        int leftX = padding + getX();
        for(int i = scrollIndex;i<components.size();i++) {
            DrawnComponent comp = components.get(i);
            Style style = comp.getStyle();
            if (getResizesVertically()) {
                style.setY(topY);
                style.setX(leftX);
                style.setWidth(compWidth);
                style.setHeight(Math.min(comp.getHeight(), compHeight));
                topY += comp.getHeight() + padding;
            } else {
                style.setX(leftX);
                style.setHeight(compHeight);
                style.setWidth(Math.min(comp.getWidth(), compWidth));
                style.setY(topY);
                leftX += comp.getWidth() + padding;
            }
        }
    }

    @Override
    public void click() {
        System.out.println("Clicked ComponentList");
    }

    public boolean getResizesVertically() {
        return getStyle().getBooleanAttribute("resizesVertically");
    }

    public void setResizesVertically(boolean resizesVertically) {
        getStyle().setAttribute("resizesVertically", resizesVertically + "");
    }

    public boolean getResizesHorizontally() {
        return getStyle().getBooleanAttribute("resizesHorizontally");
    }

    public void setResizesHorizontally(boolean resizesHorizontally) {
        getStyle().setAttribute("resizesHorizontally", resizesHorizontally + "");
    }

}
