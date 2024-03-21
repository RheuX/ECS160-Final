import java.awt.*;

public class Window extends StructureObject {
    public Window(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Set the color to black
        g2d.setColor(Color.BLACK);

        // Draw the window
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
        g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);

        // Check if the window is selected
        if (isSelected) {
            // Draw the dotted outline
            g2d.setStroke(DOTTED_STROKE);
            int x1 = Math.min(startPoint.x, endPoint.x) - 3;
            int y1 = Math.min(startPoint.y, endPoint.y) - 3;
            int width = Math.abs(startPoint.x - endPoint.x) + 6;
            int height = Math.abs(startPoint.y - endPoint.y) + 6;
            g2d.drawRect(x1, y1, width, height);
        }
    }
}
