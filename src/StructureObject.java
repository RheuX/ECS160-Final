import java.awt.Point;
import java.awt.Stroke;
import java.io.Serializable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

// @author ChatGPT
public abstract class StructureObject implements Serializable {
    protected Point startPoint;
    protected Point endPoint;
    protected boolean isSelected;
    private static final int SELECTION_THRESHOLD = 10; // Adjust as needed
    protected static final Stroke DOTTED_STROKE = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
    protected static final Color OUTLINE_COLOR = Color.RED;

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


    public boolean contains(Point point) {
        // Calculate the distance from the point to the line formed by the wall
        double distance = distanceToLine(startPoint, endPoint, point);
        // Check if the distance is within the threshold
        return distance <= SELECTION_THRESHOLD;
    }

    // Helper method to calculate the distance from a point to a line segment
    private double distanceToLine(Point start, Point end, Point point) {
        double xDelta = end.x - start.x;
        double yDelta = end.y - start.y;
        if ((xDelta == 0) && (yDelta == 0)) {
            return Math.sqrt((point.x - start.x) * (point.x - start.x) + (point.y - start.y) * (point.y - start.y));
        }

        double u = ((point.x - start.x) * xDelta + (point.y - start.y) * yDelta) / (xDelta * xDelta + yDelta * yDelta);
        Point closestPoint;
        if (u < 0) {
            closestPoint = start;
        } else if (u > 1) {
            closestPoint = end;
        } else {
            closestPoint = new Point((int) Math.round(start.x + u * xDelta), (int) Math.round(start.y + u * yDelta));
        }
        return closestPoint.distance(point);
    }

    // Abstract method for drawing the object
    public abstract void draw(Graphics2D g2d);
}
