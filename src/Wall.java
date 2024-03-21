import java.awt.*;

public class Wall extends StructureObject {

    public Wall(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (startPoint != null && endPoint != null) {
            // Set the color to black
            g2d.setColor(Color.BLACK);
            
            // Draw the wall
            g2d.setStroke(new BasicStroke(10)); // Set thickness
            g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            
            // Check if the wall is selected
            if (isSelected) {
                // Draw the dotted outline
                g2d.setStroke(DOTTED_STROKE);
                g2d.setColor(OUTLINE_COLOR);
                int x1 = Math.min(startPoint.x, endPoint.x) - 3;
                int y1 = Math.min(startPoint.y, endPoint.y) - 3;
                int width = Math.abs(startPoint.x - endPoint.x) + 6;
                int height = Math.abs(startPoint.y - endPoint.y) + 6;
                g2d.drawRect(x1, y1, width, height);
            }
        }
    }
}
