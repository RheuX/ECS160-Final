import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DrawingTools {
    private JPanel canvas;
    private DrawingMode drawingMode = DrawingMode.NONE;
    private Point tempPoint1;
    private Point tempPoint2;

    public DrawingTools(JPanel canvas) {
        this.canvas = canvas;
        setupDrawing();
    }

    private void setupDrawing() {
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (drawingMode == DrawingMode.NONE) {
                    return;
                }

                if (isStructure()) {
                    if (tempPoint1 == null) {
                        tempPoint1 = e.getPoint();
                    } else {
                        tempPoint2 = e.getPoint();
                        drawStructuralObject(createStructuralObject(tempPoint1, tempPoint2));
                        tempPoint1 = null;
                    }
                } else {
                    drawFurnitureObject(createFurnitureObject(e.getPoint(), 50, 100));
                }
            }
        });
    }

    public void setDrawingMode(DrawingMode mode) {
        this.drawingMode = mode;
    }

    private boolean isStructure() {
        return drawingMode == DrawingMode.DOOR || drawingMode == DrawingMode.WALL || drawingMode == DrawingMode.WINDOW;
    }

    private void drawStructuralObject(StructuralObject object) {
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        object.draw(g2d);
        g2d.dispose();
    }

    private void drawFurnitureObject(FurnitureObject furnitureObject) {
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        furnitureObject.draw(g2d);
        g2d.dispose();
    }

    private StructuralObject createStructuralObject(Point startPoint, Point endPoint) {
        switch (drawingMode) {
            case WALL:
                return new Wall(startPoint, endPoint);
            case WINDOW:
                return new Window(startPoint, endPoint);
            case DOOR:
                return new Door(startPoint, endPoint);
            default:
                throw new IllegalArgumentException("Unknown drawing mode: " + drawingMode);
        }
    }

    private FurnitureObject createFurnitureObject(Point point, int width, int height) {
        switch (drawingMode) {
            case BED:
                return new Bed(point, 100, 150);
            case DESK:
                return new Desk(point, width, height);
            case CHAIR:
                return new Chair(point, 25, 25);
            case TOILET:
                return new Toilet(point, 25, 25);
            case SHOWER:
                return new Shower(point, 40, 80);
            case SINK:
                return new Sink(point, width, height);
            default:
                throw new IllegalArgumentException("Unknown drawing mode: " + drawingMode);
        }
    }

    public enum DrawingMode {
        NONE,
        WALL,
        WINDOW,
        BED,
        DOOR,
        DESK,
        CHAIR,
        TOILET,
        SHOWER,
        SINK,
        // Add more drawing modes as needed
    }
}
