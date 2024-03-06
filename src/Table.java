import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;

public class Table extends FurnitureObject {
    private static final Color DARK_WOOD_COLOR = new Color(101, 50, 40); // Darker brown wood color

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

        if (isSelected()) {
            // Draw the dotted outline if the couch is selected
            g2d.setStroke(DOTTED_STROKE);
            g2d.setColor(OUTLINE_COLOR);
            g2d.drawRect(x - 3, y - 3, width + 6, height + 6);
            g2d.setStroke(new BasicStroke()); // Reset the stroke to the default
        }
    }
}