import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class TV extends FurnitureObject {
    private static final Color DARK_GRAY_COLOR = new Color(64, 64, 64); // Dark gray color for the TV

    public TV(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void draw(Graphics2D g2d) {

        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;

        // Draw the main body of the TV
        int tvBodyWidth = width * 2; // Adjust as needed
        int tvBodyHeight = height / 10; // Adjust as needed
        g2d.setColor(DARK_GRAY_COLOR);
        g2d.fillRect(x, y, tvBodyWidth, tvBodyHeight);

        // Draw the outline for the TV body
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, tvBodyWidth, tvBodyHeight);

        // Draw the base of the TV
        int baseWidth = width / 2; // Adjust as needed
        int baseHeight = height / 20; // Adjust as needed
        int baseX = x + (tvBodyWidth - baseWidth) / 2;
        int baseY = y + tvBodyHeight;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(baseX, baseY, baseWidth, baseHeight);

        // Draw the outline for the base
        g2d.setColor(Color.BLACK);
        g2d.drawRect(baseX, baseY, baseWidth, baseHeight);
    }
}