package ui.components.impl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ui.Frame;
import ui.components.ArtAssistant;
import ui.components.DrawnComponent;
import ui.components.interfaces.ContainerComponent;
import ui.components.interfaces.Scrollable;
import ui.components.style.Style;


public class ComponentList extends DrawnComponent implements Scrollable, ContainerComponent {
    
    private static final Map<String, String> ARROW_MAP = new HashMap<String, String>() {{
        put("up", "↑");
        put("down", "↓");
        put("left", "←");
        put("right", "→");
    }};

    private static String getArrow(String direction) {
        return ARROW_MAP.get(direction);
    }

    private List<DrawnComponent> components;
    private int scrollIndex;
    private Button scrollButton1, scrollButton2;

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
        style.setResizesHorizontally(true);
        style.setResizesVertically(false);
        style.setScrollMultiplier(1);
        
        int sbSize = 15;
        style.setScrollbarColor(Color.gray);
        style.setScrollerBackgroundColor(Color.lightGray);
        style.setScrollbarSize(sbSize);

        scrollButton1 = new Button(getFrame(), getArrow("up")) {
            @Override
            public void click(int x, int y) {
                scroll(-1);
            }
        };
        scrollButton2 = new Button(getFrame(), getArrow("down")){
            @Override
            public void click(int x, int y) {
                scroll(1);
            }
        };
    }

    @Override
    public void draw(Graphics2D g) {
        Style style = getStyle();
        if (style.isDisabled()) return;
        ArtAssistant.attemptBackground(g, style, getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        ArtAssistant.attemptBorder(g, style, getX(), getY(), getWidth(), getHeight(), isHovered());
        for (int i = scrollIndex; i < components.size(); i++) { components.get(i).draw(g); }
        if (components.size() > 1) {
            scrollButton1.draw(g);
            scrollButton2.draw(g);
        }
        ArtAssistant.drawScrollbar(g, style, scrollButton1.getStyle(), getX(), getY(), getWidth(), getHeight(), scrollIndex, components.size());
    }

    public void scroll(int amount) {
        scrollIndex += (amount * getStyle().getScrollMultiplier());
        if (scrollIndex < 0) scrollIndex = 0;
        if (scrollIndex > components.size() - 1) scrollIndex = components.size() - 1;
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
    //Stops components from being drawn over the edge of the list
    public void repositionComponents() {
        int padding = getStyle().getPadding();
        int border = getStyle().getBorderWidth();
        int scrollbarSize = getStyle().getScrollbarSize();
        int wallOffset = padding + border;
        int compWidth = getWidth() - (wallOffset * 2) - scrollbarSize - padding;
        int compHeight = getHeight() - (wallOffset * 2) - scrollbarSize - padding;
        int topY = wallOffset + getY();
        int leftX = wallOffset + getX();
        int endValue = getStyle().getResizesVertically() ? getHeight() + getY() : getWidth() + getX();

        int scrollbarX, scrollbarY;
        if (getStyle().getResizesVertically()) {
            scrollbarX = getX() + getWidth() - scrollbarSize - border;
            scrollbarY = getY() + border;
            int scrollbarY2 = getY() + getHeight() - scrollbarSize - border;
            scrollButton1.getStyle().setBounds(scrollbarX, scrollbarY, scrollbarSize, scrollbarSize);
            scrollButton2.getStyle().setBounds(scrollbarX, scrollbarY2, scrollbarSize, scrollbarSize);
            scrollButton1.getStyle().setText(getArrow("up"));
            scrollButton2.getStyle().setText(getArrow("down"));
        } else {
            scrollbarX = getX() + border;
            scrollbarY = getY() + getHeight() - scrollbarSize - border;
            int scrollbarX2 = getX() + getWidth() - scrollbarSize - border;
            scrollButton1.getStyle().setBounds(scrollbarX, scrollbarY, scrollbarSize, scrollbarSize);
            scrollButton2.getStyle().setBounds(scrollbarX2, scrollbarY, scrollbarSize, scrollbarSize);
            scrollButton1.getStyle().setText(getArrow("left"));
            scrollButton2.getStyle().setText(getArrow("right"));
        }
        for(int i = scrollIndex;i<components.size();i++) {
            DrawnComponent comp = components.get(i);
            Style style = comp.getStyle();
            if (getStyle().getResizesVertically()) {
                style.setHeight(Math.min(comp.getHeight(), compHeight));
                int bottomY = topY + comp.getHeight() - border;
                if (bottomY > endValue) {
                    comp.getStyle().setDisabled(true);
                    continue;
                } else {
                    comp.getStyle().setDisabled(false);
                }
                style.setY(topY);
                style.setX(leftX);
                style.setWidth(compWidth);
                topY += comp.getHeight() + wallOffset;
            } else {
                style.setWidth(Math.min(comp.getWidth(), compWidth));
                int rightX = leftX + comp.getWidth() - border;
                if (rightX > endValue) {
                    comp.getStyle().setDisabled(true);
                    continue;
                } else {
                    comp.getStyle().setDisabled(false);
                }
                style.setX(leftX);
                style.setHeight(compHeight);
                style.setY(topY);
                leftX += comp.getWidth() + wallOffset;
            }
        }
    }

    @Override
    public void click(int x, int y) {
        for (DrawnComponent comp : components) {
            if (comp.isHovered()) {
                comp.click(x, y);
                break;
            }
        }
        if (scrollButton1.isHovered()) {
            scrollButton1.click(x, y);
        } else if (scrollButton2.isHovered()) {
            scrollButton2.click(x, y);
        }
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        for (DrawnComponent comp : components) {
            if (comp.checkHover(x, y)) return true;
        }
        if (scrollButton1.checkHover(x, y)) return true;
        if (scrollButton2.checkHover(x, y)) return true;
        return false;
    }

    @Override
    public void setHoveredCursor(int x, int y) {
        for (DrawnComponent comp : components) {
            if (comp.isHovered()) {
                comp.setHoveredCursor(x, y);
                return;
            }
        }
        getFrame().setCursor(Cursor.DEFAULT_CURSOR);
    }

}
