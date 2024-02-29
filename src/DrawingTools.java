import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DrawingTools {
    private JPanel canvas;
    private DrawingMode drawingMode = DrawingMode.NONE;
    private boolean deleteMode = false; // Flag for delete mode
    private Point tempPoint1;
    private Point tempPoint2;
    private ManageCanvas manageCanvas;

    public DrawingTools(JPanel canvas, ManageCanvas manageCanvas) {
        this.canvas = canvas;
        this.manageCanvas = manageCanvas;
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
                        StructureObject object = createStructuralObject(tempPoint1, tempPoint2);
                        drawStructuralObject(object);
                        manageCanvas.addStructure(object); // Add structure to ManageCanvas
                        tempPoint1 = null;
                    }
                } else {
                    FurnitureObject furnitureObject = createFurnitureObject(e.getPoint(), 50, 100);
                    drawFurnitureObject(furnitureObject);
                    manageCanvas.addFurniture(furnitureObject); // Add furniture to ManageCanvas
                }
            }
        });
    }

    public void setDrawingMode(DrawingMode mode) {
        this.drawingMode = mode;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
    }

    private boolean isStructure() {
        return drawingMode == DrawingMode.DOOR || drawingMode == DrawingMode.WALL || drawingMode == DrawingMode.WINDOW;
    }

    private void deleteObject(Point point) {
        if (deleteMode) {
            // Iterate over objects in canvas and check if point is inside any object
            for (StructureObject structureObject : manageCanvas.getAllStructures()) {
                if (isPointInsideObject(point, structureObject)) {
                    manageCanvas.deleteStructure(structureObject);
                    redrawCanvas();
                    return; // Exit the method once an object is deleted
                }
            }
    
            for (FurnitureObject furnitureObject : manageCanvas.getAllFurniture()) {
                if (isPointInsideObject(point, furnitureObject)) {
                    manageCanvas.deleteFurniture(furnitureObject);
                    redrawCanvas();
                    return; // Exit the method once an object is deleted
                }
            }
        }
    }
    
    private boolean isPointInsideObject(Point point, StructureObject structureObject) {
        // Check if the point is inside the boundaries of the structureObject
        // Implement this method based on your specific object shapes and properties
        return false; // Placeholder return, replace with actual implementation
    }
    
    private boolean isPointInsideObject(Point point, FurnitureObject furnitureObject) {
        // Check if the point is inside the boundaries of the furnitureObject
        // Implement this method based on your specific object shapes and properties
        return false; // Placeholder return, replace with actual implementation
    }
    
    private void redrawCanvas() {
        // Redraw the canvas after deleting an object
        // You might need to call methods to clear the canvas and redraw all objects
        // Implement this method based on your canvas implementation
    }
    
    private void drawStructuralObject(StructureObject object) {
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        object.draw(g2d);
        g2d.dispose();
    }

    private void drawFurnitureObject(FurnitureObject furnitureObject) {
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        furnitureObject.draw(g2d);
        g2d.dispose();
    }

    private StructureObject createStructuralObject(Point startPoint, Point endPoint) {
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
                return new Sink(point, 60, 45);
            case TABLE:
                return new Table(point, width+20, height+40);
            case DININGCHAIR:
                return new DiningChair(point, 25, 25); 
            case REFRIGERATOR:
                return new Refrigerator(point, 50, 50);
            case SOFA:
                return new Sofa(point, width, height);
            case TV:
                return new TV(point, width, height);
            case COUCH:
                return new Couch(point, 50, 40);
            default:
                throw new IllegalArgumentException("Unknown drawing mode: " + drawingMode);
        }
    }

    public enum DrawingMode {
        NONE,
        DELETE,
        RESIZE,
        ROTATE_LEFT,
        ROTATE_RIGHT,
        ROTATE_FLIP,
        WALL,
        WINDOW,
        BED,
        DOOR,
        DESK,
        CHAIR,
        TOILET,
        SHOWER,
        SINK,
        TABLE,
        DININGCHAIR,
        REFRIGERATOR,
        TV,
        SOFA,
        COUCH
        // Add more drawing modes as needed
    }
}
