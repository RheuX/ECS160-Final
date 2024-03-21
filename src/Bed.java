import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;

public class Bed extends FurnitureObject {
    private static final int HEADBOARD_HEIGHT = 30; // Height of the headboard relative to the bed height
    private static final int PILLOW_WIDTH = 30; // Width of the pillows
    private static final int PILLOW_HEIGHT = 20; // Height of the pillows

    public Bed(Point startPoint, int width, int height) {
        super(startPoint, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Calculate the position and dimensions of the bed to make it centered around the start point
        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Draw the headboard
        g2d.setColor(Color.GRAY);
        g2d.fillRect(x, y, width, HEADBOARD_HEIGHT);

        // Draw outline for the headboard
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRect(x, y, width, HEADBOARD_HEIGHT);

        // Draw the bed frame
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x, y + HEADBOARD_HEIGHT, width, height - HEADBOARD_HEIGHT);

        // Draw outline for the bed frame
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRect(x, y + HEADBOARD_HEIGHT, width, height - HEADBOARD_HEIGHT);

        // Calculate the position and dimensions of the pillows
        int pillowXLeft = x + width / 4 - PILLOW_WIDTH / 2;
        int pillowXRight = x + 3 * width / 4 - PILLOW_WIDTH / 2;
        int pillowY = y + HEADBOARD_HEIGHT - PILLOW_HEIGHT;

        // Draw the left pillow
        g2d.setColor(Color.WHITE);
        g2d.fillOval(pillowXLeft, pillowY, PILLOW_WIDTH, PILLOW_HEIGHT);

        // Draw outline for the left pillow
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawOval(pillowXLeft, pillowY, PILLOW_WIDTH, PILLOW_HEIGHT);

        // Draw the right pillow
        g2d.setColor(Color.WHITE);
        g2d.fillOval(pillowXRight, pillowY, PILLOW_WIDTH, PILLOW_HEIGHT);

        // Draw outline for the right pillow
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawOval(pillowXRight, pillowY, PILLOW_WIDTH, PILLOW_HEIGHT);

        if (isSelected()) {
            // Draw the dotted outline if the couch is selected
            g2d.setStroke(DOTTED_STROKE);
            g2d.setColor(SELECT_COLOR);
            g2d.drawRect(x - 3, y - 3, width + 6, height + 6);
            g2d.setStroke(new BasicStroke()); // Reset the stroke to the default
        }
    }
}
