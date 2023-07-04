package math;

public class Dimension {
    
    private double width, height;

    public Dimension(double width, double height) {
        this.width = width;
        this.height = height;    
    }

    public Dimension() {
        this(0, 0);
    }

    public int getWidth() {
        return (int) width;
    }

    public int getHeight() {
        return (int) height;
    }

    public double getExactWidth() {
        return width;
    }

    public double getExactHeight() {
        return height;
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(Dimension dimension) {
        setSize(dimension.getExactWidth(), dimension.getExactHeight());
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;    
    }
    
}
