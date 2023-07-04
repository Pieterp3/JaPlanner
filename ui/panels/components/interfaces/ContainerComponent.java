package ui.panels.components.interfaces;

import java.util.List;

import ui.panels.components.DrawnComponent;

public interface ContainerComponent {
    public void addComponent(DrawnComponent component);
    public void removeComponent(DrawnComponent component);
    public List<DrawnComponent> getComponents();
    public void mouseMoved(int x, int y);
}
