import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Chair extends FurnitureObject {
    private static final int LEG_WIDTH = 10; // Width of chair legs
    private static final int LEG_HEIGHT = 40; // Height of chair legs
    private static final int BACKREST_WIDTH = 20; // Width of chair backrest
    private static final int BACKREST_HEIGHT = 80; // Height of chair backrest

    public Chair(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Calculate the position and dimensions of the chair to make it centered around the mouse click position
        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Draw the base of the chair
        g2d.setColor(new Color(139, 69, 19)); // Brown color
        g2d.fillRect(x, y + height - LEG_HEIGHT, width, LEG_HEIGHT);

        // Draw the chair backrest
        int backrestX1 = x + width / 4;
        int backrestX2 = x + width * 3 / 4 - BACKREST_WIDTH;
        int backrestY = y + height - LEG_HEIGHT - BACKREST_HEIGHT;
        g2d.fillRect(backrestX1, backrestY, BACKREST_WIDTH, BACKREST_HEIGHT);
        g2d.fillRect(backrestX2, backrestY, BACKREST_WIDTH, BACKREST_HEIGHT);

        // Draw the chair legs
        g2d.setColor(Color.BLACK); // Black color for legs
        g2d.fillRect(x, y + height - LEG_HEIGHT, LEG_WIDTH, LEG_HEIGHT);
        g2d.fillRect(x + width - LEG_WIDTH, y + height - LEG_HEIGHT, LEG_WIDTH, LEG_HEIGHT);
    }
}
