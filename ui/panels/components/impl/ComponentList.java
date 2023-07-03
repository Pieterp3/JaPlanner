package ui.panels.components.impl;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import ui.Frame;
import ui.panels.components.DrawnComponent;


public class ComponentList extends DrawnComponent {
    
    private List<DrawnComponent> components;

    public ComponentList(Frame frame) {
        super(frame);
        components = new ArrayList<DrawnComponent>();
        getStyle().setHeight(100);
        setResizesHorizontally(true);
        setResizesVertically(false);
        getStyle().addDefaultBorder();
    }

    @Override
    public void draw(Graphics2D g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public void click() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'click'");
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
