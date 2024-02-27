import java.awt.*;

// @author ChatGPT
public class Wall extends StructuralObject {
    public Wall(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (startPoint != null && endPoint != null) {
            // Draw a thick solid line connecting the points
            g2d.setStroke(new BasicStroke(10)); // Set thickness
            g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }
}
