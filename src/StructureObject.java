import java.awt.Point;
import java.awt.Graphics2D;

// @author ChatGPT
public abstract class StructureObject {
    protected Point startPoint;
    protected Point endPoint;
    protected boolean isSelected;

    public StructureObject(Point startPoint, Point endPoint) {
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    // Abstract method for drawing the object
    public abstract void draw(Graphics2D g2d);
}
