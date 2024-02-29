import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.RoundRectangle2D;

public class Sink extends FurnitureObject {
    private static final Color OUTLINE_COLOR = Color.BLACK; // Color of the outline

    public Sink(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Draw the rectangular base with smooth edges
        int cornerArcSize = width / 10; // Adjust as needed for the curve size
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRoundRect(x, y, width, height, cornerArcSize, cornerArcSize);

        // Draw outline for the rectangular base
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRoundRect(x, y, width, height, cornerArcSize, cornerArcSize);

        // Draw the sink oval
        int sinkX = x;
        int sinkY = y + height / 5;
        int sinkWidth = width;
        int sinkHeight = height * 3 / 5;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(sinkX, sinkY, sinkWidth, sinkHeight);

        // Draw outline for the sink oval
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawOval(sinkX, sinkY, sinkWidth, sinkHeight);

        // Draw the drain (circle) inside the sink oval
        int drainRadius = width / 10;
        int drainX = x + width / 2 - drainRadius;
        int drainY = y + height / 2 - drainRadius;
        g2d.setColor(Color.BLACK);
        g2d.fillOval(drainX, drainY, 2 * drainRadius, 2 * drainRadius);

        // Draw outline for the drain
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawOval(drainX, drainY, 2 * drainRadius, 2 * drainRadius);

        // Draw the rectangular pipe
        int pipeWidth = width / 5;
        int pipeHeight = height / 5;
        int pipeX = x + (width - pipeWidth) / 2;
        int pipeY = y;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(pipeX, pipeY, pipeWidth, pipeHeight);

        // Draw outline for the pipe
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRect(pipeX, pipeY, pipeWidth, pipeHeight);
    }
}
