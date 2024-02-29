import java.awt.*;
import java.awt.geom.Line2D;

// @author ChatGPT
public class Door extends StructureObject {
    public Door(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (startPoint != null && endPoint != null) {
        // Draw the straight line with width 2
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(new Line2D.Double(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY()));

        // Calculate the position for the line forming a 90-degree angle
        double angle = Math.atan2(endPoint.getY() - startPoint.getY(), endPoint.getX() - startPoint.getX());
        double length = 10; // Adjust the length as needed
        double angle90 = angle + Math.PI / 2; // 90-degree angle

        double x1 = endPoint.getX() + length * Math.cos(angle90);
        double y1 = endPoint.getY() + length * Math.sin(angle90);

        // Draw the line forming a 90-degree angle
        g2d.draw(new Line2D.Double(endPoint.getX(), endPoint.getY(), x1, y1));
        }
    }
}
