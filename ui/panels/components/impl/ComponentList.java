package ui.panels.components.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import ui.Frame;
import ui.panels.components.ArtAssistant;
import ui.panels.components.DrawnComponent;
import ui.panels.components.interfaces.Scrollable;
import ui.panels.components.style.Style;
import ui.panels.components.interfaces.ContainerComponent;


public class ComponentList extends DrawnComponent implements Scrollable, ContainerComponent {
    
    private List<DrawnComponent> components;
    private int scrollIndex;

    public ComponentList(Frame frame, int x, int y, int width, int height) {
        super(frame);
        components = new ArrayList<DrawnComponent>();
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
        style.setPadding(2);
        style.setBackgroundColor(Color.darkGray);
        style.setBackgroundHoverColor(Color.darkGray);
        style.setResizesHorizontally(false);
        style.setResizesVertically(true);
    }

    @Override
    public void draw(Graphics2D g) {
        Style style = getStyle();
        if (style.isDisabled()) return;
        ArtAssistant.attemptBackground(g, style, getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        ArtAssistant.attemptBorder(g, style, getX(), getY(), getWidth(), getHeight(), isHovered());
        for (int i = scrollIndex; i < components.size(); i++) { components.get(i).draw(g); }
    }

    public void scroll(int amount) {
        scrollIndex += amount;
        if (scrollIndex < 0) scrollIndex = 0;
        if (scrollIndex > components.size()-1) scrollIndex = components.size()-1;
        repositionComponents();
    }

    @Override
    public void addComponent(DrawnComponent component) {
        components.add(component);
        repositionComponents();
    }

    @Override
    public void removeComponent(DrawnComponent component) {
        components.remove(component);
        repositionComponents();
    }

    @Override
    public List<DrawnComponent> getComponents() {
        return components;
    }

    //Prefers Vertical scrolling but defaults to horizontal
    //TODO add a display for scrollbar and arrow buttons
    //TODO add a way to change the scroll amount
    //Sets max width or height depending on scroll direction
        //Max is size of scroller - padding
    //Stops components from being drawn over the edge of the list
    public void repositionComponents() {
        int padding = getStyle().getPadding();
        int border = getStyle().getBorderWidth();
        int wallOffset = padding + border;
        int compWidth = getWidth() - (wallOffset * 2);
        int compHeight = getHeight() - (wallOffset * 2);
        int topY = padding + getY();
        int leftX = padding + getX();
        int endValue = getStyle().getResizesVertically() ? getHeight()+getY() : getWidth()+getX();
        for(int i = scrollIndex;i<components.size();i++) {
            DrawnComponent comp = components.get(i);
            Style style = comp.getStyle();
            if (getStyle().getResizesVertically()) {
                style.setHeight(Math.min(comp.getHeight(), compHeight));
                int bottomY = topY + comp.getHeight();
                if (bottomY > endValue) {
                    comp.getStyle().setDisabled(true);
                    continue;
                } else {
                    comp.getStyle().setDisabled(false);
                }
                style.setY(topY);
                style.setX(leftX);
                style.setWidth(compWidth);
                topY += comp.getHeight() + padding;
            } else {
                style.setWidth(Math.min(comp.getWidth(), compWidth));
                int rightX = leftX + comp.getWidth();
                if (rightX > endValue) {
                    comp.getStyle().setDisabled(true);
                    continue;
                } else {
                    comp.getStyle().setDisabled(false);
                }
                style.setX(leftX);
                style.setHeight(compHeight);
                style.setY(topY);
                leftX += comp.getWidth() + padding;
            }
        }
    }

    @Override
    public void click() {
        for (DrawnComponent comp : components) {
            if (comp.isHovered()) {
                comp.click();
                break;
            }
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        for (DrawnComponent comp : components) {
            comp.checkHover(x, y);
        }
    }

}
