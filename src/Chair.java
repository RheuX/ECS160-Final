import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.BasicStroke;

public class Chair extends FurnitureObject {
    public Chair(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Draw square with circular edges
        int cornerArcSize = width / 4; // Adjust as needed for the curve size
        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(x, y, width, height, cornerArcSize, cornerArcSize);

        // Draw outline for the square with circular edges
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRoundRect(x, y, width, height, cornerArcSize, cornerArcSize);

        // Draw crescent moon shape
        int crescentWidth = width / 2;
        int crescentHeight = height;
        int crescentX = x - crescentWidth / 2;
        int crescentY = y;
        g2d.setColor(Color.LIGHT_GRAY);
        drawCrescent(g2d, crescentX, crescentY, crescentWidth, crescentHeight);

        // Draw outline for the crescent moon shape
        g2d.setColor(OUTLINE_COLOR);
        g2d.draw(drawCrescent(g2d, crescentX, crescentY, crescentWidth, crescentHeight));

        if (isSelected()) {
            // Draw the dotted outline if the couch is selected
            g2d.setStroke(DOTTED_STROKE);
            g2d.setColor(OUTLINE_COLOR);
            g2d.drawRect(x - 3, y - 3, width + 6, height + 6);
            g2d.setStroke(new BasicStroke()); // Reset the stroke to the default
        }
    }

    // Method to draw a crescent moon shape
    private Area drawCrescent(Graphics2D g2d, int x, int y, int width, int height) {
        // Create two arcs to form the crescent moon shape
        Arc2D outerArc = new Arc2D.Double(x, y, width, height, 90, 180, Arc2D.OPEN);
        Arc2D innerArc = new Arc2D.Double(x + width / 4, y, width / 2, height, 90, 180, Arc2D.OPEN);

        // Create areas from the arcs
        Area area = new Area(outerArc);
        area.subtract(new Area(innerArc));

        // Fill the crescent moon shape
        g2d.fill(area);

        return area;
    }
}
