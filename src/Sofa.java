import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Sofa extends FurnitureObject {
    private static final Color TAN_COLOR = new Color(210, 180, 140); // Neutral toned tan color
    private static final Color OUTLINE_COLOR = Color.BLACK;

    public Sofa(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {

        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Draw the neutral-toned tan sofa
        g2d.setColor(TAN_COLOR);
        g2d.fillRect(x, y, width * 4 / 5, height);

        // Draw the arms, backrest, and cushions with outlines
        g2d.setColor(OUTLINE_COLOR);

        // Draw the outline for the backrest
        g2d.drawRect(x, y, width / 5, height);

        // Draw the outlines for the seats
        g2d.drawRect(x + width / 5, y, width * 3 / 5, height / 2); // Upper seat
        g2d.drawRect(x + width / 5, y + height / 2, width * 3 / 5, height / 2); // Lower seat

        // Draw the outlines for the arms
        int armWidth = width * 3 / 5; // Adjust as needed
        int armHeight = height / 8; // Adjust as needed
        g2d.drawRect(x + width / 5, y, armWidth, armHeight); // Upper arm
        g2d.drawRect(x + width / 5, y + height - armHeight, armWidth, armHeight); // Lower arm
    }
}