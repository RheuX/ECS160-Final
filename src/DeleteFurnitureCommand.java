import java.util.List;

public class DeleteFurnitureCommand implements Command {
    private final ManageCanvas manageCanvas;
    private final List<FurnitureObject> deletedFurniture;

    public DeleteFurnitureCommand(ManageCanvas manageCanvas, List<FurnitureObject> deletedFurniture) {
        this.manageCanvas = manageCanvas;
        this.deletedFurniture = deletedFurniture;
    }

    @Override
    public void execute() {
        for (FurnitureObject furnitureObject : deletedFurniture) {
            manageCanvas.deleteFurniture(furnitureObject);
        }
    }

    @Override
    public void undo() {
        for (FurnitureObject furnitureObject : deletedFurniture) {
            manageCanvas.addFurniture(furnitureObject);
        }
    }
}
