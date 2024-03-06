import java.awt.*;

public abstract class FurnitureObject {

    protected Point startPoint;
    protected int width;
    protected int height;
    protected boolean isSelected;
    protected static final Stroke DOTTED_STROKE = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
    protected static final Color OUTLINE_COLOR = Color.BLACK;

    public FurnitureObject(Point startPoint, int width, int height) {
        this.startPoint = startPoint;
        this.width = width;
        this.height = height;
        this.isSelected = false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean contains(Point point) {
        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;
        return (point.getX() >= x && point.getX() <= x + width && point.getY() >= y && point.getY() <= y + height);
    }

    public abstract void draw(Graphics2D g2d);
}
