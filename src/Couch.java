import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Couch extends FurnitureObject {
    private static final Color TAN_COLOR = new Color(210, 180, 140); // Neutral toned tan color
    private static final Color OUTLINE_COLOR = Color.BLACK; // Color of the outline

    public Couch(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {

        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Draw the neutral-toned tan couch
        g2d.setColor(TAN_COLOR);
        g2d.fillRect(x, y, width, height);

        // Draw the outline for the backrest
        int backrestHeight = height / 3; // Adjust as needed
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRect(x, y, width, backrestHeight);

        // Draw the outlines for the armrests
        int armrestWidth = width / 6; // Adjust as needed
        g2d.drawRect(x, y + backrestHeight, armrestWidth, height - backrestHeight);
        g2d.drawRect(x + width - armrestWidth, y + backrestHeight, armrestWidth, height - backrestHeight);

        // Draw the outline for the cushion
        g2d.drawRect(x + armrestWidth, y + backrestHeight, width - 2 * armrestWidth, height - backrestHeight);
    }
}