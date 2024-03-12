import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DrawingTools {
    private JPanel canvas;
    private DrawingMode drawingMode = DrawingMode.NONE;
    private Point tempPoint1;
    private Point tempPoint2;
    private static ManageCanvas manageCanvas;

    public DrawingTools(JPanel canvas, ManageCanvas manageCanvas) {
        this.canvas = canvas;
        DrawingTools.manageCanvas = manageCanvas; 
        setupDrawing();
    }

    private void setupDrawing() {
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePress(e);
            }
        });
    }

    private void handleMousePress(MouseEvent e) {
        Point clickPoint = e.getPoint();
        if (drawingMode == DrawingMode.NONE) {
            handleFurnitureSelection(clickPoint);
        } else if (isStructure()) {
            handleStructureCreation(clickPoint);
        } else if (isFurniture()) {
            handleFurnitureCreation(clickPoint);
        }
    }

    private void handleFurnitureSelection(Point clickPoint) {
        for (FurnitureObject furniture : manageCanvas.getAllFurniture()) {
            if (furniture.contains(clickPoint)) {
                furniture.setSelected(!furniture.isSelected());
                redrawCanvas();
                break;
            }
        }
    }

    private void handleStructureCreation(Point clickPoint) {
        if (tempPoint1 == null) {
            tempPoint1 = clickPoint;
        } else {
            tempPoint2 = clickPoint;
            StructureObject object = createStructuralObject(tempPoint1, tempPoint2);
            drawStructuralObject(object);
            manageCanvas.addStructure(object);
            tempPoint1 = null;
            tempPoint2 = null;
        }
    }

    private void handleFurnitureCreation(Point clickPoint) {
        FurnitureObject furnitureObject = createFurnitureObject(clickPoint, 50, 100);
        drawFurnitureObject(furnitureObject);
        manageCanvas.addFurniture(furnitureObject);
    }

    private boolean isStructure() {
        return drawingMode == DrawingMode.DOOR || drawingMode == DrawingMode.WALL || drawingMode == DrawingMode.WINDOW;
    }

    private boolean isFurniture() {
        return (!isStructure() && drawingMode != DrawingMode.NONE);
    }


    public void setDrawingMode(DrawingMode mode) {
        this.drawingMode = mode;
    }

    public void deleteSelectedObjects() {
        // Iterate over objects in canvas and delete selected ones
        for (FurnitureObject furnitureObject : manageCanvas.getAllFurniture()) {
            if (furnitureObject.isSelected()) {
                manageCanvas.deleteFurniture(furnitureObject);
            }
        }
    
        for (StructureObject structureObject : manageCanvas.getAllStructures()) {
            if (structureObject.isSelected()) {
                manageCanvas.deleteStructure(structureObject);
            }
        }
    
        // Redraw canvas after deletion
        redrawCanvas();
    }

    public void rotateSelectedObjects(double angleInDegrees) {
        for (FurnitureObject furniture : manageCanvas.getAllFurniture()) {
            if (furniture.isSelected()) {
                furniture.rotate(angleInDegrees);
            }
        }
        redrawCanvas();
    }    
    
    private void redrawCanvas() {
        canvas.repaint(); 
    }
    
    private void drawStructuralObject(StructureObject object) {
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        object.draw(g2d);
        g2d.dispose();
    }

    static BufferedImage drawAllObjects(MainCanvasPanel canvas) {
        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
    
        // Draw all furniture objects
        for (FurnitureObject furniture : manageCanvas.getAllFurniture()) {
            furniture.draw(g2d);
        }

        // Draw all structure objects
        for (StructureObject structure : manageCanvas.getAllStructures()) {
            structure.draw(g2d);
        }
    
        g2d.dispose();
        return image;
    }

    public void drawFurnitureObject(FurnitureObject furnitureObject) {
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
        return FurnitureFactory.createFurniture(drawingMode, point, width, height);
    }

    public enum DrawingMode {
        NONE,
        RESIZE,
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