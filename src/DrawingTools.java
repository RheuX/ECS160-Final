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
                if (tempPoint1 == null) {
                    tempPoint1 = e.getPoint();
                } else {
                    tempPoint2 = e.getPoint();
                    drawObject(createStructuralObject(tempPoint1, tempPoint2));
                    tempPoint1 = null; 
                }
            }
        });

        // Implement mouse dragging for continuous drawing
        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drawingMode != DrawingMode.NONE) {
                    fillSquare(e.getPoint());
                }
            }
        });
    }

    public void setDrawingMode(DrawingMode mode) {
        this.drawingMode = mode;
    }

    private void drawObject(StructuralObject object) {
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        object.draw(g2d);
        g2d.dispose();
    }

    private void fillSquare(Point point) {
        // Snap the drawing to the grid
        int gridSize = 30; // Adjust the size of the grid as needed
        int snappedX = (point.x / gridSize) * gridSize;
        int snappedY = (point.y / gridSize) * gridSize;
    
        // Draw different objects based on the drawing mode
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(snappedX, snappedY, gridSize, gridSize);
        g2d.dispose();
    }

    private StructuralObject createStructuralObject(Point startPoint, Point endPoint) {
        switch (drawingMode) {
            case WALL:
                return new Wall(startPoint, endPoint);
            case WINDOW :
                return new Window(startPoint, endPoint);
            case DOOR :
                return new Door(startPoint, endPoint); 
            default:
                return null;
        }
    }

    public enum DrawingMode {
        NONE,
        WALL,
        WINDOW,
        DOOR,
        TABLE,
        CHAIR
        // Add more drawing modes as needed
    }
}