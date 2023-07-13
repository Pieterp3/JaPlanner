package ui.components.containers;

import ui.Frame;
import ui.components.DrawnComponent;
import util.structures.List;
/**TODO
 * 
18. Make ContainerComponent abstract class and extend drawnComponent
 */
public abstract class ContainerComponent extends DrawnComponent {

    private List<DrawnComponent> components;

    public ContainerComponent(Frame frame) {
        super(frame);
        components = new List<>();
    }

    public List<DrawnComponent> getComponents() {
        return components;
    }

    public DrawnComponent getComponent(int i) {
        return components.get(i);
    }

    public int getComponentCount() {
        return components.size();
    }

    public void addComponent(DrawnComponent component) {
        components.add(component);
        repositionComponents();
    }

    public void removeComponent(DrawnComponent component) {
        components.remove(component);
        repositionComponents();
    }

    @Override
    public void click(int x, int y) {
        for (DrawnComponent comp : getComponents()) {
            if (comp.isHovered()) {
                comp.click(x, y);
                break;
            }
        }
    }

    @Override
    public void setHoveredCursor(int x, int y) {
        for (DrawnComponent comp : components) {
            if (comp.isHovered()) {
                comp.setHoveredCursor(x, y);
                return;
            }
        }
        getFrame().setCursor(Frame.DEFAULT_CURSOR);
    }

    public abstract void repositionComponents();

}
