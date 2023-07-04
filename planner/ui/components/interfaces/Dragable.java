package ui.components.interfaces;

public interface Dragable {

    public void setDragStart(int x, int y);
    public void drop(int x, int y);
    public void drag(int currentX, int currentY);


}
