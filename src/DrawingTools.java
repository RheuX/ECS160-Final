import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.List;
import java.awt.geom.AffineTransform;

public class DrawingTools {
    private JPanel canvas;
    private DrawingMode drawingMode = DrawingMode.NONE;
    private Point tempPoint1;
    private Point tempPoint2;
    private static ManageCanvas manageCanvas;
    private final CommandManager commandManager;

    public DrawingTools(JPanel canvas, ManageCanvas manageCanvas, CommandManager commandManager) {
        this.canvas = canvas;
        DrawingTools.manageCanvas = manageCanvas; 
        this.commandManager = commandManager;
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
        AddFurnitureCommand addCommand = new AddFurnitureCommand(manageCanvas, furnitureObject);
        commandManager.executeCommand(addCommand); // Execute the command
    }

    private boolean isStructure() {
        return drawingMode == DrawingMode.DOOR || drawingMode == DrawingMode.WALL || drawingMode == DrawingMode.WINDOW;
    }

    private boolean isFurniture() {
        return (!isStructure() && drawingMode != DrawingMode.NONE);
    }


    public void setDrawingMode(DrawingMode mode) {
        System.out.println("Drawing Mode =  " + mode);
        this.drawingMode = mode;
    }
    
    public void deleteSelectedObjects() {
        List<FurnitureObject> selectedFurniture = new ArrayList<>();
        for (FurnitureObject furnitureObject : manageCanvas.getAllFurniture()) {
            if (furnitureObject.isSelected()) {
                selectedFurniture.add(furnitureObject);
            }
        }

        // Create a command to delete selected furniture
        DeleteFurnitureCommand deleteCommand = new DeleteFurnitureCommand(manageCanvas, selectedFurniture);
        
        // Execute the command
        commandManager.executeCommand(deleteCommand);
    
        // for (StructureObject structureObject : manageCanvas.getAllStructures()) {
        //     if (structureObject.isSelected()) {
        //         manageCanvas.deleteStructure(structureObject);
        //     }
        // }
    
        // Redraw canvas after deletion
        redrawCanvas();
    }

    public void rotateSelectedFurniture(double rotationAngle) {
        List<FurnitureObject> selectedFurniture = new ArrayList<>();
        for (FurnitureObject furnitureObject : manageCanvas.getAllFurniture()) {
            if (furnitureObject.isSelected()) {
                selectedFurniture.add(furnitureObject);
            }
        }
    
        // Create a command to rotate selected furniture objects
        RotateFurnitureCommand rotateCommand = new RotateFurnitureCommand(manageCanvas, selectedFurniture, rotationAngle);
        
        // Execute the command
        commandManager.executeCommand(rotateCommand);
    
        // Redraw the canvas
        redrawCanvas();
    }
    
    private void redrawCanvas() {
        // Redraw the canvas after deleting an object
        // You might need to call methods to clear the canvas and redraw all objects
        // Implement this method based on your canvas implementation
        canvas.repaint(); 
    }
    
    private void drawStructuralObject(StructureObject object) {
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        object.draw(g2d);
        g2d.dispose();
    }

    static BufferedImage drawAllFurnitureAndStructures(MainCanvasPanel canvas) {
    BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = image.createGraphics();
    
    // Draw all furniture objects
    for (FurnitureObject furniture : manageCanvas.getAllFurniture()) {
        double rotationAngle = Math.toRadians(furniture.getRotationAngle());
        AffineTransform oldTransform = g2d.getTransform(); // Save the current transform
        AffineTransform rotationTransform = AffineTransform.getRotateInstance(rotationAngle, furniture.getStartPoint().getX(), furniture.getStartPoint().getY());
        g2d.transform(rotationTransform); // Apply rotation transformation
        furniture.draw(g2d); // Draw the furniture object
        g2d.setTransform(oldTransform); // Restore the original transform
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

        // Get the rotation angle (in radians or degrees)
        double rotationAngle = Math.toRadians(furnitureObject.getRotationAngle()); // Convert degrees to radians if needed

        // Create a new AffineTransform for rotation
        AffineTransform oldTransform = g2d.getTransform(); // Save the current transform
        AffineTransform rotationTransform = AffineTransform.getRotateInstance(rotationAngle, furnitureObject.getStartPoint().getX(), furnitureObject.getStartPoint().getY());
        g2d.transform(rotationTransform);

        // Draw the furniture object
        furnitureObject.draw(g2d);

        // Restore the original transform
        g2d.setTransform(oldTransform);

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