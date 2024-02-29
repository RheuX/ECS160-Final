import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Shower extends FurnitureObject {
    private static final int BASE_WIDTH = 60; // Width of the bathtub base
    private static final int BASE_HEIGHT = BASE_WIDTH * 2; // Height of the bathtub base
    private static final double OVAL_SCALE_FACTOR = 0.8; // Scale factor for the oval
    private static final int PIPE_WIDTH = 10; // Width of the pipe
    private static final int PIPE_HEIGHT = 30; // Height of the pipe
    private static final int PIPE_OFFSET_X = BASE_WIDTH / 2 - PIPE_WIDTH / 2; // X offset for the pipe
    private static final Color OUTLINE_COLOR = Color.BLACK; // Color of the outline
    private static final int ROUNDING_ARC_WIDTH = 10; // Width of rounding arc
    private static final int ROUNDING_ARC_HEIGHT = 10; // Height of rounding arc

    public Shower(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Calculate the position and dimensions of the bathtub base to center it around the mouse click position
        int x = (int) startPoint.getX() - BASE_WIDTH / 2;
        int y = (int) startPoint.getY() - BASE_HEIGHT / 2;

        // Draw the bathtub base (rectangle with rounded corners)
        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(x, y, BASE_WIDTH, BASE_HEIGHT, ROUNDING_ARC_WIDTH, ROUNDING_ARC_HEIGHT);

        // Draw outline for the base
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRoundRect(x, y, BASE_WIDTH, BASE_HEIGHT, ROUNDING_ARC_WIDTH, ROUNDING_ARC_HEIGHT);

        // Calculate the position and dimensions of the oval-shaped center of the bathtub
        int ovalWidth = (int) (BASE_WIDTH * OVAL_SCALE_FACTOR);
        int ovalHeight = (int) (BASE_HEIGHT * OVAL_SCALE_FACTOR);
        int ovalX = x + (BASE_WIDTH - ovalWidth) / 2;
        int ovalY = y + (BASE_HEIGHT - ovalHeight) / 2;

        // Draw the oval-shaped center of the bathtub
        g2d.setColor(Color.WHITE);
        g2d.fillOval(ovalX, ovalY, ovalWidth, ovalHeight);

        // Draw outline for the oval
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawOval(ovalX, ovalY, ovalWidth, ovalHeight);

        // Calculate the position and dimensions of the pipe
        int pipeX = x + BASE_WIDTH / 2 - PIPE_WIDTH / 2;
        int pipeY = y + BASE_HEIGHT / 20;

        // Draw the pipe
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(pipeX, pipeY, PIPE_WIDTH, PIPE_HEIGHT);

        // Draw outline for the pipe
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRect(pipeX, pipeY, PIPE_WIDTH, PIPE_HEIGHT);
    }
}
