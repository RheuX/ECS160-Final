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

    public Rectangle getBounds() {
        int x = (int) startPoint.getX() - width / 2;
        int y = (int) startPoint.getY() - height / 2;
        return new Rectangle(x, y, width, height);
    }

    public void rotate(double angleInDegrees) {
        // Calculate center point
        double centerX = startPoint.getX() + width / 2.0;
        double centerY = startPoint.getY() + height / 2.0;
    
        // Translate to origin
        double translatedX = startPoint.getX() - centerX;
        double translatedY = startPoint.getY() - centerY;
    
        // Perform rotation around origin
        double angleInRadians = Math.toRadians(angleInDegrees);
        double rotatedX = translatedX * Math.cos(angleInRadians) - translatedY * Math.sin(angleInRadians);
        double rotatedY = translatedX * Math.sin(angleInRadians) + translatedY * Math.cos(angleInRadians);
    
        // Translate back to original position
        startPoint.setLocation(rotatedX + centerX, rotatedY + centerY);
    }
    


    public abstract void draw(Graphics2D g2d);
}
