package ui.components.interfaces;

import java.util.List;

import ui.components.DrawnComponent;

public interface ContainerComponent {
    public void addComponent(DrawnComponent component);
    public void removeComponent(DrawnComponent component);
    public List<DrawnComponent> getComponents();
    public boolean mouseMoved(int x, int y);
}
