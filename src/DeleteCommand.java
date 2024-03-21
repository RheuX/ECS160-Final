import java.util.List;

public class DeleteCommand implements Command {
    private final ManageCanvas manageCanvas;
    private final List<FurnitureObject> deletedFurniture;
    private final List<StructureObject> deletedStructure;

    public DeleteCommand(ManageCanvas manageCanvas, List<FurnitureObject> deletedFurniture, List<StructureObject> deletedStructure) {
        this.manageCanvas = manageCanvas;
        this.deletedFurniture = deletedFurniture;
        this.deletedStructure = deletedStructure;
    }

    @Override
    public void execute() {
        for (FurnitureObject furnitureObject : deletedFurniture) {
            manageCanvas.deleteFurniture(furnitureObject);
        }

        for (StructureObject structureObject : deletedStructure) {
            manageCanvas.deleteStructure(structureObject);
        }
    }

    @Override
    public void undo() {
        for (FurnitureObject furnitureObject : deletedFurniture) {
            manageCanvas.addFurniture(furnitureObject);
        }

        for (StructureObject structureObject : deletedStructure) {
            manageCanvas.addStructure(structureObject);
        }
    }
}
