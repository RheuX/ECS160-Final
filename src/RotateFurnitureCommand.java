import java.util.List;

public class RotateFurnitureCommand implements Command {
    private final ManageCanvas manageCanvas;
    private final List<FurnitureObject> selectedFurniture;
    private final double rotationAngle;

    public RotateFurnitureCommand(ManageCanvas manageCanvas, List<FurnitureObject> selectedFurniture, double rotationAngle) {
        this.manageCanvas = manageCanvas;
        this.selectedFurniture = selectedFurniture;
        this.rotationAngle = rotationAngle;
    }

    @Override
    public void execute() {
        // Rotate selected furniture objects
        for (FurnitureObject furniture : selectedFurniture) {
            furniture.setRotationAngle(rotationAngle);
        }
    }

    @Override
    public void undo() {
        // Undo the rotation by resetting the rotation angles of selected furniture objects
        for (FurnitureObject furniture : selectedFurniture) {
            furniture.setRotationAngle(-rotationAngle); // Reset rotation angle to 0
        }
    }
}
