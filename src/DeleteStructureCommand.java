import java.util.List;

public class DeleteStructureCommand implements Command {
    private final ManageCanvas manageCanvas;
    private final List<StructureObject> deletedFurniture;

    public DeleteStructureCommand(ManageCanvas manageCanvas, List<StructureObject> deletedFurniture) {
        this.manageCanvas = manageCanvas;
        this.deletedFurniture = deletedFurniture;
    }

    @Override
    public void execute() {
        for (StructureObject structureObject : deletedFurniture) {
            manageCanvas.deleteStructure(structureObject);
        }
    }

    @Override
    public void undo() {
        for (StructureObject structureObject : deletedFurniture) {
            manageCanvas.addStructure(structureObject);
        }
    }
}
