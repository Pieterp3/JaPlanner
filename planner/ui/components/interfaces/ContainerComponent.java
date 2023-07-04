package ui.components.interfaces;

import ui.components.DrawnComponent;

import structures.List;

public interface ContainerComponent {
    public void addComponent(DrawnComponent component);
    public void removeComponent(DrawnComponent component);
    public List<DrawnComponent> getComponents();
    public boolean mouseMoved(int x, int y);
}
