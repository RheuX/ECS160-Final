import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DrawingTools {
    private JPanel canvas;
    private DrawingMode drawingMode = DrawingMode.NONE;

    public DrawingTools(JPanel canvas) {
        this.canvas = canvas;
        setupDrawing();
    }

    private void setupDrawing() {
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (drawingMode != DrawingMode.NONE) {
                    drawObject(e.getPoint());
                }
            }
        });

        // Implement mouse dragging for continuous drawing
        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drawingMode != DrawingMode.NONE) {
                    drawObject(e.getPoint());
                }
            }
        });
    }

    public void setDrawingMode(DrawingMode mode) {
        this.drawingMode = mode;
    }

    private void drawObject(Point point) {
        // Snap the drawing to the grid
        int gridSize = 30; // Adjust the size of the grid as needed
        int snappedX = (point.x / gridSize) * gridSize;
        int snappedY = (point.y / gridSize) * gridSize;
    
        // Draw different objects based on the drawing mode
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        g2d.setColor(Color.BLACK);
        switch (drawingMode) {
            case WALL:
                g2d.fillRect(snappedX, snappedY, gridSize, gridSize); // Fill the grid cell with the wall color
                break;
            case TABLE:
                // Draw table logic
                break;
            case CHAIR:
                // Draw chair logic
                break;
            // Add more cases for other objects as needed
            default:
                break;
        }
        g2d.dispose();
    }
    

    public enum DrawingMode {
        NONE,
        WALL,
        TABLE,
        CHAIR
        // Add more drawing modes as needed
    }
    
}
