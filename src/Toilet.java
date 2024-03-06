import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;

public class Toilet extends FurnitureObject {
    private static final int BASE_WIDTH = 20; // Width of the toilet base
    private static final int BASE_HEIGHT = 50; // Height of the toilet base
    private static final int CIRCLE_RADIUS = 15; // Radius of the larger circle
    private static final int SMALL_CIRCLE_RADIUS = 7; // Radius of the smaller circle
    private static final int CIRCLE_OVERLAP = (int) (BASE_WIDTH / 3.0); // Amount of overlap for the circles into the rectangle

    public Toilet(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Calculate the position and dimensions of the toilet base to center it around the mouse click position
        int x = (int) startPoint.getX() - BASE_WIDTH / 2;
        int y = (int) startPoint.getY() - BASE_HEIGHT / 2;

        // Calculate the position and dimensions of the larger circle to overlap into the rectangle
        int circleX = x + BASE_WIDTH - CIRCLE_OVERLAP; // Adjusted to overlap into the rectangle
        int circleY = y + BASE_HEIGHT / 2 - CIRCLE_RADIUS;

        // Draw the larger circle
        g2d.setColor(Color.GRAY);
        g2d.fillOval(circleX, circleY, 2 * CIRCLE_RADIUS, 2 * CIRCLE_RADIUS);

        // Draw outline for the base
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawOval(circleX, circleY, 2 * CIRCLE_RADIUS, 2 * CIRCLE_RADIUS);

        // Calculate the position and dimensions of the smaller circle inside the larger circle
        int smallCircleX = circleX + CIRCLE_RADIUS - SMALL_CIRCLE_RADIUS;
        int smallCircleY = circleY + CIRCLE_RADIUS - SMALL_CIRCLE_RADIUS;

        // Draw the smaller circle
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval(smallCircleX, smallCircleY, 2 * SMALL_CIRCLE_RADIUS, 2 * SMALL_CIRCLE_RADIUS);

        // Draw outline for the base
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawOval(smallCircleX, smallCircleY, 2 * SMALL_CIRCLE_RADIUS, 2 * SMALL_CIRCLE_RADIUS);

        // Draw the toilet base (rectangle)
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(x, y, BASE_WIDTH, BASE_HEIGHT);

        // Draw outline for the base
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRect(x, y, BASE_WIDTH, BASE_HEIGHT);

        if (isSelected()) {
            // Draw the dotted outline if the couch is selected
            g2d.setStroke(DOTTED_STROKE);
            g2d.setColor(OUTLINE_COLOR);
            g2d.drawRect(x - 3, y - 3, BASE_WIDTH + CIRCLE_RADIUS + SMALL_CIRCLE_RADIUS + 6, BASE_HEIGHT + 6);
            g2d.setStroke(new BasicStroke()); // Reset the stroke to the default
        }
    }
}
