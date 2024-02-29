import java.awt.*;
import java.awt.geom.Line2D;

// @author ChatGPT
public class Window extends StructureObject {
    public Window(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
        g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
