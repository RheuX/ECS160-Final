import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Desk extends FurnitureObject {
    private static final int LEG_LENGTH = 20; // Length of each leg
    private static final double ANGLE = Math.toRadians(25); // Angle of the legs (25 degrees)

    public Desk(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Calculate the position of the desk to make it centered around the mouse click position
        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Draw the desk surface
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(x, y, width, height);

        // Draw the desk legs
        drawLeg(g2d, x, y + height); // Bottom-left leg
        drawLeg(g2d, x + width, y + height); // Bottom-right leg
    }

    // Method to draw a leg at a specified position
    private void drawLeg(Graphics2D g2d, int x, int y) {
        // Calculate the position of the leg start point
        int legX = x;
        int legY = y;

        // Calculate the end point of the leg based on the angle
        int legEndX = (int) (legX - LEG_LENGTH * Math.cos(ANGLE)); // Subtract for leftward direction
        int legEndY = (int) (legY - LEG_LENGTH * Math.sin(ANGLE)); // Subtract for upward direction

        // Draw the leg
        g2d.setColor(Color.BLACK);
        g2d.drawLine(legX, legY, legEndX, legEndY);
    }
}
