import java.awt.*;

public abstract class FurnitureObject {
    protected Point startPoint;
    protected int width;
    protected int height;

    public FurnitureObject(Point startPoint, int width, int height) {
        this.startPoint = startPoint;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public abstract void draw(Graphics2D g2d);
}
