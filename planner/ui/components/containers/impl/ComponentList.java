package ui.components.containers.impl;

import ui.components.DrawnComponent;
import ui.components.containers.ContainerComponent;
import ui.components.interfaces.Scrollable;
import ui.components.style.Style;
import ui.frames.Frame;
import ui.components.impl.Button;

import ui.graphics.Color;
import ui.graphics.Graphics;
import util.structures.Map;
/**TODO
 * 16. Allow Dragging on scroller
 * 19. Check for bug in resizingComponents when complist is horizontal
 * 26. Add single, interval and multiple interval selection modes to complist
 */
public class ComponentList extends ContainerComponent implements Scrollable {

    private static final Map<String, String> ARROW_MAP = new Map<String, String>() {
        {
            put("up", "^");
            put("down", "v");
            put("left", "<");
            put("right", ">");
        }
    };

    private static String getArrow(String direction) {
        return ARROW_MAP.get(direction);
    }

    private int scrollIndex;
    private Button scrollButton1, scrollButton2;

    public ComponentList(Frame frame, int x, int y, int width, int height) {
        super(frame);
        scrollIndex = 2;
        initStyle(x, y, width, height);
    }

    public ComponentList(Frame frame) {
        this(frame, 0, 0, 200, 100);
    }

    private void initStyle(int x, int y, int width, int height) {
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

        scrollButton1 = new Button(getFrame(), getArrow("up")) {
            @Override
            public void click(int x, int y) {
                scroll(-1);
                System.out.println("Clicked");
            }
        };
        scrollButton2 = new Button(getFrame(), getArrow("down")) {
            @Override
            public void click(int x, int y) {
                scroll(1);
            }
        };
        scrollButton1.setAttribute("alignment", "center");
        scrollButton2.setAttribute("alignment", "center");
        addComponent(scrollButton1);
        addComponent(scrollButton2);
    }

    @Override
    public void draw(Graphics g, Style style) {
        g.drawBackground(getX(), getY(), getWidth(), getHeight(), isHovered(), isPressed());
        g.attemptBorder(getX(), getY(), getWidth(), getHeight(), isHovered());

        getComponent(0).updateGraphicsStyle(g);
        getComponent(1).updateGraphicsStyle(g);

        for (int i = scrollIndex; i < getComponentCount(); i++) {
            getComponent(i).updateGraphicsStyle(g);
        }
        g.setStyle(style);
        g.drawScrollbar(scrollButton1.getStyle(), getX(), getY(), getWidth(), getHeight(), scrollIndex,
                getComponentCount() - 1);
    }

    public void scroll(int amount) {
        scrollIndex += (amount * style.getIntAttribute("scrollMultiplier"));
        if (scrollIndex < 2)
            scrollIndex = 2;
        if (scrollIndex > getComponentCount() - 1)
            scrollIndex = getComponentCount() - 1;
        repositionComponents();
    }

    @Override
    public void removeAllComponents() {
        while(getComponentCount() > 2) {
            removeComponent(getComponent(2));
        }
    }

    // Prefers Vertical scrolling but defaults to horizontal
    // Stops components from being drawn over the edge of the list
    @Override
    public void repositionComponents() {
        int padding = style.getIntAttribute("padding");
        int border = style.getIntAttribute("borderWidth");
        int scrollbarSize = style.getIntAttribute("scrollbarSize");
        boolean resizesVertically = style.getBooleanAttribute("resizesVertically");

        int wallOffset = padding + border;
        int compWidth = getWidth() - (wallOffset * 2) - scrollbarSize - padding;
        int compHeight = getHeight() - (wallOffset * 2) - scrollbarSize - padding;
        int topY = wallOffset + getY();
        int leftX = wallOffset + getX();
        int endValue = resizesVertically ? getHeight() + getY() : getWidth() + getX();

        int scrollbarX, scrollbarY;
        if (resizesVertically) {
            scrollbarX = getX() + getWidth() - scrollbarSize - border;
            scrollbarY = getY() + border;
            int scrollbarY2 = getY() + getHeight() - scrollbarSize - border;
            scrollButton1.setAttributes(new Map<>() {
                {
                    put("text", getArrow("up"));
                    put("x", scrollbarX);
                    put("y", scrollbarY);
                    put("width", scrollbarSize);
                    put("height", scrollbarSize);
                }
            });
            scrollButton2.setAttributes(new Map<>() {
                {
                    put("text", getArrow("down"));
                    put("x", scrollbarX);
                    put("y", scrollbarY2);
                    put("width", scrollbarSize);
                    put("height", scrollbarSize);
                }
            });
        } else {
            scrollbarX = getX() + border;
            scrollbarY = getY() + getHeight() - scrollbarSize - border;
            int scrollbarX2 = getX() + getWidth() - scrollbarSize - border;
            scrollButton1.setAttributes(new Map<>() {
                {
                    put("text", getArrow("left"));
                    put("x", scrollbarX);
                    put("y", scrollbarY);
                    put("width", scrollbarSize);
                    put("height", scrollbarSize);
                }
            });
            scrollButton2.setAttributes(new Map<>() {
                {
                    put("text", getArrow("right"));
                    put("x", scrollbarX2);
                    put("y", scrollbarY);
                    put("width", scrollbarSize);
                    put("height", scrollbarSize);
                }
            });
        }
        for (int i = scrollIndex; i < getComponentCount(); i++) {
            DrawnComponent comp = getComponent(i);
            if (comp == scrollButton1 || comp == scrollButton2)
                continue;
            final int finalTopY = topY;
            final int finalLeftX = leftX;
            if (resizesVertically) {
                comp.setAttribute("height", Math.min(comp.getHeight(), compHeight));
                int bottomY = topY + comp.getHeight() - border;
                if (bottomY > endValue) {
                    comp.setAttribute("disabled", true);
                    continue;
                } else {
                    comp.setAttribute("disabled", false);
                }
                comp.setAttributes(new Map<>() {
                    {
                        put("y", finalTopY);
                        put("x", finalLeftX);
                        put("width", compWidth);
                    }
                });
                topY += comp.getHeight() + wallOffset;
            } else {
                comp.setAttribute("width", Math.min(comp.getWidth(), compWidth));
                int rightX = leftX + comp.getWidth() - border;
                if (rightX > endValue) {
                    comp.setAttribute("disabled", true);
                    continue;
                } else {
                    comp.setAttribute("disabled", false);
                }
                comp.setAttributes(new Map<>() {
                    {
                        put("x", finalLeftX);
                        put("y", finalTopY);
                        put("height", compHeight);
                    }
                });
                leftX += comp.getWidth() + wallOffset;
            }
        }
    }

}
