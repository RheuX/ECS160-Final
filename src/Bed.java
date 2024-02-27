import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Bed extends FurnitureObject {
    private static final int HEADBOARD_HEIGHT = 30; // Height of the headboard relative to the bed height

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

        // Draw the bed frame
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x, y + HEADBOARD_HEIGHT, width, height - HEADBOARD_HEIGHT);
    }
}
