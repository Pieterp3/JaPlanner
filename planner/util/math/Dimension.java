package util.math;

public class Dimension {
    
    private double width, height;

    public Dimension(double width, double height) {
        this.width = width;
        this.height = height;    
    }

    public Dimension() {
        this(0, 0);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(Dimension dimension) {
        setSize(dimension.getWidth(), dimension.getHeight());
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;    
    }
    
}
