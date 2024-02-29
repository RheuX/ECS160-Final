import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Table extends FurnitureObject {
    private static final Color DARK_WOOD_COLOR = new Color(101, 50, 40); // Darker brown wood color
    private static final Color OUTLINE_COLOR = Color.BLACK; // Color of the outline

    public Table(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Draw the table surface
        g2d.setColor(DARK_WOOD_COLOR);
        g2d.fillRect(x, y, width, height);

        // Draw outline for the table
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRect(x, y, width, height);
    }
}