import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Desk extends FurnitureObject {
    private static final Color WOOD_COLOR = new Color(139, 69, 19); // Brown wood color
    private static final Color OUTLINE_COLOR = Color.BLACK; // Color of the outline

    public Desk(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Draw the desk surface
        g2d.setColor(WOOD_COLOR);
        g2d.fillRect(x, y, width, height);

        // Draw outline for the desk
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRect(x, y, width, height);
    }
}
