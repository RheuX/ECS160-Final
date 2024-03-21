import java.awt.Point;

public class AddCommand implements Command {
    private final ManageCanvas manageCanvas;
    private final FurnitureObject furnitureObject;
    private final StructureObject structureObject;

    public AddCommand(ManageCanvas manageCanvas, FurnitureObject furnitureObject, StructureObject structureObject) {
        this.manageCanvas = manageCanvas;
        this.furnitureObject = furnitureObject;
        this.structureObject = structureObject;
    }

    @Override
    public void execute() {
        if (furnitureObject != null) {
            manageCanvas.addFurniture(furnitureObject);
        }
        if (structureObject != null) {
            manageCanvas.addStructure(structureObject);
        }
    }

    @Override
    public void undo() {
        if (furnitureObject != null) {
            manageCanvas.deleteFurniture(furnitureObject);
        }
        if (structureObject != null) {
            manageCanvas.deleteStructure(structureObject);
        }
    }
}
