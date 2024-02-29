import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Refrigerator extends FurnitureObject {
    private static final Color LIGHT_GRAY_COLOR = Color.LIGHT_GRAY; // Light gray color for the refrigerator
    private static final Color OUTLINE_COLOR = Color.BLACK; // Color of the outline

    public Refrigerator(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {

        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Draw the light gray square with black outline
        g2d.setColor(LIGHT_GRAY_COLOR);
        g2d.fillRect(x, y, width, height);

        // Draw the outline for the refrigerator
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRect(x, y, width, height);

        // Draw an X through the corners
        g2d.drawLine(x, y, x + width, y + height);
        g2d.drawLine(x, y + height, x + width, y);

        // Draw a narrow rectangle to indicate the door
        int doorWidth = width / 8; // Adjust as needed
        int doorHeight = height;
        int doorX = x + width /*- doorWidth*/;
        int doorY = y;
        g2d.setColor(LIGHT_GRAY_COLOR);
        g2d.fillRect(doorX, doorY, doorWidth, doorHeight);
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRect(doorX, doorY, doorWidth, doorHeight);
    }
}
