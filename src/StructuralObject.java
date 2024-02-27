import java.awt.Point;
import java.awt.Graphics2D;

// @author ChatGPT
public abstract class StructuralObject {
    protected Point startPoint;
    protected Point endPoint;

    public StructuralObject(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    // Abstract method for drawing the object
    public abstract void draw(Graphics2D g2d);
}
