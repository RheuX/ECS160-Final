import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Sink extends FurnitureObject {
    private static final Color OUTLINE_COLOR = Color.BLACK; // Color of the outline

    public Sink(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {
        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Calculate adjusted width based on the height
        int adjustedWidth = (int) (height * 1.1);

        // Draw the rectangular base
        g2d.setColor(Color.DARK_GRAY);
        g2d.fill(new Rectangle2D.Double(x, y, adjustedWidth, height));

        // Draw outline for the rectangular base
        g2d.setColor(OUTLINE_COLOR);
        g2d.draw(new Rectangle2D.Double(x, y, adjustedWidth, height));

        // Draw the larger oval around the sink basin
        int sinkWidth = height * 3 / 3; // Width of the sink oval
        int sinkHeight = height * 2 / 3; // Height of the sink oval
        int sinkX = x + (adjustedWidth - sinkWidth) / 2;
        int sinkY = y + height / 5;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(sinkX, sinkY, sinkWidth, sinkHeight);

        // Draw outline for the larger sink oval
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawOval(sinkX, sinkY, sinkWidth, sinkHeight);

        // Draw the sink oval
        int innerSinkWidth = sinkWidth * 9 / 10; // Width of the inner sink oval
        int innerSinkHeight = sinkHeight * 9 / 10; // Height of the inner sink oval
        int innerSinkX = sinkX + (sinkWidth - innerSinkWidth) / 2;
        int innerSinkY = sinkY + (sinkHeight - innerSinkHeight) / 5;
        g2d.setColor(Color.WHITE);
        g2d.fillOval(innerSinkX, innerSinkY, innerSinkWidth, innerSinkHeight);

        // Draw outline for the inner sink oval
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawOval(innerSinkX, innerSinkY, innerSinkWidth, innerSinkHeight);

        // Draw the drain (circle) inside the sink oval
        int drainDiameter = innerSinkHeight / 5; // Diameter of the drain circle
        int drainX = innerSinkX + (innerSinkWidth - drainDiameter) / 2;
        int drainY = innerSinkY + (innerSinkHeight - drainDiameter) / 2;
        g2d.setColor(Color.BLACK);
        g2d.fillOval(drainX, drainY, drainDiameter, drainDiameter);

        // Draw outline for the drain
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawOval(drainX, drainY, drainDiameter, drainDiameter);

        // Draw the rectangular pipe with slightly larger height
        int pipeWidth = height / 8; // Width of the pipe
        int pipeHeight = height / 3; // Height of the pipe
        int pipeX = x + adjustedWidth / 2 - pipeWidth / 2;
        int pipeY = y + adjustedWidth / 15;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(pipeX, pipeY, pipeWidth, pipeHeight);

        // Draw outline for the pipe
        g2d.setColor(OUTLINE_COLOR);
        g2d.drawRect(pipeX, pipeY, pipeWidth, pipeHeight);
    }
}
